package cartola.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cartola.gamer.cbr.RealizaConsultas;
import cartola.gamer.cbr.ResultWriter;
import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.modelo.ResultCase;
import cartola.gamer.model.SearchQuery;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;

@SpringBootApplication
public class CartolaGamerApplication {
	public static void main(String[] args) {
		// SpringApplication.run(CartolaGamerApplication.class, args);
		teste();
	}

	public static void teste() {
		List<String> posicoes = new ArrayList<String>();
		posicoes.add("gol");
		posicoes.add("zag");
		posicoes.add("lat");
		posicoes.add("mei");
		posicoes.add("ata");
		posicoes.add("tec");

		List<Double> orcamentos = new ArrayList<Double>();
		orcamentos.add(4.0);
		orcamentos.add(8.0);
		orcamentos.add(12.0);

		RealizaConsultas realizaConsultas = new RealizaConsultas();

		for (int rodada = 2; rodada <= 38; rodada++) {
			for (String posicao : posicoes) {
				for (Double orcamento : orcamentos) {
					SearchQuery query = new SearchQuery();
					query.setId_rodada(rodada);
					query.setPosicao(posicao);
					query.setCusto(orcamento);
					realizarConsulta(realizaConsultas, query);
				}
			}
		}
	}

	public static void realizarConsulta(RealizaConsultas realizaConsultas, SearchQuery query) {
		CaseBaseDescription gameState = new CaseBaseDescription();
		gameState.setPosicao(query.getPosicao());
		gameState.setCusto(query.getCusto());
		gameState.setId_rodada(query.getId_rodada());
		gameState.setStatus(7);

		try {
			List<ResultCase> resultCases = realizaConsultas.retornaConsulta(gameState);
			Collections.sort(resultCases); // retorna do menor pro maior
			Collections.reverse(resultCases);

			ResultWriter.writeResult(query, resultCases);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
