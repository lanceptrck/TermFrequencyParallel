
public class TFIDFJob implements Runnable {

	private TFIDFQueue queue;

	public TFIDFJob(TFIDFQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
		queue.printJob(new Object());
	}

}
