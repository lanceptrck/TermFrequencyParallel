import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {

		try {

			ITFIDFCalculator calcImpl = new TFIDFCalculator();
			ITFIDFCalculator calc = (ITFIDFCalculator)
					UnicastRemoteObject.exportObject(calcImpl, 0);

			ThreadVersionARemote factoryImpl = new ThreadVersionAImpl();
			ThreadVersionARemote factory = (ThreadVersionARemote)
					UnicastRemoteObject.exportObject(factoryImpl, 1);

			ThreadVersionBRemote factoryBImpl = new ThreadVersionBImpl();
			ThreadVersionBRemote factoryB = (ThreadVersionBRemote)
					UnicastRemoteObject.exportObject(factoryBImpl, 2);

			Registry registry = LocateRegistry.createRegistry(9600);
			registry.bind("ITFIDFCalculator", calc);
			registry.bind("FactoryA", factory);
			registry.bind("FactoryB", factoryB);

			System.err.println("Server Ready");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

}
