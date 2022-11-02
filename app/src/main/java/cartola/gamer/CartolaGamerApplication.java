package cartola.gamer;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cartola.gamer.cbr.RealizaConsultas;
import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.modelo.CasosRetornadosModelo;
import cartola.gamer.cbr.modelo.RetornoModelo;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;

@SpringBootApplication
public class CartolaGamerApplication {
	public static void main(String[] args) {		
		SpringApplication.run(CartolaGamerApplication.class, args);
		
		RealizaConsultas realizaConsultas = new RealizaConsultas();
		
		CaseBaseDescription gameState = new CaseBaseDescription();
//		gameState.setId(101997);
//		gameState.setId_rodada(30);
//		gameState.setNome("Braian Romero");
		gameState.setPosicao("lat");
		gameState.setStatus(7);
		gameState.setCusto(9.90);
		gameState.setUltima_pontuacao(10.60);
//		gameState.setMedia_pontuacao(2.00);
		gameState.setMando("F");
		gameState.setId_oponente(276);
		
		try {
			RetornoModelo result = realizaConsultas.retornaConsulta(gameState);
			List<CasosRetornadosModelo> casosRetornados = result.getListaCasosRetornados();
			for (CasosRetornadosModelo caso : casosRetornados) {
				System.out.println(caso.getCaseRetrieved().toString());
			}
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
