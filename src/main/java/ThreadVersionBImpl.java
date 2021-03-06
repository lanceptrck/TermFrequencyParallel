import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ThreadVersionBImpl implements ThreadVersionBRemote {

    private List<TFIDFThreadVersionB> threadMonitor;
    private LanguageReader lr;

    public ThreadVersionBImpl() {
        this.threadMonitor = new ArrayList<>();
    }

    @Override
    public void run(List<List<String>> documents, List<String> document, String documentName, String fileName) throws RemoteException {
        TFIDFThreadVersionB threadVersionB = new TFIDFThreadVersionB(documents, document, documentName, fileName);
        threadMonitor.add(threadVersionB);
        threadVersionB.start();
    }

    @Override
    public void joinAll() throws RemoteException {
        for(TFIDFThreadVersionB t : threadMonitor){
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
