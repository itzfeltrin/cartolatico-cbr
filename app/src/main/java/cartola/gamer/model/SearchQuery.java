package cartola.gamer.model;

public class SearchQuery {
	private String posicao;
	private double custo;
	private String mando;
	private int id_oponente;
	

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

	public String getMando() {
		return this.mando;
	}

	public void setMando(String mando) {
		this.mando = mando;
	}

	public int getId_oponente() {
		return this.id_oponente;
	}

	public void setId_oponente(int id_oponente) {
		this.id_oponente = id_oponente;
	}
}
