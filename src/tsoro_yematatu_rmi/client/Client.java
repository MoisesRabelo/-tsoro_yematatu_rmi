package tsoro_yematatu_rmi.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import tsoro_yematatu_rmi.server.IServer;
import tsoro_yematatu_rmi.view.Screen;

public class Client extends UnicastRemoteObject implements IClient {

	private static final long serialVersionUID = 1L;

	private static final String ROTE = "//localhost/serverRMI";

	private IServer iServer;
	private Screen screen;

	private int playerId;
	private int turnsMade;
	private int opponent;

	private List<Integer[]> possibleVictories;
	private Integer[] myShots;
	private Integer[] enemyShots;
	private Integer[] allShots;

	private boolean buttonsEnabled;
	private boolean winner;
	
	private int piecesUsed;
	private int enemyPieces;
	
	public Client() throws RemoteException {
		super();

		this.turnsMade = 0;
		this.myShots = new Integer[3];
		this.enemyShots = new Integer[3];
		this.allShots = new Integer[7];

		this.piecesUsed = 0;
		this.enemyPieces = 0;

		this.possibleVictories = getPossibleVictories();

		this.winner = false;

		connectServer();
		screen = new Screen(String.valueOf(playerId));
		setUpPlayers();

		setUpButtons();
		setUpChat();

		System.out.println("-> O Player " + playerId + " se conectou ao servidor");
	}

	private void connectServer() {
		try {
			iServer = (IServer) Naming.lookup(ROTE);
			playerId = iServer.getPlayerId();
			String url = "//localhost/player" + playerId + "RMI";

			Naming.rebind(url, this);
			iServer.lookupPlayer(url, playerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Integer[]> getPossibleVictories() {
		Integer[] firstColumn = {0, 1, 4};
		Integer[] secondColumn = {0, 2, 5};
		Integer[] thirdColumn = {0, 3, 6};
		Integer[] firstLine = {1, 2, 3};
		Integer[] secondLine = {4, 5, 6};
		

		List<Integer[]> list = new ArrayList<>(
				Arrays.asList(firstColumn, secondColumn, thirdColumn, firstLine, secondLine));

		return list;
	}

	private void setUpPlayers() {
		if (playerId == 1) {
			opponent = 2;
			buttonsEnabled = true;
		} else {
			opponent = 1;
			buttonsEnabled = false;
		}
		updateButtons();
	}

	private ActionListener createActionButton() {
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JButton jb = (JButton) ae.getSource();
				int btnMove = Integer.parseInt(jb.getActionCommand());

				if (piecesUsed < 3) {
					myShots[piecesUsed] = btnMove;
					piecesUsed++;
				}
				
				buttonsEnabled = false;
				turnsMade++;
				
				if (turnsMade > 3) {
					validateMoveAndUpdateShots(btnMove);
				} else {
					updateButtons();

					try {
						iServer.sendMove(btnMove, playerId);
					} catch (Exception e) {
						e.printStackTrace();
					}

					checkWinner();
				}
			}
		};
		return al;
	}

	private ActionListener createActionDesistButton() {
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				screen.getChatView().getChatArea().append("\n-> Player" + playerId + " desistiu D:"
						+ "\n" +"-> Player " + opponent + " venceu :D");

				buttonsEnabled = false;
				updateButtons();

				winner = true;
			}
		};
		
