package cartola.gamer.web.model;


import javax.persistence.*;

@Entity
@Table(name = "casos")

public class BaseCasos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "tipo_incidente")
    private String tipo_incidente;

    @Column(name = "hora_deteccao")
    private String hora_deteccao;

    @Column(name = "ip_origem")
    private String ip_origem;

    @Column(name = "portas_origem")
    private String portas_origem;

    @Column(name = "url_maliciosa")
    private String url_maliciosa;

    @Column(name = "hostname_origem")
    private String hostname_origem;

    @Column(name = "ip_cc")
    private String ip_cc;

    @Column(name = "porta_cc")
    private String porta_cc;

    @Column(name = "hostname_cc")
    private String hostname_cc;

    @Column(name = "nome_malware_falha")
    private String nome_malware_falha;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tamanho")
    private String tamanho;

    @Column(name = "protocolo")
    private String protocolo;

    @Column(name = "sistema_operacional")
    private String sistema_operacional;

    @Column(name = "conta")
    private String conta;

    @Column(name = "informacoes")
    private String informacoes;

    @Column(name = "aplicacao")
    private String aplicacao;

    @Column(name = "tentativas")
    private Integer tentativas;

    @Column(name = "tratamento_sugerido")
    private String tratamento_sugerido;

    @Column(name = "diagnostico_inicial")
    private String diagnostico_inicial;

    @Column(name = "passos")
    private String passos;

    public BaseCasos(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTipo_incidente() {
		return tipo_incidente;
	}

	public void setTipo_incidente(String tipo_incidente) {
		this.tipo_incidente = tipo_incidente;
	}

	public String getHora_deteccao() {
		return hora_deteccao;
	}

	public void setHora_deteccao(String hora_deteccao) {
		this.hora_deteccao = hora_deteccao;
	}

	public String getIp_origem() {
		return ip_origem;
	}

	public void setIp_origem(String ip_origem) {
		this.ip_origem = ip_origem;
	}

	public String getPortas_origem() {
		return portas_origem;
	}

	public void setPortas_origem(String portas_origem) {
		this.portas_origem = portas_origem;
	}

	public String getUrl_maliciosa() {
		return url_maliciosa;
	}

	public void setUrl_maliciosa(String url_maliciosa) {
		this.url_maliciosa = url_maliciosa;
	}

	public String getHostname_origem() {
		return hostname_origem;
	}

	public void setHostname_origem(String hostname_origem) {
		this.hostname_origem = hostname_origem;
	}

	public String getIp_cc() {
		return ip_cc;
	}

	public void setIp_cc(String ip_cc) {
		this.ip_cc = ip_cc;
	}

	public String getPorta_cc() {
		return porta_cc;
	}

	public void setPorta_cc(String porta_cc) {
		this.porta_cc = porta_cc;
	}

	public String getHostname_cc() {
		return hostname_cc;
	}

	public void setHostname_cc(String hostname_cc) {
		this.hostname_cc = hostname_cc;
	}

	public String getNome_malware_falha() {
		return nome_malware_falha;
	}

	public void setNome_malware_falha(String nome_malware_falha) {
		this.nome_malware_falha = nome_malware_falha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getSistema_operacional() {
		return sistema_operacional;
	}

	public void setSistema_operacional(String sistema_operacional) {
		this.sistema_operacional = sistema_operacional;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Integer getTentativas() {
		return tentativas;
	}

	public void setTentativas(Integer tentativas) {
		this.tentativas = tentativas;
	}

	public String getTratamento_sugerido() {
		return tratamento_sugerido;
	}

	public void setTratamento_sugerido(String tratamento_sugerido) {
		this.tratamento_sugerido = tratamento_sugerido;
	}

	public String getDiagnostico_inicial() {
		return diagnostico_inicial;
	}

	public void setDiagnostico_inicial(String diagnostico_inicial) {
		this.diagnostico_inicial = diagnostico_inicial;
	}

	public String getPassos() {
		return passos;
	}

	public void setPassos(String passos) {
		this.passos = passos;
	}
    
    
}
