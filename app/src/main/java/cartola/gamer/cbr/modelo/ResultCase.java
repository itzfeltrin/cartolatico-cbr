package cartola.gamer.cbr.modelo;

import java.util.List;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;

public class ResultCase {
	private CaseBaseDescription description;
	private List<RetrievedCase> retrievedCaseList;

	public ResultCase(CaseBaseDescription description, List<RetrievedCase> retrievedCaseList) {
		this.description = description;
		this.retrievedCaseList = retrievedCaseList;
	}

	public CaseBaseDescription getDescription() {
		return this.description;
	}

	public List<RetrievedCase> getRetrievedCaseList() {
		return this.retrievedCaseList;
	}

	public void setRetrievedCaseList(List<RetrievedCase> retrievedCaseList) {
		this.retrievedCaseList = retrievedCaseList;
	}

	public String getAverageScore() {
		Double overallScore = 0.0;
		for (RetrievedCase retrievedCase : this.retrievedCaseList) {
			Double retrievedCaseScore = retrievedCase.getCaseRetrieved().getPontuacao();
			if (retrievedCaseScore == null) {
				overallScore += 0.0;
			} else {
				overallScore += retrievedCaseScore;
			}
		}
		Double averageScore = overallScore / this.retrievedCaseList.size();
		return String.format(String.format("%,.2f", averageScore));
	}
}
