function getPosition(id) {
  const positions = {
    1: {
      id: 1,
      nome: "Goleiro",
      abreviacao: "gol",
    },
    2: {
      id: 2,
      nome: "Lateral",
      abreviacao: "lat",
    },
    3: {
      id: 3,
      nome: "Zagueiro",
      abreviacao: "zag",
    },
    4: {
      id: 4,
      nome: "Meia",
      abreviacao: "mei",
    },
    5: {
      id: 5,
      nome: "Atacante",
      abreviacao: "ata",
    },
    6: {
      id: 6,
      nome: "Técnico",
      abreviacao: "tec",
    },
  };

  if (positions[id]) {
    return positions[id].abreviacao;
  } else {
    console.log(id);
    return "null";
  }
}

module.exports = {
  getPosition,
};
