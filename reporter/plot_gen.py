import pandas as pd
import plotly.graph_objects as go
import plotly.express as px
from plotly.subplots import make_subplots


def plot_graph(labels=[], data=[]):
	data = pd.read_csv("data/resultados.csv")
	df = data[data["query_id_rodada"] == 30]
	df = df[df["query_posicao"] == "ata"]

	fig = make_subplots(
		rows=3,
		cols=1,
		subplot_titles=("Busca com 4 Cartoletas", "Busca com 8 Cartoletas", "Busca com 12 Cartoletas"),
	)

	for idx, budget in enumerate([4.0, 8.0, 12.0]):
		within_budget_df = df[df["query_orcamento"] == budget].head(5)

		fig.append_trace(go.Bar(
			x=within_budget_df["nome"],
			y=within_budget_df["media_normalizada"],
			text=within_budget_df["media_normalizada"],
			name='Pontuação Esperada',
			marker_color='indianred',
		), row=idx + 1, col=1)

		fig.append_trace(go.Bar(
			x=within_budget_df["nome"],
			y=within_budget_df["pontuacao_obtida"],
			text=within_budget_df["pontuacao_obtida"],
			name='Pontuação Obtida',
			marker_color='lightsalmon',
		), row=idx + 1, col=1)

	fig.update_layout(
		width=800,
		title_text="Busca por atacante no mercado da rodada 30"
	)

	fig.show()