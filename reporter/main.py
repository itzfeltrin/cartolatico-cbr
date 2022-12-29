from textwrap import dedent

from plot_gen import GraphGen

answer = None

while True:
    answer = int(input(dedent("""
        1. Grafico de linha com o rank do jogador recomendado para uma determinada posicao durante as 38 rodadas
        2. Grafico de coluna com a media do ranking do jogador recomendado em uma determinada posicao para todas as rodadas e orcamentos
        3. Grafico de coluna com a diferenca media do jogador recomendado em uma determinada posicao para todas as rodadas e orcamentos
        4. Grafico de coluna com a diferenca media por custo para todas as posicoes e rodadas
        >> """)))

    if answer == -1:
        break
    elif answer < 1 or answer > 4: 
        print("Entrada invalida")
        pass

    graph_gen = GraphGen()

    if answer == 1:
        position = input(dedent("""
        Digite a posicao desejada:
        >> """))
        graph_gen.plot_rec_player_rank(position)
    elif answer == 2:
        graph_gen.plot_avg_rec_player_rank()
    elif answer == 4:
        graph_gen.plot_avg_diff_by_budget()