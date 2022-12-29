import pandas as pd
import plotly.express as px
import plotly.graph_objects as go
from plotly.subplots import make_subplots


ROUNDS = range(2, 38)
POSITIONS = ['gol', 'zag', 'lat', 'mei', 'ata', 'tec']
BUDGETS = [4.0, 8.0, 12.0]


def get_recommended_by_round(df, round):
	values = df[df["query_id_rodada"] == round].head(1)['rank'].values
	if len(values) > 0:
		return values[0]
	return 0


class GraphGen:
	def __init__(self) -> None:
		self.df = pd.read_csv("data/resultados.csv")

	def plot_rec_player_rank(self, position: str):
		if position not in POSITIONS:
			return

		fig = make_subplots(
			rows=3,
			cols=1,
			subplot_titles=(
				list(map(lambda x: str(x), BUDGETS))
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
				"rank": map(lambda round: get_recommended_by_round(budget_df, round), range(2, 38))
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

		fig.update_layout(
			width=1024,
			title_text=f"Rank do {position.upper()} recomendado por orcamento a cada rodada"
		)

		fig.show()

	def plot_avg_rec_player_rank(self):
		data = {
			"position": POSITIONS,
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
			title="Media do rank do jogador recomendado para todas as rodadas e orcamentos para cada posicao"
		)

		fig.update_layout(
			width=800,
		)

		fig.show()


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
				marker_color=index,
				showlegend=False
			), row=row, col=col)

		fig.update_layout(
			width=800,
			title_text="Média da diferença por posicao de todas as rodadas"
		)

		fig.show()
