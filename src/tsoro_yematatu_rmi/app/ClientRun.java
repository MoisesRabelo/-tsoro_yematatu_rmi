package tsoro_yematatu_rmi.app;

import java.rmi.RemoteException;

import tsoro_yematatu_rmi.client.Client;

public class ClientRun {
	public static void main(String[] args) throws RemoteException {

      new Client();
    }
}
