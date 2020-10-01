import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientWithThreadB {

    static final String result_name = "result_version_b_client.txt";

    public static void main(String[] args) {
        try {
            Registry registry;

            registry = LocateRegistry.getRegistry("192.168.106", 9600);

            ThreadVersionBRemote remote = (ThreadVersionBRemote) registry.lookup("FactoryB");
            List<TFIDFThreadVersionB> threadMonitor = new ArrayList<TFIDFThreadVersionB>();

            List<String> documentNames = new ArrayList<String>();
            List<List<String>> documents = new ArrayList<List<String>>();
            final int documentCount = 25;

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

            int counter = 0;
            for (int i = 0; i < documentCount; i++) {
                if (counter % 2 == 0) {
                    TFIDFThreadVersionB t = new TFIDFThreadVersionB(documents, documents.get(i), documentNames.get(i), result_name);
                    threadMonitor.add(t);
                    t.start();
                } else {
                    remote.run(documents, documents.get(i), documentNames.get(i), result_name);
                }
                counter++;
            }

            for (TFIDFThreadVersionB t : threadMonitor) {
                t.join();
            }

            remote.joinAll();

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
