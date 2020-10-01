import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DriverVersionA {

	static final String result_name = "result_version_a.txt";

	public static void main(String args[]) {
		List<String> documentNames = new ArrayList<String>();
		List<List<String>> documents = new ArrayList<List<String>>();
		List<TFIDFThreadVersionA> threadMonitor = new ArrayList<TFIDFThreadVersionA>();
		final int documentCount = 50;

		clear(result_name);

		for (int i = 1; i <= documentCount; i++) {
			documentNames.add("article_" + i + ".txt");
			documents.add(ProcessUtils.getWordsFromArticle("articles/article_" + i + ".txt"));
		}

		Instant start = Instant.now();
		// time passes

		List<String> keywords = Arrays.asList("covid-19", "covid", "sars-cov-2", "pandemic", "health", "vaccine",
				"cases", "government", "duterte", "lockdowns", "lockdown", "robredo", "philippines", "virus",
				"corruption", "quarantine", "president", "antibody", "antibodies", "FDA", "donald", "trump", "research",
				"study", "studies", "healthcare", "workers", "death", "deaths");

		for (String keyword : keywords) {
			TFIDFThreadVersionA t = new TFIDFThreadVersionA(documents, keyword, result_name);
			// es.submit(t);
			threadMonitor.add(t);
			t.start();
		}

		/*
		 * es.shutdown();
		 * 
		 * try { es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); } catch
		 * (InterruptedException e) {
		 * 
		 * }
		 */

		for (TFIDFThreadVersionA t : threadMonitor) {
			t.join();
		}

		System.out.println("\nCheck counter: " + TFIDFThreadVersionA.count);

		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);

		String timeElapsedInfo = "Time elapsed is " + timeElapsed;

		try {
			FileWriteUtils.write(result_name, timeElapsedInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void clear(String name) {
		FileWriter fwOb;
		try {
			fwOb = new FileWriter(name, false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
