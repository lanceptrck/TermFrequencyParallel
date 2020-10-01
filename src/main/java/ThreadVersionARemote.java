import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThreadVersionARemote extends Remote {

	public void run(List<List<String>> documents, String keyword, String fileName) throws RemoteException;

	public void joinAll() throws RemoteException;

	public List<String> getRemainingFileContents(String fileName) throws RemoteException;

	public void clear(String fileName) throws RemoteException;

}
