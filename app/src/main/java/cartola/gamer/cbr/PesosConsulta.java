package cartola.gamer.cbr;

public class PesosConsulta {
	// Double id;
	Double id_atleta;
	Double id_rodada;
	Double nome;
	Double posicao;
	Double status;
	Double custo;
	Double pontuacao;
	Double ultima_pontuacao;
	Double media_pontuacao;
	Double mando;
	Double id_oponente;
	
	public PesosConsulta() {
		this.id_atleta = 1.00;
		this.id_rodada = 1.00;
		this.nome = 1.00;
		this.posicao = 1.00;
		this.status = 1.00;
		this.custo = 1.00;
		this.pontuacao = 1.00;
		this.ultima_pontuacao = 1.00;
		this.media_pontuacao = 1.00;
		this.mando = 1.00;
		this.id_oponente = 1.00;		
	}

	public Double getIdAtleta() {
		return this.id_atleta;
	}
	
	public Double getIdRodada() {
		return this.id_rodada;
	}

	public void setCodigo(Double id_rodada) {
		this.id_rodada = id_rodada;
	}

	public Double getNome() {
		return this.nome;
	}

	public void setNome(Double nome) {
		this.nome = nome;
	}

	public Double getPosicao() {
		return this.posicao;
	}

	public void setPosicao(Double posicao) {
		this.posicao = posicao;
	}

	public Double getCaseStatus() {
		return this.status;
	}

	public void setStatus(Double status) {
		this.status = status;
	}

	public Double getCusto() {
		return this.custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}
	
	public Double getPontuacao() {
		return this.pontuacao;
	}

	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Double getUltimaPontuacao() {
		return this.ultima_pontuacao;
	}

	public void setUltimaPontuacao(Double ultima_pontuacao) {
		this.ultima_pontuacao = ultima_pontuacao;
	}

	public Double getMediaPontuacao() {
		return this.media_pontuacao;
	}

	public void setMediaPontuacao(Double media_pontuacao) {
		this.media_pontuacao = media_pontuacao;
	}

	public Double getMando() {
		return this.mando;
	}

	public void setMando(Double mando) {
		this.mando = mando;
	}

	public Double getIdOponente() {
		return this.id_oponente;
	}

	public void setIdOponente(Double id_oponente) {
		this.id_oponente = id_oponente;
	}
}
