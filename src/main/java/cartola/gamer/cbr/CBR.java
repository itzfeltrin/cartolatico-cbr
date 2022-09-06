package cartola.gamer.cbr;

import java.util.Collection;
import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import es.ucm.fdi.gaia.jcolibri.casebase.CachedLinealCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.connector.DataBaseConnector;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.selection.SelectCases;


public class CBR{

    public Connector initialize_conector() throws ExecutionException{
        Connector _connector = null;
        //System.out.println(Base);
        try{
            System.out.println("Iniciando conexão es.ucm.fdi.gaia.jcolibri...");
            _connector = new DataBaseConnector();
            _connector.initFromXMLfile(es.ucm.fdi.gaia.jcolibri.util.FileIO.findFile("cartola/gamer/cbr/hibernate/databaseconfig.xml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return _connector;
    }

    public CBRCaseBase initialize_caseBase() throws ExecutionException{
        CBRCaseBase _caseBase = new CachedLinealCaseBase();
        //CBRCaseBase _caseBase = new LinealCaseBase();
        return _caseBase;
    }

    /**
     * Realiza a conecao do conetor com a base
     *
     * @param _caseBase
     * @param _connector
     * @return
     * @throws InitializingException
     */
    public CBRCaseBase openConnectionBase(CBRCaseBase _caseBase, Connector _connector) throws InitializingException{
        System.out.println("Antes de iniciar...");
        System.out.println(_connector);
        _caseBase.init(_connector);
        System.out.println("Depois de iniciar...");
        //for(CBRCase c : _caseBase.getCases()) {
        //System.out.println(c);
        //}/
        return _caseBase;
    }

    /**
     * Fecha a conexao entre a base e o conector
     *
     * @param _connector
     * @throws ExecutionException
     */
    public void closeConnection(Connector _connector) throws ExecutionException{
        _connector.close();
    }

    public Collection<RetrievalResult> executeQuery(CBRCaseBase _caseBase, CBRQuery query) throws ExecutionException{
        CaseBaseDescription desc = (CaseBaseDescription) query.getDescription();
        NNConfig simConfig = new NNConfig();
        simConfig.setDescriptionSimFunction(new Average());

        PesosConsulta pesos = new PesosConsulta();

        // codigo
        if(desc.getCodigo() != null){
            Attribute codigo = new Attribute("codigo", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("codigo", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(codigo, pesos.getCodigo());
            System.out.println("SimConfig codigo: " + simConfig.getLocalSimilFunction(codigo));
        }
        // tipo_incidente
        if(desc.getTipo_incidente() != null){
            Attribute tipo_incidente = new Attribute("tipo_incidente", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("tipo_incidente", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(tipo_incidente, pesos.getTipo_incidente());
            System.out.println("SimConfig tipo_incidente: " + simConfig.getLocalSimilFunction(tipo_incidente));
        }
        // hora_deteccao
        if(desc.getHora_deteccao() != null){
            Attribute hora_deteccao = new Attribute("hora_deteccao", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("hora_deteccao", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(hora_deteccao, pesos.getHora_deteccao());
            System.out.println("SimConfig hora_deteccao: " + simConfig.getLocalSimilFunction(hora_deteccao));
        }
        // ip_origem
        if(desc.getIp_origem() != null){
            Attribute ip_origem = new Attribute("ip_origem", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("ip_origem", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(ip_origem, pesos.getIp_origem());
            System.out.println("SimConfig ip_origem: " + simConfig.getLocalSimilFunction(ip_origem));
        }
        // portas_origem
        if(desc.getPortas_origem() != null){
            Attribute portas_origem = new Attribute("portas_origem", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("portas_origem", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(portas_origem, pesos.getPortas_origem());
            System.out.println("SimConfig portas_origem: " + simConfig.getLocalSimilFunction(portas_origem));
        }
        // url_maliciosa
        if(desc.getUrl_maliciosa() != null){
            Attribute url_maliciosa = new Attribute("url_maliciosa", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("url_maliciosa", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(url_maliciosa, pesos.getUrl_maliciosa());
            System.out.println("SimConfig url_maliciosa: " + simConfig.getLocalSimilFunction(url_maliciosa));
        }
        // hostname_origem
        if(desc.getHostname_origem() != null){
            Attribute hostname_origem = new Attribute("hostname_origem", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("hostname_origem", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(hostname_origem, pesos.getHostname_origem());
            System.out.println("SimConfig hostname_origem: " + simConfig.getLocalSimilFunction(hostname_origem));
        }
        // ip_cc
        if(desc.getIp_cc() != null){
            Attribute ip_cc = new Attribute("ip_cc", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("ip_cc", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(ip_cc, pesos.getIp_cc());
            System.out.println("SimConfig ip_cc: " + simConfig.getLocalSimilFunction(ip_cc));
        }
        // porta_cc
        if(desc.getPorta_cc() != null){
            Attribute porta_cc = new Attribute("porta_cc", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("porta_cc", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(porta_cc, pesos.getPorta_cc());
            System.out.println("SimConfig porta_cc: " + simConfig.getLocalSimilFunction(porta_cc));
        }
        // hostname_cc
        if(desc.getHostname_cc() != null){
            Attribute hostname_cc = new Attribute("hostname_cc", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("hostname_cc", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(hostname_cc, pesos.getHostname_cc());
            System.out.println("SimConfig hostname_cc: " + simConfig.getLocalSimilFunction(hostname_cc));
        }
        // nome_malware_falha
        if(desc.getNome_malware_falha() != null){
            Attribute nome_malware_falha = new Attribute("nome_malware_falha", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("nome_malware_falha", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(nome_malware_falha, pesos.getNome_malware_falha());
            System.out.println("SimConfig nome_malware_falha: " + simConfig.getLocalSimilFunction(nome_malware_falha));
        }
        // titulo
        if(desc.getTitulo() != null){
            Attribute titulo = new Attribute("titulo", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("titulo", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(titulo, pesos.getTitulo());
            System.out.println("SimConfig titulo: " + simConfig.getLocalSimilFunction(titulo));
        }
        // tamanho
        if(desc.getTamanho() != null){
            Attribute tamanho = new Attribute("tamanho", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("tamanho", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(tamanho, pesos.getTamanho());
            System.out.println("SimConfig tamanho: " + simConfig.getLocalSimilFunction(tamanho));
        }
        // protocolo
        if(desc.getProtocolo() != null){
            Attribute protocolo = new Attribute("protocolo", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("protocolo", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(protocolo, pesos.getProtocolo());
            System.out.println("SimConfig protocolo: " + simConfig.getLocalSimilFunction(protocolo));
        }
        // sistema_operacional
        if(desc.getSistema_operacional() != null){
            Attribute sistema_operacional = new Attribute("sistema_operacional", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("sistema_operacional", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(sistema_operacional, pesos.getSistema_operacional());
            System.out.println("SimConfig sistema_operacional: " + simConfig.getLocalSimilFunction(sistema_operacional));
        }
        // conta
        if(desc.getConta() != null){
            Attribute conta = new Attribute("conta", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("conta", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(conta, pesos.getConta());
            System.out.println("SimConfig conta: " + simConfig.getLocalSimilFunction(conta));
        }
        // informacoes
        if(desc.getInformacoes() != null){
            Attribute informacoes = new Attribute("informacoes", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("informacoes", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(informacoes, pesos.getInformacoes());
            System.out.println("SimConfig informacoes: " + simConfig.getLocalSimilFunction(informacoes));
        }
        // aplicacao
        if(desc.getAplicacao() != null){
            Attribute aplicacao = new Attribute("aplicacao", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("aplicacao", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(aplicacao, pesos.getAplicacao());
            System.out.println("SimConfig aplicacao: " + simConfig.getLocalSimilFunction(aplicacao));
        }
        // tentativas
        if(desc.getTentativas() != null){
            Attribute tentativas = new Attribute("tentativas", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("tentativas", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(tentativas, pesos.getTentativas());
            System.out.println("SimConfig tentativas: " + simConfig.getLocalSimilFunction(tentativas));
        }
        // tratamento_sugerido
        if(desc.getTratamento_sugerido() != null){
            Attribute tratamento_sugerido = new Attribute("tratamento_sugerido", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("tratamento_sugerido", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(tratamento_sugerido, pesos.getTratamento_sugerido());
            System.out.println("SimConfig tratamento_sugerido: " + simConfig.getLocalSimilFunction(tratamento_sugerido));
        }
        // diagnostico_inicial
        if(desc.getDiagnostico_inicial() != null){
            Attribute diagnostico_inicial = new Attribute("diagnostico_inicial", CaseBaseDescription.class);
            simConfig.addMapping(new Attribute("diagnostico_inicial", CaseBaseDescription.class), new Equal());
            simConfig.setWeight(diagnostico_inicial, pesos.getDiagnostico_inicial());
            System.out.println("SimConfig diagnostico_inicial: " + simConfig.getLocalSimilFunction(diagnostico_inicial));
        }

        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 5);

        return eval;
    }
}
