import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {

	static final String result_name = "result_version_a.txt";

	public static void main(String[] args) {

		// Getting the registry

		try {
			Registry registry;


			registry = LocateRegistry.getRegistry("192.168.0.109", 9600);

			// Looking up the registry for the remote object
			ITFIDFCalculator calculator = (ITFIDFCalculator) registry.lookup("ITFIDFCalculator");

			List<String> documentNames = new ArrayList<String>();
			List<List<String>> documents = new ArrayList<List<String>>();
			TFIDFCalculator calc = new TFIDFCalculator();
			double highest = 0.0;
			String documentName = "article_";
			final int documentCount = 50;
			final String result_name = "result_version_a_no_thread_rmi.txt";

			DriverVersionA.clear(result_name);

			for (int i = 1; i <= documentCount; i++) {
				documentNames.add("article_" + i + ".txt");
				documents.add(ProcessUtils.getWordsFromArticle("articles/article_" + i + ".txt"));
			}

			Instant start = Instant.now();
			// time passes

			List<String> keywords = Arrays.asList("covid-19", "covid", "sars-cov-2", "pandemic", "health", "vaccine",
					"cases", "government", "duterte", "lockdowns", "lockdown", "robredo", "philippines", "virus",
					"corruption", "quarantine", "president", "antibody", "antibodies", "FDA", "donald", "trump",
					"research", "study", "studies", "healthcare", "workers", "death", "deaths");

			System.out.println(keywords.size());

			List<String> doc = new ArrayList<String>();
			try {

				int counter = 0;
				for (String keyword : keywords) {
					highest = 0;
					for (int i = 0; i < documents.size(); i++) {
						doc = documents.get(i);

						double tfidf;
						if (counter % 2 == 0)
							tfidf = calculator.tfIdf(doc, documents, keyword);
						else
							tfidf = calc.tfIdf(doc, documents, keyword);

						if (highest < tfidf) {
							documentName = "article_" + (i + 1) + ".txt";
							highest = tfidf;
						}
						counter++;
					}

					String report = "Highest tf-idf score for keyword (" + keyword + ") is: " + highest
							+ " on document: " + documentName;

					FileWriteUtils.write(result_name, report);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			Instant end = Instant.now();
			Duration timeElapsed = Duration.between(start, end);

			String timeElapsedInfo = "Time elapsed is " + timeElapsed;

			try {
				FileWriteUtils.write(result_name, timeElapsedInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
