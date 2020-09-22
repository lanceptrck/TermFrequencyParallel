import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class TFIDFThreadVersionA implements Runnable, Serializable {

	private Thread t;
	private List<List<String>> documents;
	private String documentName;
	private String keyword;
	private TFIDFCalculator calculator;
	private String threadName;
	public static int count = 0;

	private double highest = 0.0;

	public TFIDFThreadVersionA(List<String> document, List<List<String>> documents, String documentName,
			String keyword) {
		super();
		this.documents = documents;
		this.documentName = documentName;
		this.keyword = keyword;
		calculator = new TFIDFCalculator();
		this.threadName = documentName + " with keyword: " + keyword;
		System.out.println("Created thread for " + threadName);
	}

	public TFIDFThreadVersionA(List<List<String>> documents, String keyword) {
		super();
		this.documents = documents;
		this.keyword = keyword;
		calculator = new TFIDFCalculator();
		System.out.println("Created thread for " + keyword);
	}

	public void run() {

		// lock.lock();

		try {

			TimeUnit.MILLISECONDS.sleep(0);

			System.out.println("Running thread of " + keyword);

			List<String> currentDocument = new ArrayList<String>();

			for (int i = 0; i < documents.size(); i++) {
				currentDocument = documents.get(i);
				double tfidf = calculator.tfIdf(currentDocument, documents, keyword);

				if (highest < tfidf) {
					documentName = "article_" + (i + 1) + ".txt";
					highest = tfidf;
				}

				count++;

			}

			String report = "Highest tf-idf score for keyword (" + keyword + ") is: " + highest + " on document: "
					+ documentName;

			FileWriteUtils.write(DriverVersionA.result_name, report);

		} catch (InterruptedException e) {
			System.out.println("Thread " + keyword + " interrupted.");
		} catch (IOException ioe) {
			System.out.println("Wew");
		} finally {
			// lock.unlock();
			System.out.println("Thread " + keyword + " is exiting.");
		}

	}

	public void start() {
		System.out.println("Starting thread for " + keyword);
		if (t == null) {
			t = new Thread(this, keyword);
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
