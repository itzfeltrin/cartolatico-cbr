import csv

from model.player import Player
from model.player_collection import PlayerCollection

from textwrap import dedent

from plot_gen import GraphGen


# Open the CSV file
with open('data/resultados.csv', 'r') as file:
    # Create a CSV reader object
    reader = csv.DictReader(file, fieldnames=["query_id_rodada", "query_orcamento", "query_posicao", "nome", "posicao", "custo", "id_oponente",
                            "media_normalizada", "potencial_por_cartoleta", "pontuacao_obtida", "pontuacao_obtida_por_cartoleta", "diferenca", "rank"])

    next(reader)

    player_collection = PlayerCollection()

    # Iterate through the rows of the CSV file
    for row in reader:
        player_collection.players.append(Player(row))

    player_collection.calculate_accuracy()

    # filename, position, budget = sys.argv

    # data = []
    # for rodada in range(2, 38):
    #     players = PlayerCollection.get_by_round(player_collection.players, rodada)
    #     players = PlayerCollection.get_by_budget(players, float(budget))
    #     players = PlayerCollection.get_by_position(players, position)

    #     players = sorted(players, key=lambda player: player.rank_diferenca)

    #     avg_rank_difference = 0
    #     for i in range(10):
    #         if i == len(players): break
    #         player = players[i]
    #         avg_rank_difference += player.rank_diferenca
    #         # print(f"{player.posicao} - {player.nome}: {player.rank} ({player.rank_diferenca}) -> {player.assertividade}")
    #     avg_rank_difference /= len(players)
    #     avg_rank_difference = int(avg_rank_difference)
    #     data.append(avg_rank_difference)

    answer = None

    while answer != "-1":
        answer = int(input(dedent("""
            1. Grafico de linha com o rank do jogador recomendado para uma determinada posicao durante as 38 rodadas
            2. Grafico de coluna com a media do ranking do jogador recomendado em uma determinada posicao para todas as rodadas e orcamentos
            3. Grafico de coluna com a diferenca media do jogador recomendado em uma determinada posicao para todas as rodadas e orcamentos
            4. Grafico de coluna com a diferenca media por custo para todas as posicoes e rodadas
            >> """)))

        if answer < 1 or answer > 4: 
            print("Entrada invalida")
            pass

        graph_gen = GraphGen()

        if answer == 1:
            position = input(dedent("""
            Digite a posicao desejada:
            >> """))
            graph_gen.plot_recc_player_rank(position)
        elif answer == 4:
            graph_gen.plot_avg_diff_by_budget()