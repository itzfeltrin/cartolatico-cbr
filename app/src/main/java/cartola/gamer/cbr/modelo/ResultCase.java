package cartola.gamer.cbr.modelo;

import java.util.List;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;

public class ResultCase implements Comparable<ResultCase> {
	private CaseBaseDescription description;
	private Integer rank;
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

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setRetrievedCaseList(List<RetrievedCase> retrievedCaseList) {
		this.retrievedCaseList = retrievedCaseList;
	}

	public Double getDoubleAverageScore() {
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
		return averageScore;
	}

	public String getAverageScore() {
		Double averageScore = this.getDoubleAverageScore();
		return String.format("%,.2f", averageScore);
	}

	public String getAverageScoreByCost() {
		Double averageScore = this.getDoubleAverageScore();
		Double cost = this.getDescription().getCusto();
		Double averageScoreByCost = averageScore / cost;
		return String.format("%,.2f", averageScoreByCost);
	}

	public String getScoreByCost() {
		Double score = this.getDescription().getPontuacao();
		Double cost = this.getDescription().getCusto();
		return String.format("%,.2f", score / cost);
	}

	public String getDifference() {
		Double score = this.getDescription().getPontuacao();
		Double averageScore = this.getDoubleAverageScore();
		return String.format("%,.2f", score - averageScore);
	}

	@Override
	public int compareTo(ResultCase r) {
		return this.getAverageScore().compareTo(r.getAverageScore());
	}
}
