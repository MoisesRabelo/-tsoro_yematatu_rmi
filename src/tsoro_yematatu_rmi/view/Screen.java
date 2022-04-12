package tsoro_yematatu_rmi.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel displayField;
	
	private ImageIcon imgBoard;
	private ImageIcon imgDefault;
	private ImageIcon imgPiece1;
	private ImageIcon imgPiece2;

	private JButton[] buttons = new JButton[7];
	
	private JButton btnDesist;
	
	HashMap<Integer, List<Integer>> positionsButtons;

	private ChatView chatView;

	public Screen(String playerName) {
		initComponents();
		setUpGUI(playerName);
	}

	private void initComponents() {
		displayField = new JLabel();
		
		btnDesist = new JButton();		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
		}
		
		positionsButtons = createMapPositionsButtons();

		chatView = new ChatView();
		
		imgBoard = new ImageIcon(this.getClass().getResource("/resources/board.png"));
		imgDefault = new ImageIcon(this.getClass().getResource("/resources/default.png"));
		imgPiece1 = new ImageIcon(this.getClass().getResource("/resources/piece1.png"));
		imgPiece2 = new ImageIcon(this.getClass().getResource("/resources/piece2.png"));
	}

	private void setUpGUI(String playerName) {
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		
		this.setTitle("Player " + playerName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayField.setIcon(imgBoard);
		this.setContentPane(displayField);

		// set up buttons
		setupButtons();

		// set up chat
		chatView.setUpGUI();

		this.add(chatView.getChatTextField());
		this.add(chatView.getChatButton());
		this.add(chatView.getChatScrollPane());

		this.setVisible(true);
	}
	
	private void setupButtons() {
		btnDesist.setBounds(1000, 180, 150, 40);
		btnDesist.setText("DESISTIR");

		this.add(btnDesist);
		for (int i = 0; i < buttons.length; i++) {
			List<Integer> position = positionsButtons.get(i);
			buttons[i].setBounds(position.get(0), position.get(1), position.get(2), position.get(3));

			buttons[i].setOpaque(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].setFocusable(false);
			buttons[i].setIcon(imgDefault);
			buttons[i].setDisabledIcon(imgDefault);
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setActionCommand(String.valueOf(i));

			this.add(buttons[i]);
		}
	}

	private HashMap<Integer, List<Integer>> createMapPositionsButtons() {
		HashMap<Integer, List<Integer>> positionsButtons = new HashMap<>();

		positionsButtons.put(0, new ArrayList<Integer>(Arrays.asList(290, 40, 80, 80)));
		positionsButtons.put(1, new ArrayList<Integer>(Arrays.asList(150, 325, 80, 80)));
		positionsButtons.put(2, new ArrayList<Integer>(Arrays.asList(290, 325, 80, 80)));
		positionsButtons.put(3, new ArrayList<Integer>(Arrays.asList(450, 325, 80, 80)));
		positionsButtons.put(4, new ArrayList<Integer>(Arrays.asList(40, 540, 80, 80)));
		positionsButtons.put(5, new ArrayList<Integer>(Arrays.asList(290, 540, 80, 80)));
		positionsButtons.put(6, new ArrayList<Integer>(Arrays.asList(560, 540, 80, 80)));
		
		return positionsButtons;
	}

	/**
	 * @return the displayField
	 */
	public JLabel getDisplayField() {
		return displayField;
	}

	/**
	 * @return the imgBoard
	 */
	public ImageIcon getImgBoard() {
		return imgBoard;
	}

	/**
	 * @return the imgDefault
	 */
	public ImageIcon getImgDefault() {
		return imgDefault;
	}

	/**
	 * @return the imgPiece1
	 */
	public ImageIcon getImgPiece1() {
		return imgPiece1;
	}

	/**
	 * @return the imgPiece2
	 */
	public ImageIcon getImgPiece2() {
		return imgPiece2;
	}

	/**
	 * @return the buttons
	 */
	public JButton[] getButtons() {
		return buttons;
	}

	/**
	 * @return the btnDesist
	 */
	public JButton getBtnDesist() {
		return btnDesist;
	}

	/**
	 * @return the positionsButtons
	 */
	public HashMap<Integer, List<Integer>> getPositionsButtons() {
		return positionsButtons;
	}

	/**
	 * @return the chatView
	 */
	public ChatView getChatView() {
		return chatView;
	}

}
