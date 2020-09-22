import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TFIDFQueue {

	private final Lock queueLock = new ReentrantLock();
	private int jobs = 0;

	public void printJob(Object document) {

		jobs++;

		queueLock.lock();
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());

			queueLock.unlock();
			jobs--;
		}
	}

}
