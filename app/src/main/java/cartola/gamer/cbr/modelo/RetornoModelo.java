package cartola.gamer.cbr.modelo;

import java.util.HashMap;
import java.util.List;

public class RetornoModelo {
	List<CasosRetornadosModelo> listaCasosRetornados;
	HashMap<Double, Integer> hashCasosRecuperados;

	public RetornoModelo(List<CasosRetornadosModelo> listaCasosRetornados,
			HashMap<Double, Integer> hashCasosRecuperados) {
		this.listaCasosRetornados = listaCasosRetornados;
		this.hashCasosRecuperados = hashCasosRecuperados;
	}

	public List<CasosRetornadosModelo> getListaCasosRetornados() {
		return listaCasosRetornados;
	}

	public void setListaCasosRetornados(List<CasosRetornadosModelo> listaCasosRetornados) {
		this.listaCasosRetornados = listaCasosRetornados;
	}

	public HashMap<Double, Integer> getHashCasosRecuperados() {
		return hashCasosRecuperados;
	}

	public void setHashCasosRecuperados(HashMap<Double, Integer> hashCasosRecuperados) {
		this.hashCasosRecuperados = hashCasosRecuperados;
	}
}
