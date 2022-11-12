package cartola.gamer.cbr.modelo;

import java.util.Collection;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;

public class ComparedCase {
	private CaseBaseDescription description;
	private Collection<RetrievalResult> retrievedCases;

	public ComparedCase(CaseBaseDescription description, Collection<RetrievalResult> retrievedCases) {
		this.description = description;
		this.retrievedCases = retrievedCases;
	}

	public CaseBaseDescription getDescription() {
		return this.description;
	}

	public Collection<RetrievalResult> getRetrievedCases() {
		return this.retrievedCases;
	}
}
