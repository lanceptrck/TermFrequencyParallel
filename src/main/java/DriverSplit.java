import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DriverSplit {

	static final String result_name = "result_version_split.txt";

	public static void main(String[] args) {

		List<String> documentNames = new ArrayList<String>();
		List<List<String>> documents = new ArrayList<List<String>>();
		List<TFIDFThreadVersionB> threadMonitorNew = new ArrayList<TFIDFThreadVersionB>();
		final int documentCount = 50;
		final int num_batch = documentCount / 25;

		DriverVersionA.clear(result_name);

		for (int i = 1; i <= documentCount; i++) {
			documentNames.add("article_" + i + ".txt");
			documents.add(ProcessUtils.getWordsFromArticle("articles/article_" + i + ".txt"));
		}

		Instant start = Instant.now();

		ExecutorService es = Executors.newFixedThreadPool(5);

		while (documents.size() > 0) {
			
		}

	}

}
