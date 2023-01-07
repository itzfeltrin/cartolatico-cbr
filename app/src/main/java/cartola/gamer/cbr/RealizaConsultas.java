package cartola.gamer.cbr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.modelo.RetrievedCase;
import cartola.gamer.cbr.modelo.ComparedCase;
import cartola.gamer.cbr.modelo.ResultCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;

public class RealizaConsultas {
	Connector _connector;
	CBRCaseBase _caseBase = null;
	CBR cbr = new CBR();

	public RealizaConsultas() {
		if (this._caseBase == null) {
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
	}

	public List<ResultCase> retornaConsulta(CaseBaseDescription gameState) throws ExecutionException {
		CBRQuery query = new CBRQuery();
		query.setDescription(gameState);

		ArrayList<ComparedCase> comparedCases = cbr.preloadQuery(_caseBase, query);

		List<ResultCase> result = new ArrayList<>();

		for (ComparedCase comparedCase : comparedCases) {
			List<RetrievedCase> retrievedCaseList = new ArrayList<RetrievedCase>();

			for (RetrievalResult retrievedCase : comparedCase.getRetrievedCases()) {
				RetrievedCase retrievedCaseModel = new RetrievedCase();
				CaseBaseDescription description = (CaseBaseDescription) retrievedCase.get_case().getDescription();
				retrievedCaseModel.setCaseRetrieved(description);
				retrievedCaseModel.setSimilaridadeDoCasoComAconsulta(retrievedCase.getEval());
				retrievedCaseList.add(retrievedCaseModel);
			}

			ResultCase retorno = new ResultCase(comparedCase.getDescription(), retrievedCaseList);

			result.add(retorno);
		}

		Comparator<ResultCase> sortByScore = new Comparator<ResultCase>() {
			public int compare(ResultCase a, ResultCase b) {
				try {
					return a.getDescription().getPontuacao() > b.getDescription().getPontuacao() ? -1 : 1;
				} catch (NullPointerException ex) {
					return 1;
				}
			}
		};

		List<ResultCase> clonedResult = new ArrayList<ResultCase>(result);
		Collections.sort(clonedResult, sortByScore);

		for (ResultCase resultCase : result) {
			/*
			 * Ranking-normalizado= (n-r+1)/n
			 * 
			 * Onde:
			 * n é o número de jogadores de mesma posição é custo dentro da janela
			 * 
			 * R é a posição que o jogador ficou (o que vc pega hoje)
			 */
			Double normalizedRank = Double.valueOf(result.size() - (clonedResult.indexOf(resultCase) + 1) + 1)
					/ result.size();
			resultCase.setRank(normalizedRank);

			/* Old way below */
			// int clonedIndex = clonedResult.indexOf(resultCase);
			// resultCase.setRank(clonedIndex + 1);
		}

		return result;
	}

	public void fechaBase() {
		_caseBase.close();
	}
}
