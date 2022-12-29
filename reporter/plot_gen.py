import pandas as pd
import plotly.graph_objects as go
from plotly.subplots import make_subplots


ROUNDS = range(2, 38)
POSITIONS = ['gol', 'zag', 'lat', 'mei', 'ata', 'tec']
BUDGETS = [4.0, 8.0, 12.0]


class GraphGen:
	def __init__(self) -> None:
		self.df = pd.read_csv("data/resultados.csv")

	def plot_avg_diff_by_budget(self):
		fig = make_subplots(
			rows=3,
			cols=2,
			subplot_titles=(
				list(map(lambda x: x.upper(), POSITIONS))
			)
		)

		plot_coords = {
			0: (1, 1),
			1: (1, 2),
			2: (2, 1),
			3: (2, 2),
			4: (3, 1),
			5: (3, 2)
		}

		for index, position in enumerate(POSITIONS):
			df = self.df[self.df["query_posicao"] == position]

			low_avg = df[df["query_orcamento"] == 4.0]["diferenca"].abs().mean()
			med_avg = df[df["query_orcamento"] == 8.0]["diferenca"].abs().mean()
			hig_avg = df[df["query_orcamento"] == 12.0]["diferenca"].abs().mean()
			all_avg = map(lambda x: round(x, 2), [low_avg, med_avg, hig_avg])

			data = pd.DataFrame({
				"budget": BUDGETS, 
				"avg_value": all_avg
			})

			row, col = plot_coords[index]

			fig.append_trace(go.Bar(
				x=data["budget"].map(lambda x: str(x)),
				y=data["avg_value"],
				text=data["avg_value"],
				marker_color="indianred",
				showlegend=False
			), row=row, col=col)

		fig.update_layout(
			width=800,
			title_text="Média da diferença por posicao de todas as rodadas"
		)

		fig.show()

		# positions_df = pd.DataFrame(POSITIONS)

		# for index, budget in enumerate(BUDGETS):
		# 	df = self.df
		# 	players_within_budget_df = df[df["query_orcamento"] == budget]

		

# def plot_graph(answer):
# 	data = pd.read_csv("data/resultados.csv")
# 	df = data[data["query_id_rodada"] == 30]
# 	df = df[df["query_posicao"] == "ata"]

# 	fig = make_subplots(
# 		rows=3,
# 		cols=1,
# 		subplot_titles=("Busca com 4 Cartoletas", "Busca com 8 Cartoletas", "Busca com 12 Cartoletas"),
# 	)

# 	for idx, budget in enumerate([4.0, 8.0, 12.0]):
# 		within_budget_df = df[df["query_orcamento"] == budget].head(10)

# 		fig.append_trace(go.Bar(
# 			x=within_budget_df["nome"],
# 			y=within_budget_df["media_normalizada"],
# 			text=within_budget_df["media_normalizada"],
# 			name='Pontuação Esperada',
# 			marker_color='indianred',
# 		), row=idx + 1, col=1)

# 		fig.append_trace(go.Bar(
# 			x=within_budget_df["nome"],
# 			y=within_budget_df["pontuacao_obtida"],
# 			text=within_budget_df["pontuacao_obtida"],
# 			name='Pontuação Obtida',
# 			marker_color='lightsalmon',
# 		), row=idx + 1, col=1)

# 	fig.update_layout(
# 		width=800,
# 		title_text="Busca por atacante no mercado da rodada 30"
# 	)

# 	fig.show()


# "Diferença média com orçamento de 4 Cartoletas",
# "Diferença média com orçamento de 8 Cartoletas",
# "Diferença média com orçamento de 12 Cartoletas"