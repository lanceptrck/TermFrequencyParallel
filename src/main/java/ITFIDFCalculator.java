import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ITFIDFCalculator extends Remote {

	public double tf(List<String> doc, String term) throws RemoteException;

	public double idf(List<List<String>> docs, String term) throws RemoteException;

	public double tfIdf(List<String> doc, List<List<String>> docs, String term) throws RemoteException;

}