		return al;
	}
	
	private void setUpButtons() {
		screen.getBtnDesist().addActionListener(createActionDesistButton());
		ActionListener al = createActionButton();
		for (JButton button : screen.getButtons()) {
			button.addActionListener(al);
		}		
	}

	private void setUpChat() {
		//Enviar mensagem ao clicar no botão
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(!screen.getChatView().getChatTextField().getText().isEmpty()) {
					sendChatMessage();
				}
			}
		};

		//Enviar mensagem ao clicar enter
		KeyListener kl = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!screen.getChatView().getChatTextField().getText().isEmpty()) {
						sendChatMessage();
					}
				}
			}
		};

		screen.getChatView().getChatButton().addActionListener(al);
		screen.getChatView().getChatTextField().addKeyListener(kl);
	}

	private void updateButtons() {
		for (int i = 0; i < screen.getButtons().length; i++) {
			screen.getButtons()[i].setEnabled(buttonsEnabled);

			if (allShots[i] != null) {
				screen.getButtons()[i].setEnabled(false);
			}
		}

		setButtonPlayer();
	}

	private void updateButtonAfterPlay() {

		Integer[] btnEnable = new Integer[2];

		for (int i = 0; i < allShots.length; i++) {
			if (allShots[i] == null) {
				btnEnable[0] = i;
				screen.getButtons()[i].setEnabled(false);
				screen.getButtons()[i].setDisabledIcon(screen.getImgDefault());
			}
			
			if (allShots[i] != null) {
				screen.getButtons()[i].setEnabled(false);
			}
		}

		for (int i = 0; i < allShots.length; i++) {
			if (allShots[i] != null && allShots[i] == 1) {
				btnEnable[1] = i;

				for (Integer[] pv : possibleVictories) {
					if (Arrays.asList(pv).containsAll(Arrays.asList(btnEnable))) {
						screen.getButtons()[btnEnable[1]].setEnabled(buttonsEnabled);
					}
				}
			}
		}

		setButtonPlayer();
	}

	private void setButtonPlayer() {
		ImageIcon imgMyPiece;
		ImageIcon imgOpponentPiece;
		if(playerId == 1) {
			imgMyPiece = screen.getImgPiece1();
			imgOpponentPiece = screen.getImgPiece2();
		} else {
			imgMyPiece = screen.getImgPiece2();
			imgOpponentPiece = screen.getImgPiece1();
		}		
		
		for (int i = 0; i < enemyShots.length; i++) {
			if (myShots[i] != null) {
				screen.getButtons()[myShots[i]].setIcon(imgMyPiece);
				screen.getButtons()[myShots[i]].setDisabledIcon(imgMyPiece);
			}
	
			if (enemyShots[i] != null) {
				screen.getButtons()[enemyShots[i]].setDisabledIcon(imgOpponentPiece);
			}
		}
	}

	//Feito pensando em Jogo da velha
	private void checkWinner() {
		for (Integer[] pv : possibleVictories) {
			if (Arrays.asList(pv).containsAll(Arrays.asList(myShots))) {
				winner = true;
				
				screen.getChatView().getChatArea().append("\n-> Player " + playerId + " GANHOU :D");

				try {
					iServer.sendMessage("@win@", playerId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void validateMoveAndUpdateShots(int btnMove) {
		boolean isValid = false;
		
		int btnEmpty = getEmptySpace();		

		for (Integer[] pv : possibleVictories) {
			if (Arrays.asList(pv).containsAll(Arrays.asList(btnMove, btnEmpty))) {
				isValid = true;
				updateMyShots(btnMove, btnEmpty);
			}
		}

		if (isValid) {
			updateAllShotsBoard();
			updateButtonAfterPlay();

			try {
				iServer.sendMove(btnMove, playerId);
				iServer.updateShots(myShots, playerId);
			} catch (Exception e) {
				e.printStackTrace();
			}

			checkWinner();
		}
	}
	
	private int getEmptySpace() {
		for (int i = 0; i < allShots.length; i++) {
			if (allShots[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	private void updateMyShots(int btnMove, int btnEmpty) {
		for (int i = 0; i < myShots.length; i++) {
			if (myShots[i].equals(btnMove)) {
				myShots[i] = btnEmpty;
			}
		}
	}

	private void updateAllShotsBoard() {
		allShots = new Integer[7];

		for (int i = 0; i < myShots.length; i++) {
			if (myShots[i] != null) {
				allShots[myShots[i]] = 1;
			}

			if (enemyShots[i] != null) {
				allShots[enemyShots[i]] = 2;
			}
		}
	}

	@Override
	public void updateTurn(int btnMove) throws RemoteException {
		if (btnMove != -1) {
			screen.getChatView().getChatArea().append(
					"\n-> SUA VEZ Player " + playerId);

			if (enemyPieces < 3) {
				enemyShots[enemyPieces] = btnMove;
				enemyPieces++;
			}
		}

		if (turnsMade <= 3) {
			for (int i = 0; i < myShots.length; i++) {
				if (myShots[i] != null) {
					allShots[myShots[i]] = 1;
				}

				if (enemyShots[i] != null) {
					allShots[enemyShots[i]] = 2;
				}
			}
		}
		buttonsEnabled = true;

		if (turnsMade >= 3 && enemyPieces == 3) {
			updateButtonAfterPlay();
		} else {
			updateButtons();
		}

		if (!winner) {
			checkWinner();
		}		
		
	}

	@Override
	public void updateOpponentShots(Integer[] pieces) throws RemoteException {
		enemyShots = pieces;
		updateAllShotsBoard();
		buttonsEnabled = true;

		if (turnsMade >= 3 && enemyPieces == 3) {
			updateButtonAfterPlay();
		} else updateButtons();

		if (!winner) {
			checkWinner();
		}
	}

	@Override
	public void receiveMessage(String message) throws RemoteException {
		if (!message.equalsIgnoreCase("@exit@")) {
			
			if (!winner && message.equalsIgnoreCase("@win@")) {
				buttonsEnabled = false;
				winner = true;
				updateButtons();
				
				screen.getChatView().getChatArea().append("\n-> Player " + opponent + " GANHOU :D");
			}
			
			if (!message.equalsIgnoreCase("@win@")) {
				screen.getChatView().getChatArea().append("\nPlayer " + opponent + ": " + message);
			}
		}
	}
	
	public void sendChatMessage() {
		String message = screen.getChatView().getChatTextField().getText();

		screen.getChatView().getChatArea().append("\nPlayer " + playerId + ": " + message);

		try {
			iServer.sendMessage(message, playerId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		screen.getChatView().getChatTextField().setText("");
	}

}