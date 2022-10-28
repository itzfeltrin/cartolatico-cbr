async function createConnection() {
  const mysql = require("mysql2/promise");

  const connection = await mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "cartolatico",
  });

  return connection;
}

async function createTables() {
  const connection = await createConnection();

  await connection.execute("DROP TABLE IF EXISTS atletas");
  await connection.execute("DROP TABLE IF EXISTS clubes");

  await connection.execute(`CREATE TABLE clubes (
    id INT NOT NULL,
    nome VARCHAR(20) NOT NULL,
    abreviacao CHAR(3) NOT NULL,
    nome_fantasia VARCHAR(20) NOT NULL,

    CONSTRAINT pk_clube PRIMARY KEY (id)
  )`);

  await connection.execute(`CREATE TABLE atletas (
    id SERIAL PRIMARY KEY,
    id_atleta INT NOT NULL,
    id_rodada INT NOT NULL,
    nome VARCHAR(30) NOT NULL,
    posicao CHAR(3) NOT NULL,
    status INT NOT NULL,
    custo DECIMAL(4, 2) NOT NULL,
    pontuacao DECIMAL(4, 2),
    ultima_pontuacao DECIMAL(4, 2) NOT NULL,
    media_pontuacao DECIMAL(4, 2) NOT NULL,
    mando CHAR(1) NOT NULL,
    id_oponente INT,

    CONSTRAINT fk_id_oponente FOREIGN KEY (id_oponente) REFERENCES clubes(id)
  )`);

  await connection.end();
}

function cleanNumericString(numericStr) {
  return Number(numericStr.replace(/\D/g, ""));
}

// const fetch = (...args) =>
//   import("node-fetch").then(({ default: fetch }) => fetch(...args));

// async function seedFixtures() {
//   const fs = require("fs");
//   const path = require("path");

//   new Array(30).fill(Number).forEach(async (_, idx) => {
//     const response = await fetch(
//       `https://api.cartola.globo.com/partidas/${idx + 1}`
//     );

//     const data = await response.json();

//     fs.writeFileSync(
//       path.resolve(__dirname, "data", "fixtures", `${idx + 1}.json`),
//       JSON.stringify(data)
//     );
//   });
// }

async function seedTeams() {
  const fs = require("fs");
  const path = require("path");

  const teamsJSON = fs.readFileSync(
    path.resolve(__dirname, "data", "teams", "teams.json"),
    "utf8"
  );

  const teams = JSON.parse(teamsJSON);

  const connection = await createConnection();

  await Promise.all(
    Object.values(teams).map(async (team) =>
      connection.execute(
        `INSERT INTO clubes (id, nome, abreviacao, nome_fantasia) VALUES (?, ?, ?, ?)`,
        [team.id, team.nome, team.abreviacao, team.nome_fantasia]
      )
    )
  );

  connection.end();
}

async function seedPlayers() {
  const fs = require("fs");
  const path = require("path");
  const csv = require("csv");
  const { get } = require("lodash");
  const { getPosition } = require("./helpers");

  const roundsDirPath = path.resolve(__dirname, "data", "rounds");

  const roundFiles = fs.readdirSync(roundsDirPath, {
    withFileTypes: true,
  });

  roundFiles.sort((a, b) =>
    cleanNumericString(a.name) < cleanNumericString(b.name) ? -1 : 1
  );

  const connection = await createConnection();

  async function processFile(filename, round) {
    const roundFixturesJSON = fs.readFileSync(
      path.resolve(__dirname, "data", "fixtures", `${round}.json`),
      "utf8"
    );

    const { partidas } = JSON.parse(roundFixturesJSON);

    const records = [];

    const parser = fs
      .createReadStream(path.resolve(roundsDirPath, filename))
      .pipe(csv.parse({ columns: true, delimiter: "," }));

    for await (const record of parser) {
      const serialized = {
        id_atleta: parseInt(get(record, "atletas.atleta_id")),
        id_rodada: round,
        // id_rodada: parseInt(get(record, "atletas.rodada_id")),
        nome: get(record, "atletas.apelido"),
        posicao: get(record, "atletas.posicao_id"),
        status: parseInt(get(record, "atletas.status_id")),
        custo: parseFloat(get(record, "atletas.preco_num")),
        pontuacao: null,
        ultima_pontuacao: parseFloat(get(record, "atletas.pontos_num")),
        media_pontuacao: parseFloat(get(record, "atletas.media_num")),
        // TODO: Change two props below
        mando: "C",
        id_oponente: null,
      };

      if (serialized.posicao.length === 1) {
        serialized.posicao = getPosition(Number(serialized.posicao));
      }

      const teamId = parseInt(get(record, "atletas.clube_id"));
      const fixture = partidas.find(
        (item) =>
          get(item, "clube_casa_id") === teamId ||
          get(item, "clube_visitante_id") === teamId
      );

      if (get(fixture, "clube_casa_id") === teamId) {
        serialized.id_oponente = get(fixture, "clube_visitante_id");
      } else {
        serialized.mando = "F";
        serialized.id_oponente = get(fixture, "clube_casa_id");
      }

      records.push(serialized);
    }

    return records;
  }

  const rawRecords = await Promise.all(
    roundFiles
      .slice(1)
      .map(async (roundFile, index) => processFile(roundFile.name, index + 1))
  );

  for (let i = 1; i < rawRecords.length; i++) {
    const prevPlayers = rawRecords[i - 1];
    const players = rawRecords[i];
    players.forEach((player) => {
      const prevPlayerIndex = prevPlayers.findIndex(
        (item) => item.id_atleta === player.id_atleta
      );
      if (prevPlayerIndex >= 0)
        rawRecords[i - 1][prevPlayerIndex].pontuacao = player.ultima_pontuacao;
    });
  }

  const records = rawRecords.reduce((acc, x) => [acc, x].flat(), []);

  await Promise.all(
    records.map((record) =>
      connection.execute(
        `INSERT INTO atletas (id_atleta, id_rodada, nome, posicao, status, custo, pontuacao, ultima_pontuacao, media_pontuacao, mando, id_oponente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`,
        [
          record.id_atleta,
          record.id_rodada,
          record.nome,
          record.posicao,
          record.status,
          record.custo,
          record.pontuacao,
          record.ultima_pontuacao,
          record.media_pontuacao,
          record.mando,
          record.id_oponente,
        ]
      )
    )
  );

  connection.end();
}

async function seedTables() {
  // await seedFixtures();

  await seedTeams();

  await seedPlayers();
}

async function main() {
  await createTables();

  await seedTables();
}

main();
