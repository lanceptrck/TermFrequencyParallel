import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThreadVersionBRemote extends Remote {

    public void run(List<List<String>> documents, List<String> document, String documentName, String fileName) throws RemoteException;

    public void joinAll() throws RemoteException;

    public List<String> getRemainingFileContents(String fileName) throws RemoteException;

}
