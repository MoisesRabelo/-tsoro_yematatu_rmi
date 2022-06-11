package tsoro_yematatu_rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	public int getPlayerId() throws RemoteException;
	
	public void lookupPlayer(String playerURL, Integer playerId) throws RemoteException;
	
	public void sendMove(Integer move, Integer playerId) throws RemoteException;
	
	public void updateShots(Integer[] pieces, Integer playerId) throws RemoteException;
	
	public void sendMessage(String message, Integer playerId) throws RemoteException;
}
