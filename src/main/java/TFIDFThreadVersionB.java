import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TFIDFThreadVersionB implements Runnable {

    private Thread t;
    private List<List<String>> documents;
    private List<String> keywords;
    private String documentName;
    private TFIDFCalculator calc;
    public static int count = 0;
    public String fileName;

    private double highest = 0.0;

    public TFIDFThreadVersionB(List<List<String>> documents, List<String> document, String documentName, String fileName) {
        super();
        this.documents = documents;
        this.documentName = documentName;
        this.keywords = new ArrayList<String>(document);
        this.fileName = fileName;
        calc = new TFIDFCalculator();
        System.out.println("Created thread for " + documentName);
    }

    @Override
    public void run() {
        String report = "";
        highest = 0.0;

        for (String keyword : keywords) {
            for (int i = 0; i < documents.size(); i++) {
                List<String> doc = documents.get(i);
                double tfidf = calc.tfIdf(doc, documents, keyword);

                if (highest < tfidf) {
                    report = "Highest score for " + documentName + " is keyword: " + keyword + " with TFIDF score: "
                            + tfidf;
                    highest = tfidf;
                }

            }
        }

        try {
            FileWriteUtils.write(fileName, report);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        System.out.println("Starting thread for " + documentName);
        if (t == null) {
            t = new Thread(this, documentName);
            t.start();
        }
    }

    public void join() {
        if (t != null) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAlive() {
        return t.isAlive();
    }

}
