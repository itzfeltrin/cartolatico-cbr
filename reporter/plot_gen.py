import pandas as pd
import plotly.express as px
import plotly.graph_objects as go
from plotly.subplots import make_subplots


ROUNDS = range(2, 38)
POSITIONS = ['gol', 'zag', 'lat', 'mei', 'ata', 'tec']
BUDGETS = [4.0, 8.0, 12.0]


def get_recommended_by_round(df, round, key="rank"):
	values = df[df["query_id_rodada"] == round].head(1)[key].values
	if len(values) > 0:
		return values[0]
	return 0


class GraphGen:
	def __init__(self) -> None:
		# self.df = pd.read_csv("data/resultados.csv")
		self.df = pd.read_csv("data/resultados_rank_normalizado.csv")

	def plot_rec_player_rank(self, position: str):
		if position not in POSITIONS:
			return

		fig = make_subplots(
			rows=3,
			cols=1,
			subplot_titles=(
				list(map(lambda x: str(x), map(lambda budget: f"Orçamento de {budget} Cartoletas", BUDGETS)))
			)
		)

		plot_coords = {
			0: (1, 1),
			1: (2, 1),
			2: (3, 1),			
		}

		pos_df = self.df[self.df["query_posicao"] == position]

		for index, budget in enumerate(BUDGETS):
			budget_df = pos_df[pos_df["query_orcamento"] == budget]

			data = pd.DataFrame({
				"round": range(2, 38),
				"rank": map(lambda rd: round(get_recommended_by_round(budget_df, rd), 2), range(2, 38))
			})

			row, col = plot_coords[index]

			fig.append_trace(go.Scatter(
				x=data["round"].map(lambda x: str(x)),
				y=data["rank"],
				mode="lines+markers+text",
				text=data["rank"],
				textposition="bottom right",
				marker_color=index,
				showlegend=False
			), row=row, col=col)


			fig.update_xaxes(title_text="Rodada", row=row, col=col)
			fig.update_yaxes(title_text="Rank", row=row, col=col)

		fig.update_layout(
			width=1024,
		)

		fig.show()

	def plot_avg_rec_player_rank(self):
		data = {
			"position": map(lambda x: x.upper(), POSITIONS),
			"recommended_avg_rank": []
		}

		for position in POSITIONS:
			pos_df = self.df[self.df["query_posicao"] == position]
			all_recommended = []
			for budget in BUDGETS:
				rec_pos_by_budget_df = pos_df[pos_df["query_orcamento"] == budget]
				for rd in range(20, 30):
					rec_pos_by_budget_by_round = get_recommended_by_round(rec_pos_by_budget_df, rd)
					all_recommended.append(round(rec_pos_by_budget_by_round, 2))

			data["recommended_avg_rank"].append(round(pd.Series(all_recommended).mean(), 2))

		data_df = pd.DataFrame(data)

		fig = px.bar(
			data_df,
			x="position",
			y="recommended_avg_rank",
			text="recommended_avg_rank",
			labels={
				"position": "Posição",
				"recommended_avg_rank": "Ranking médio"
			}
		)

		fig.update_layout(
			width=640,
			height=400
		)

		fig.show()

	def plot_avg_rec_player_diff(self):
		data = {
			"position": map(lambda x: x.upper(), POSITIONS),
			"recommended_avg_diff": []
		}

		for position in POSITIONS:
			pos_df = self.df[self.df["query_posicao"] == position]
			all_recommended = []
			for budget in BUDGETS:
				rec_pos_by_budget_df = pos_df[pos_df["query_orcamento"] == budget]
				for rd in range(20, 30):
					rec_pos_by_budget_by_round = get_recommended_by_round(rec_pos_by_budget_df, rd, key="diferenca")
					all_recommended.append(round(rec_pos_by_budget_by_round, 2))

			data["recommended_avg_diff"].append(abs(round(pd.Series(all_recommended).mean(), 2)))

		data_df = pd.DataFrame(data)

		fig = px.bar(
			data_df,
			x="position",
			y="recommended_avg_diff",
			text="recommended_avg_diff",
			labels={
				"recommended_avg_diff": "Diferença média",
				"position": "Posição"
			}
		)

		fig.update_layout(
			width=640,
			height=400
		)

		fig.show()

	def plot_avg_diff_by_budget(self):
		fig = make_subplots(
			rows=2,
			cols=3,
			subplot_titles=(
				list(map(lambda x: x.upper(), POSITIONS))
			)
		)

		plot_coords = {
			0: (1, 1),
			1: (1, 2),
			2: (1, 3),
			3: (2, 1),
			4: (2, 2),
			5: (2, 3)
		}

		for index, position in enumerate(POSITIONS):
			df = self.df[self.df["query_posicao"] == position]

			low_avg = df[df["query_orcamento"] == 4.0]["diferenca"].mean()
			med_avg = df[df["query_orcamento"] == 8.0]["diferenca"].mean()
			hig_avg = df[df["query_orcamento"] == 12.0]["diferenca"].mean()
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
				marker_color=index,
				showlegend=False,
			), row=row, col=col)

			fig.update_xaxes(title_text="Orçamento disponível", row=row, col=col)
			fig.update_yaxes(title_text="Diferença média", row=row, col=col)
			fig.update_yaxes(range=[0, 3], row=row, col=col)


		fig.update_layout(
			width=1024,
			height=600,
		)

		fig.show()
