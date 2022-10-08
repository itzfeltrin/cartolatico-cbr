package cartola.gamer.cbr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.modelo.CasosRetornadosModelo;
import cartola.gamer.cbr.modelo.RetornoModelo;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;

public class RealizaConsultas {
	Connector _connector;
	CBRCaseBase _caseBase;
	CBR cbr = new CBR();

	public RealizaConsultas() {
		System.out.println("Iniciando RealizaConsultas...");
		try {
			_caseBase = cbr.initialize_caseBase();
			System.out.println("-- _caseBase --");
			System.out.println(_caseBase);
			_connector = cbr.initialize_conector();
			System.out.println("-- _connector --");
			System.out.println(_connector);
			_caseBase = cbr.openConnectionBase(_caseBase, _connector);
		} catch (ExecutionException e) {
			org.apache.commons.logging.LogFactory.getLog(CBR.class).error(e);
		}
	}

	public RetornoModelo retornaConsulta(CaseBaseDescription gameState) throws ExecutionException {
		CaseBaseDescription queryDesc = gameState;
		CBRQuery query = new CBRQuery();
		query.setDescription(queryDesc);
		Collection<RetrievalResult> bestMatch = cbr.executeQuery(_caseBase, query);
		// lista de casos Recuperados
		List<CasosRetornadosModelo> listaCasosRetornados = new ArrayList<CasosRetornadosModelo>();

		for (RetrievalResult best : bestMatch) {
			CasosRetornadosModelo casosRetornados = new CasosRetornadosModelo();
			CaseBaseDescription description = (CaseBaseDescription) best.get_case().getDescription();
			casosRetornados.setSimilaridadeDoCasoComAconsulta(best.getEval());
			casosRetornados.setCusto(description.getCusto());
			listaCasosRetornados.add(casosRetornados);
		}

		HashMap<Double, Integer> hashCasosRecuperados = new HashMap<Double, Integer>();
		// foreach para adicionar a quantidade de acumulados
		for (int i = 0; i < listaCasosRetornados.size(); i++) {
			CasosRetornadosModelo casoRetornado = listaCasosRetornados.get(i);
			if (hashCasosRecuperados.containsKey(casoRetornado.getCusto()))
				hashCasosRecuperados.put(casoRetornado.getCusto(),
						hashCasosRecuperados.get(casoRetornado.getCusto() + 1));
			else
				hashCasosRecuperados.put(casoRetornado.getCusto(), hashCasosRecuperados.get(1));
		}

		RetornoModelo retorno = new RetornoModelo(listaCasosRetornados, hashCasosRecuperados);

		return retorno;
	}

	public void fechaBase() {
		_caseBase.close();
	}
}
