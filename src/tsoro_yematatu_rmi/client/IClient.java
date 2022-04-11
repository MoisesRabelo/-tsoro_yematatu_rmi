package tsoro_yematatu_rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	public void updateTurn(int btnMove) throws RemoteException;
	
	public void updateOpponentShots(Integer[] pieces) throws RemoteException;
	
	public void receiveMessage(String message) throws RemoteException;
}
