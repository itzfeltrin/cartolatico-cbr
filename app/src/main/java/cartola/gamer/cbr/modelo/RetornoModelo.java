package cartola.gamer.cbr.modelo;

import java.util.HashMap;
import java.util.List;

public class RetornoModelo{
    List<CasosRetornadosModelo> listaCasosRetornados;
    HashMap<Integer, Integer> hashCasosRecuperados;

    public RetornoModelo(List<CasosRetornadosModelo> listaCasosRetornados, HashMap<Integer, Integer> hashCasosRecuperados){
        listaCasosRetornados = listaCasosRetornados;
        hashCasosRecuperados = hashCasosRecuperados;
    }

    public List<CasosRetornadosModelo> getListaCasosRetornados(){
        return listaCasosRetornados;
    }

    public void setListaCasosRetornados(List<CasosRetornadosModelo> listaCasosRetornados){
        this.listaCasosRetornados = listaCasosRetornados;
    }

    public HashMap<Integer, Integer> getHashCasosRecuperados(){
        return hashCasosRecuperados;
    }

    public void setHashCasosRecuperados(HashMap<Integer, Integer> hashCasosRecuperados){
        this.hashCasosRecuperados = hashCasosRecuperados;
    }
}
