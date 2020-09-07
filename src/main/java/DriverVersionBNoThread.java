import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverVersionBNoThread {

	public static void main(String args[]) {
		List<String> documentNames = new ArrayList<String>();
		List<List<String>> documents = new ArrayList<List<String>>();
		TFIDFCalculator calc = new TFIDFCalculator();
		double highest = 0.0;
		String documentName = "article_";
		final int documentCount = 25;
		String result = "";
		String result_name = "result_version_b_no_thread.txt";

		DriverVersionA.clear(result_name);

		for (int i = 1; i <= documentCount; i++) {
			documentNames.add("article_" + i + ".txt");
			documents.add(ProcessUtils.getWordsFromArticle("articles/article_" + i + ".txt"));
		}

		Instant start = Instant.now();
		// time passes

		List<String> doc = new ArrayList<String>();

		for (int i = 0; i < documents.size(); i++) {
			List<String> keywords = ProcessUtils.getWordsFromArticle("articles/article_" + (i + 1) + ".txt");
			highest = 0.0;
			for (String keyword : keywords) {
				for (int j = 0; j < documents.size(); j++) {
					doc = documents.get(j);
					double tfidf = calc.tfIdf(doc, documents, keyword);

					if (highest < tfidf) {
						documentName = "article_" + (i + 1) + ".txt";
						result = "Highest score for " + documentName + " is keyword: " + keyword + " with TFIDF score: "
								+ tfidf;
						highest = tfidf;
					}

				}
			}

			try {
				FileWriteUtils.write(result_name, result);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);

		String timeElapsedInfo = "Time elapsed is " + timeElapsed;

		try {

			FileWriteUtils.write(result_name, timeElapsedInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
