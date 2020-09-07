import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DriverVersionB {
	
	static final String result_name = "result_version_b.txt";

	public static void main(String[] args) {

		List<String> documentNames = new ArrayList<String>();
		List<List<String>> documents = new ArrayList<List<String>>();
		List<TFIDFThreadVersionB> threadMonitorNew = new ArrayList<TFIDFThreadVersionB>();
		final int documentCount = 25;
		
		DriverVersionA.clear(result_name);

		for (int i = 1; i <= documentCount; i++) {
			documentNames.add("article_" + i + ".txt");
			documents.add(ProcessUtils.getWordsFromArticle("articles/article_" + i + ".txt"));
		}

		Instant start = Instant.now();

		ExecutorService es = Executors.newFixedThreadPool(documents.size());

		for (int i = 0; i < documentCount; i++) {
			TFIDFThreadVersionB t = new TFIDFThreadVersionB(documents, documents.get(i), documentNames.get(i));
			es.submit(t);
			//t.start();
			threadMonitorNew.add(t);
		}

		/*
		 * for (TFIDFThread_New t : threadMonitorNew) { t.join(); }
		 */
		
		es.shutdown();

		try {
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {

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
