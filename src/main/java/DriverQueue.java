
public class DriverQueue {

	public static void main(String[] args) {
		TFIDFQueue printerQueue = new TFIDFQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new TFIDFJob(printerQueue), "Thread " + i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}
