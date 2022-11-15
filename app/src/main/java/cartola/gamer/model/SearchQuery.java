package cartola.gamer.model;

public class SearchQuery {
	private String posicao;
	private double custo;
	private int id_rodada;
	private boolean somente_provaveis;

	public String getPosicao() {
		return this.posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public double getCusto() {
		return this.custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public int getId_rodada() {
		return this.id_rodada;
	}

	public void setId_rodada(int id_rodada) {
		this.id_rodada = id_rodada;
	}

	public boolean getSomente_provaveis() {
		return this.somente_provaveis;
	}

	public void setSomente_provaveis(boolean somente_provaveis) {
		this.somente_provaveis = somente_provaveis;
	}

}
