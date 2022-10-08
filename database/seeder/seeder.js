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
    id INT NOT NULL,
    nome VARCHAR(30) NOT NULL,
    posicao CHAR(3) NOT NULL,
    custo DECIMAL(4, 2) NOT NULL,
    ultima_pontuacao DECIMAL(4, 2) NOT NULL,
    media_pontuacao DECIMAL(4, 2) NOT NULL,
    mando CHAR(1) NOT NULL,
    id_oponente INT NOT NULL,

    CONSTRAINT pk_atleta PRIMARY KEY (id),
    CONSTRAINT fk_id_oponente FOREIGN KEY (id_oponente) REFERENCES clubes(id)
  )`);

  await connection.end();
}

async function main() {
  await createTables();
}

main();
