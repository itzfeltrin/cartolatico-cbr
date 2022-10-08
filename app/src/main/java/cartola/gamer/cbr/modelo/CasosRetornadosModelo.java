package cartola.gamer.cbr.modelo;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;

public class CasosRetornadosModelo {
	CaseBaseDescription caseRetrieved;
	Double similaridadeDoCasoComAconsulta;
	Double custo;

	public CaseBaseDescription getCaseRetrieved() {
		return this.caseRetrieved;
	}

	public void setCaseRetrieved(CaseBaseDescription caseRetrieved) {
		this.caseRetrieved = caseRetrieved;
	}

	public Double getSimilaridadeDoCasoComAconsulta() {
		return this.similaridadeDoCasoComAconsulta;
	}

	public void setSimilaridadeDoCasoComAconsulta(Double similaridadeDoCasoComAconsulta) {
		this.similaridadeDoCasoComAconsulta = similaridadeDoCasoComAconsulta;
	}

	public Double getCusto() {
		return this.custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

}
