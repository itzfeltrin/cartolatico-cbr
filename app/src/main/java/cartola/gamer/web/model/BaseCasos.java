package cartola.gamer.web.model;

import javax.persistence.*;

@Entity
@Table(name = "atletas")

public class BaseCasos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "id_rodada")
	private Integer id_rodada;

	@Column(name = "nome")
	private String nome;

	@Column(name = "posicao")
	private String posicao;

	@Column(name = "status")
	private Integer status;

	@Column(name = "custo")
	private Double custo;

	@Column(name = "ultima_pontuacao")
	private Double ultima_pontuacao;

	@Column(name = "media_pontuacao")
	private Double media_pontuacao;

	@Column(name = "mando")
	private String mando;

	@Column(name = "id_oponente")
	private Integer id_oponente;

	public BaseCasos() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdRodada() {
		return this.id_rodada;
	}

	public void setCodigo(Integer id_rodada) {
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

	public Double custo() {
		return this.custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
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

	public String getMando() {
		return this.mando;
	}

	public void setMando(String mando) {
		this.mando = mando;
	}

	public Integer getIdOponente() {
		return this.id_oponente;
	}

	public void setIdOponente(Integer id_oponente) {
		this.id_oponente = id_oponente;
	}
}
