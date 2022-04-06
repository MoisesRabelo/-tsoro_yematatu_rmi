package tsoro_yematatu_rmi.app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import tsoro_yematatu_rmi.server.Server;

public class ServerRun {
	public static void main(String[] args) {

		try {
			Server server = new Server();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//localhost/ServerRef", server);

			System.out.println("Waiting Connections...");
		} catch (Exception e) {
			System.out.println("Exception - main()");
		}

	}
}
