package cartola.gamer.cbr.modelo;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;

public class RetrievedCase {
	CaseBaseDescription caseRetrieved;
	Double similaridadeDoCasoComAconsulta;

	public CaseBaseDescription getCaseRetrieved() {
		return this.caseRetrieved;
	}

	public void setCaseRetrieved(CaseBaseDescription caseRetrieved) {
		this.caseRetrieved = caseRetrieved;
	}

	public Double getSimilaridadeDoCasoComAconsulta() {
		return this.similaridadeDoCasoComAconsulta;
	}

	public String getPorcentagemDeSimilaridade() {
		return String.format("%.2f", this.getSimilaridadeDoCasoComAconsulta() * 100) + "%";
	}

	public void setSimilaridadeDoCasoComAconsulta(Double similaridadeDoCasoComAconsulta) {
		this.similaridadeDoCasoComAconsulta = similaridadeDoCasoComAconsulta;
	}
}
