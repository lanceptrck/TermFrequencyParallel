import java.util.ArrayList;
import java.util.List;

public class ThreadVersionAImpl implements ThreadVersionARemote {

	List<TFIDFThreadVersionA> threadMonitor;

	public ThreadVersionAImpl() {
		threadMonitor = new ArrayList<TFIDFThreadVersionA>();
	}

	@Override
	public void run(List<List<String>> documents, String keyword) {
		// TODO Auto-generated method stub
		TFIDFThreadVersionA threadVersionA = new TFIDFThreadVersionA(documents, keyword);
		threadMonitor.add(threadVersionA);
		threadVersionA.start();
	}
	
	@Override
	public void joinAll() {
		for(TFIDFThreadVersionA t : threadMonitor) {
			t.join();
		}
	}

}
