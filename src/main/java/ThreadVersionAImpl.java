import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ThreadVersionAImpl implements ThreadVersionARemote {

	List<TFIDFThreadVersionA> threadMonitor;
	private LanguageReader lr;

	public ThreadVersionAImpl() {
		threadMonitor = new ArrayList<TFIDFThreadVersionA>();
	}

	@Override
	public void run(List<List<String>> documents, String keyword, String fileName) {
		// TODO Auto-generated method stub
		TFIDFThreadVersionA threadVersionA = new TFIDFThreadVersionA(documents, keyword, fileName);
		threadMonitor.add(threadVersionA);
		threadVersionA.start();
	}
	
	@Override
	public void joinAll() {
		for(TFIDFThreadVersionA t : threadMonitor) {
			t.join();
		}
	}

	@Override
	public List<String> getRemainingFileContents(String fileName) throws RemoteException {
		lr = new LanguageReader(fileName);
		return lr.getLanguage();
	}

	@Override
	public void clear(String fileName) throws RemoteException {
		DriverVersionA.clear(fileName);
	}

}
