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

public class CBR {

	public Connector initialize_conector() throws ExecutionException {
		Connector _connector = null;
		// System.out.println(Base);
		try {
			System.out.println("Iniciando conexão es.ucm.fdi.gaia.jcolibri...");
			_connector = new DataBaseConnector();
			_connector.initFromXMLfile(es.ucm.fdi.gaia.jcolibri.util.FileIO
					.findFile("main/java/cartola/gamer/cbr/hibernate/databaseconfig.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _connector;
	}

	public CBRCaseBase initialize_caseBase() throws ExecutionException {
		CBRCaseBase _caseBase = new CachedLinealCaseBase();
		// CBRCaseBase _caseBase = new LinealCaseBase();
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
	public CBRCaseBase openConnectionBase(CBRCaseBase _caseBase, Connector _connector) throws InitializingException {
		System.out.println("Antes de iniciar...");
		System.out.println(_connector);
		_caseBase.init(_connector);
		System.out.println("Depois de iniciar...");
		// for(CBRCase c : _caseBase.getCases()) {
		// System.out.println(c);
		// }/
		return _caseBase;
	}

	/**
	 * Fecha a conexao entre a base e o conector
	 *
	 * @param _connector
	 * @throws ExecutionException
	 */
	public void closeConnection(Connector _connector) throws ExecutionException {
		_connector.close();
	}

	public Collection<RetrievalResult> executeQuery(CBRCaseBase _caseBase, CBRQuery query) throws ExecutionException {
		CaseBaseDescription desc = (CaseBaseDescription) query.getDescription();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());

		PesosConsulta pesos = new PesosConsulta();

		if (desc.getIdRodada() != null) {
			Attribute id_rodada = new Attribute("id_rodada", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("id_rodada", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(id_rodada, pesos.getIdRodada());
			System.out.println("SimConfig id_rodada: " + simConfig.getLocalSimilFunction(id_rodada));
		}

		if (desc.getNome() != null) {
			Attribute nome = new Attribute("nome", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("nome", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(nome, pesos.getNome());
			System.out.println("SimConfig nome: " + simConfig.getLocalSimilFunction(nome));
		}

		if (desc.getPosicao() != null) {
			Attribute posicao = new Attribute("posicao", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("posicao", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(posicao, pesos.getPosicao());
			System.out.println("SimConfig posicao: " + simConfig.getLocalSimilFunction(posicao));
		}

		if (desc.getStatus() != null) {
			Attribute status = new Attribute("c", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("posicao", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(status, pesos.getStatus());
			System.out.println("SimConfig status: " + simConfig.getLocalSimilFunction(status));
		}

		if (desc.getCusto() != null) {
			Attribute custo = new Attribute("custo", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("custo", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(custo, pesos.getCusto());
			System.out.println("SimConfig custo: " + simConfig.getLocalSimilFunction(custo));
		}

		if (desc.getUltimaPontuacao() != null) {
			Attribute ultima_pontuacao = new Attribute("ultima_pontuacao", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("ultima_pontuacao", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(ultima_pontuacao, pesos.getUltimaPontuacao());
			System.out.println("SimConfig ultima_pontuacao: " + simConfig.getLocalSimilFunction(ultima_pontuacao));
		}

		if (desc.getMediaPontuacao() != null) {
			Attribute media_pontuacao = new Attribute("media_pontuacao", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("media_pontuacao", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(media_pontuacao, pesos.getMediaPontuacao());
			System.out.println("SimConfig media_pontuacao: " + simConfig.getLocalSimilFunction(media_pontuacao));
		}

		if (desc.getMando() != null) {
			Attribute mando = new Attribute("mando", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("mando", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(mando, pesos.getMando());
			System.out.println("SimConfig mando: " + simConfig.getLocalSimilFunction(mando));
		}

		if (desc.getIdOponente() != null) {
			Attribute id_oponente = new Attribute("id_oponente", CaseBaseDescription.class);
			simConfig.addMapping(new Attribute("id_oponente", CaseBaseDescription.class), new Equal());
			simConfig.setWeight(id_oponente, pesos.getIdOponente());
			System.out.println("SimConfig id_oponente: " + simConfig.getLocalSimilFunction(id_oponente));
		}

		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);

		return eval;
	}
}
