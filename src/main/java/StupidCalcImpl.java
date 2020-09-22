import java.rmi.RemoteException;
import java.util.List;

public class StupidCalcImpl implements ITFIDFCalculator{

	@Override
	public double tf(List<String> doc, String term) throws RemoteException {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double idf(List<List<String>> docs, String term) throws RemoteException {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double tfIdf(List<String> doc, List<List<String>> docs, String term) throws RemoteException {
		// TODO Auto-generated method stub
		return 1;
	}

}
