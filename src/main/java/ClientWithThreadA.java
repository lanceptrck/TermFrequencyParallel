import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientWithThreadA {

	static final String result_name_client = "result_version_a_client.txt";
	static final String result_name_server = "result_version_a_server.txt";

	public static void main(String[] args) {
		try {

			Registry registry;
			registry = LocateRegistry.getRegistry("192.168.0.106", 9600);
			ThreadVersionARemote remote = (ThreadVersionARemote)
					registry.lookup("FactoryA");

			List<TFIDFThreadVersionA> threadMonitor = new ArrayList<TFIDFThreadVersionA>();

			List<String> documentNames = new ArrayList<String>();
			List<List<String>> documents = new ArrayList<List<String>>();
			final int documentCount = 50;

			DriverVersionA.clear(result_name_client);
			remote.clear(result_name_server);

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

			int counter = 0;
			for (String keyword : keywords) {
				if (counter % 2 == 0) {
					TFIDFThreadVersionA t = new
							TFIDFThreadVersionA(documents, keyword, result_name_client);
					threadMonitor.add(t);
					t.start();
				} else {
					remote.run(documents, keyword, result_name_server);
				}

				counter++;
			}

			for (TFIDFThreadVersionA t : threadMonitor) {
				t.join();
			}

			remote.joinAll();

			Instant end = Instant.now();
			Duration timeElapsed = Duration.between(start, end);

			ProcessUtils.printLinesSeparately(remote.getRemainingFileContents(result_name_server), result_name_client);

			String timeElapsedInfo = "Time elapsed is " + timeElapsed;

			try {
				FileWriteUtils.write(result_name_client, timeElapsedInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
