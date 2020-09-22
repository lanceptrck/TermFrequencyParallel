import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ThreadVersionARemote extends Remote {

	public void run(List<List<String>> documents, String keyword) throws RemoteException;

	public void joinAll() throws RemoteException;

}
