package tsoro_yematatu_rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	public int getPlayerId() throws RemoteException;
	
	public void lookupPlayer(String playerURL, int playerId) throws RemoteException;
	
	public void sendMove(int move, int playerId) throws RemoteException;
	
	public void updateShots(Integer[] pieces, int playerId) throws RemoteException;
	
	public void sendMessage(String message, int playerId) throws RemoteException;
}
