package cartola.gamer.cbr.teste;

import cartola.gamer.cbr.RealizaConsultas;
import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.modelo.RetornoModelo;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;

public class testeConsulta {

	public static void main(String[] args) {
		CaseBaseDescription caseA = new CaseBaseDescription();
		caseA.setAplicacao("server");
		try {
			RetornoModelo retorno = new RealizaConsultas().retornaConsulta(caseA);
			System.out.println(retorno.getListaCasosRetornados().size());
			retorno.getListaCasosRetornados().forEach(f ->{
				System.out.println("Caso: "+ f.toString());
			});
			
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
