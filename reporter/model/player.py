class Player:
    def __init__(self, row) -> None:
        self.query_id_rodada = int(row["query_id_rodada"])
        self.query_orcamento = float(
            row["query_orcamento"]) if row["query_orcamento"] != "" else row["query_orcamento"]
        self.query_posicao = row["query_posicao"]
        self.nome = row["nome"]
        self.posicao = row["posicao"]
        self.custo = float(
            row["custo"]) if row["custo"] != "" else row["custo"]
        self.id_oponente = row["id_oponente"]
        self.media_normalizada = float(
            row["media_normalizada"]) if row["media_normalizada"] != "" else row["media_normalizada"]
        self.potencial_por_cartoleta = float(
            row["potencial_por_cartoleta"]) if row["potencial_por_cartoleta"] != "" else row["potencial_por_cartoleta"]
        self.pontuacao_obtida = float(
            row["pontuacao_obtida"]) if row["pontuacao_obtida"] != "" else row["pontuacao_obtida"]
        self.pontuacao_obtida_por_cartoleta = float(
            row["pontuacao_obtida_por_cartoleta"]) if row["pontuacao_obtida_por_cartoleta"] != "" else row["pontuacao_obtida_por_cartoleta"]
        self.diferenca = float(
            row["diferenca"]) if row["diferenca"] != "" else row["diferenca"]
        self.rank = int(row["rank"])
        self.rank_diferenca = None
        self.assertividade = None
