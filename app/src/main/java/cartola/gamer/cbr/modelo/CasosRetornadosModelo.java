package cartola.gamer.cbr.modelo;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;


public class CasosRetornadosModelo{
    CaseBaseDescription caseRetrieved;
    double similaridadeDoCasoComAconsulta;
    int tipoIncidente;


    public CaseBaseDescription getCaseRetrieved(){
        return caseRetrieved;
    }

    public void setCaseRetrieved(CaseBaseDescription caseRetrieved){
        this.caseRetrieved = caseRetrieved;
    }

    public double getSimilaridadeDoCasoComAconsulta(){
        return similaridadeDoCasoComAconsulta;
    }

    public void setSimilaridadeDoCasoComAconsulta(double similaridadeDoCasoComAconsulta){
        this.similaridadeDoCasoComAconsulta = similaridadeDoCasoComAconsulta;
    }

    public int getTipoIncidente(){
        return tipoIncidente;
    }

    public void setTipoIncidente(int tipoIncidente){
        this.tipoIncidente = tipoIncidente;
    }

}
