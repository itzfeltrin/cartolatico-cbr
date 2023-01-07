package cartola.gamer.cbr;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import cartola.gamer.cbr.descriptions.CaseBaseDescription;
import cartola.gamer.cbr.modelo.ResultCase;
import cartola.gamer.model.SearchQuery;

public class ResultWriter {
	// static final String fileName = "resultados.csv";
	static final String fileName = "resultados_rank_normalizado.csv";

	public static void writeResult(SearchQuery searchQuery, List<ResultCase> results) {
		try {
			String filePath = "src/main/resources/static/csv/" + fileName;
			CSVWriter writer = new CSVWriter(
					new OutputStreamWriter(new FileOutputStream(
							filePath, true),
							StandardCharsets.UTF_8),
					';',
					CSVWriter.DEFAULT_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);

			for (ResultCase result : results) {
				CaseBaseDescription description = result.getDescription();
				List<String> line = new ArrayList<String>();
				line.add(description.getId_rodada().toString());
				line.add(searchQuery.getCusto().toString());
				line.add(searchQuery.getPosicao());
				line.add(description.getNome());
				line.add(description.getPosicao());
				line.add(description.getCusto().toString());
				line.add(description.getId_oponente().toString());
				line.add(result.getAverageScore());
				line.add(result.getAverageScoreByCost());
				if (description.getPontuacao() != null) {
					line.add(description.getPontuacao().toString());
					line.add(result.getScoreByCost());
					line.add(result.getDifference());
				} else {
					line.add("");
					line.add("");
					line.add("");
				}

				line.add(result.getRank().toString());
				writer.writeNext(line.toArray(new String[0]));
			}

			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println(ex);
		}
	}
}
