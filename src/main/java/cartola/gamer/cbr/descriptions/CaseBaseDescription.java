package cartola.gamer.cbr.descriptions;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;


public class CaseBaseDescription implements CaseComponent{
    private Integer id;
    private Integer codigo;
    private Integer tipo_incidente;
    private String hora_deteccao;
    private String ip_origem;
    private String portas_origem;
    private String url_maliciosa;
    private String hostname_origem;
    private String ip_cc;
    private String porta_cc;
    private String hostname_cc;
    private String nome_malware_falha;
    private String titulo;
    private String tamanho;
    private String protocolo;
    private Integer sistema_operacional;
    private String conta;
    private String informacoes;
    private String aplicacao;
    private Integer tentativas;
    private String tratamento_sugerido;
    private String diagnostico_inicial;
    private String passos;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getCodigo(){
        return codigo;
    }

    public void setCodigo(Integer codigo){
        this.codigo = codigo;
    }

    public Integer getTipo_incidente(){
        return tipo_incidente;
    }

    public void setTipo_incidente(Integer tipo_incidente){
        this.tipo_incidente = tipo_incidente;
    }

    public String getHora_deteccao(){
        return hora_deteccao;
    }

    public void setHora_deteccao(String hora_deteccao){
        this.hora_deteccao = hora_deteccao;
    }

    public String getIp_origem(){
        return ip_origem;
    }

    public void setIp_origem(String ip_origem){
        this.ip_origem = ip_origem;
    }

    public String getPortas_origem(){
        return portas_origem;
    }

    public void setPortas_origem(String portas_origem){
        this.portas_origem = portas_origem;
    }

    public String getUrl_maliciosa(){
        return url_maliciosa;
    }

    public void setUrl_maliciosa(String url_maliciosa){
        this.url_maliciosa = url_maliciosa;
    }

    public String getHostname_origem(){
        return hostname_origem;
    }

    public void setHostname_origem(String hostname_origem){
        this.hostname_origem = hostname_origem;
    }

    public String getIp_cc(){
        return ip_cc;
    }

    public void setIp_cc(String ip_cc){
        this.ip_cc = ip_cc;
    }

    public String getPorta_cc(){
        return porta_cc;
    }

    public void setPorta_cc(String porta_cc){
        this.porta_cc = porta_cc;
    }

    public String getHostname_cc(){
        return hostname_cc;
    }

    public void setHostname_cc(String hostname_cc){
        this.hostname_cc = hostname_cc;
    }

    public String getNome_malware_falha(){
        return nome_malware_falha;
    }

    public void setNome_malware_falha(String nome_malware_falha){
        this.nome_malware_falha = nome_malware_falha;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getTamanho(){
        return tamanho;
    }

    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
    }

    public String getProtocolo(){
        return protocolo;
    }

    public void setProtocolo(String protocolo){
        this.protocolo = protocolo;
    }

    public Integer getSistema_operacional(){
        return sistema_operacional;
    }

    public void setSistema_operacional(Integer sistema_operacional){
        this.sistema_operacional = sistema_operacional;
    }

    public String getConta(){
        return conta;
    }

    public void setConta(String conta){
        this.conta = conta;
    }

    public String getInformacoes(){
        return informacoes;
    }

    public void setInformacoes(String informacoes){
        this.informacoes = informacoes;
    }

    public String getAplicacao(){
        return aplicacao;
    }

    public void setAplicacao(String aplicacao){
        this.aplicacao = aplicacao;
    }

    public Integer getTentativas(){
        return tentativas;
    }

    public void setTentativas(Integer tentativas){
        this.tentativas = tentativas;
    }

    public String getTratamento_sugerido(){
        return tratamento_sugerido;
    }

    public void setTratamento_sugerido(String tratamento_sugerido){
        this.tratamento_sugerido = tratamento_sugerido;
    }

    public String getDiagnostico_inicial(){
        return diagnostico_inicial;
    }

    public void setDiagnostico_inicial(String diagnostico_inicial){
        this.diagnostico_inicial = diagnostico_inicial;
    }

    public String getPassos(){
        return passos;
    }

    public void setPassos(String passos){
        this.passos = passos;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id", this.getClass());
    }

    @Override
    public String toString(){
        return "CaseBaseDescription{" +
                "id=" + id +
                ", codigo=" + codigo +
                ", tipo_incidente=" + tipo_incidente +
                ", hora_deteccao='" + hora_deteccao + '\'' +
                ", ip_origem='" + ip_origem + '\'' +
                ", portas_origem='" + portas_origem + '\'' +
                ", url_maliciosa='" + url_maliciosa + '\'' +
                ", hostname_origem='" + hostname_origem + '\'' +
                ", ip_cc='" + ip_cc + '\'' +
                ", porta_cc='" + porta_cc + '\'' +
                ", hostname_cc='" + hostname_cc + '\'' +
                ", nome_malware_falha='" + nome_malware_falha + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", protocolo='" + protocolo + '\'' +
                ", sistema_operacional=" + sistema_operacional +
                ", conta='" + conta + '\'' +
                ", informacoes='" + informacoes + '\'' +
                ", aplicacao='" + aplicacao + '\'' +
                ", tentativas=" + tentativas +
                ", tratamento_sugerido='" + tratamento_sugerido + '\'' +
                ", diagnostico_inicial='" + diagnostico_inicial + '\'' +
                ", passos='" + passos + '\'' +
                '}';
    }
}
