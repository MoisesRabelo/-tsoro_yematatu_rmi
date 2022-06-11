package tsoro_yematatu_rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tsoro_yematatu_rmi.client.IClient;

public class Server extends UnicastRemoteObject implements IServer {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYERS = 2;
	
	private int numPlayers;
	private IClient player1;
	private IClient player2;

	public Server() throws RemoteException {
		super();
		
		System.out.println("Servidor criado!");
		numPlayers = 0;
	}

	@Override
	public int getPlayerId() {
		if (numPlayers < MAX_PLAYERS) {
			numPlayers++;

			System.out.println("-> Player " + numPlayers + " conectour-se :p");

			if (numPlayers == MAX_PLAYERS) {
				System.out.println("-> Último jogador entrou, BOA PARTIDA!");
			}
		}

		return numPlayers;
	}

	@Override
	public void lookupPlayer(String playerURL, Integer playerId) throws RemoteException {
		try {
			if (playerId == 1) {
				player1 = (IClient) Naming.lookup(playerURL);
			} else {
				player2 = (IClient) Naming.lookup(playerURL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMove(Integer move, Integer playerId) throws RemoteException {
		if (playerId == 1) {
			player2.updateTurn(move);
		}

		if (playerId == 2) {
			player1.updateTurn(move);
		}
	}

	@Override
	public void updateShots(Integer[] pieces, Integer playerId) throws RemoteException {
		if (playerId == 1) {
			player2.updateOpponentShots(pieces);
		}

		if (playerId == 2) {
			player1.updateOpponentShots(pieces);
		}
	}

	@Override
	public void sendMessage(String message, Integer playerId) throws RemoteException {
		if (playerId == 1) {
			player2.receiveMessage(message);
		} else {
			player1.receiveMessage(message);
		}
	}

}
