package cartola.gamer.cbr.descriptions;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;



public class CaseBaseDescription implements CaseComponent {	
	private Integer id;
	private Integer id_atleta;
	private Integer id_rodada;
	private String nome;
	private String posicao;
	private Integer status;
	private Double custo;
	private Double pontuacao;
	private Double ultima_pontuacao;
	private Double media_pontuacao;
	private String mando;
	private Integer id_oponente;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId_atleta() {
		return this.id_atleta;
	}

	public void setId_atleta(Integer id_atleta) {
		this.id_atleta = id_atleta;
	}

	public Integer getId_rodada() {
		return this.id_rodada;
	}

	public void setId_rodada(Integer id_rodada) {
		this.id_rodada = id_rodada;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPosicao() {
		return this.posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
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

	public Double getUltima_pontuacao() {
		return this.ultima_pontuacao;
	}

	public void setUltima_pontuacao(Double ultima_pontuacao) {
		this.ultima_pontuacao = ultima_pontuacao;
	}

	public Double getMedia_pontuacao() {
		return this.media_pontuacao;
	}

	public void setMedia_pontuacao(Double media_pontuacao) {
		this.media_pontuacao = media_pontuacao;
	}

	public String getMando() {
		return this.mando;
	}

	public void setMando(String mando) {
		this.mando = mando;
	}

	public Integer getId_oponente() {
		return this.id_oponente;
	}

	public void setId_oponente(Integer id_oponente) {
		this.id_oponente = id_oponente;
	}

	@Override
    public Attribute getIdAttribute() {
        return new Attribute("id", this.getClass());
    }

	@Override
	public String toString() {
		return "CaseBaseDescription{" + "id=" + this.id + ", id_atleta=" + this.id_atleta + ", id_rodada=" + this.id_rodada + ", nome=" + this.nome
				+ ", posicao='" + this.posicao + '\'' + ", status='" + this.status + '\'' + ", custo='" + this.custo
				+ '\'' + ", pontuacao=" + this.pontuacao + ", ultima_pontuacao='" + this.ultima_pontuacao + '\'' + ", media_pontuacao='"
				+ this.media_pontuacao + '\'' + ", mando='" + this.mando + '\'' + ", id_oponente='" + this.id_oponente
				+ '\'' + '}';
	}
}
