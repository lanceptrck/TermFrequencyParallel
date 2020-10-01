import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ThreadVersionBImpl implements ThreadVersionBRemote {

    private List<TFIDFThreadVersionB> threadMonitor;

    public ThreadVersionBImpl() {
        this.threadMonitor = new ArrayList<>();
    }

    @Override
    public void run(List<List<String>> documents, List<String> document, String documentName) throws RemoteException {
        TFIDFThreadVersionB threadVersionB = new TFIDFThreadVersionB(documents, document, documentName);
        threadMonitor.add(threadVersionB);
        threadVersionB.start();
    }

    @Override
    public void joinAll() throws RemoteException {
        for(TFIDFThreadVersionB t : threadMonitor){
            t.join();
        }
    }
}
