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
		gameState.setId(101997);
		gameState.setIdRodada(30);
		gameState.setNome("Braian Romero");
		gameState.setPosicao("ata");
		gameState.setStatus(7);
		gameState.setCusto(4.99);
		gameState.setUltimaPontuacao(2.00);
		gameState.setMediaPontuacao(1.32);
		gameState.setMando("F");
		gameState.setIdOponente(262);
		
		try {
			RetornoModelo result = realizaConsultas.retornaConsulta(gameState);
			List<CasosRetornadosModelo> casosRetornados = result.getListaCasosRetornados();
			for (CasosRetornadosModelo caso : casosRetornados) {
				System.out.println(caso.toString());
			}
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
