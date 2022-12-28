class PlayerCollection:
    def __init__(self) -> None:
        self.players = []

    def calculate_accuracy(self):
        for round in range(2, 38):
            by_round = PlayerCollection.get_by_round(self.players, round)
            for position in ["gol", "zag", "lat", "mei", "ata", "tec"]:
                by_position = PlayerCollection.get_by_position(
                    by_round, position)
                for budget in [4.0, 8.0, 12.0]:
                    by_budget = PlayerCollection.get_by_budget(
                        by_position, budget)

                    players_by_position = dict({
                        "gol": [],
                        "zag": [],
                        "lat": [],
                        "mei": [],
                        "ata": [],
                        "tec": [],
                    })

                    for player in by_budget:
                        players_by_position[player.posicao].append(player)

                    for value in players_by_position.values():
                        index = 1
                        for player in value:
                            player.rank_diferenca = abs(index - player.rank)
                            player.assertividade = index / \
                                player.rank if index > player.rank else player.rank / index
                            index += 1

    @classmethod
    def get_by_round(cls, players, round=2):
        return list(filter(lambda player: player.query_id_rodada == round, players))

    @classmethod
    def get_by_budget(cls, players, budget=4.0):
        return list(filter(lambda player: player.query_orcamento == budget, players))

    @classmethod
    def get_by_position(cls, players, position="tec"):
        return list(filter(lambda player: player.posicao == position, players))
