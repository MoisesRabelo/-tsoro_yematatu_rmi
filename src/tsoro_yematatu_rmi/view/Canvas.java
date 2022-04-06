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

public class Canvas extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel displayField;
	
	private ImageIcon boardImage;
	private ImageIcon defaultImage;
	private ImageIcon player1Image;
	private ImageIcon player2Image;

	private JButton[] buttons = new JButton[7];
	HashMap<Integer, List<Integer>> positionsButtons;

	private ChatView chatView;

	public Canvas(String playerName) {
		initComponents();
		setUpGUI(playerName);
	}

	private void initComponents() {
		this.displayField = new JLabel();

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
		}
		
		positionsButtons = createMapPositionsButtons();

		chatView = new ChatView();
		
		this.boardImage = new ImageIcon(this.getClass().getResource("/resources/board.png"));
		this.defaultImage = new ImageIcon(this.getClass().getResource("/resources/default.png"));
		this.player1Image = new ImageIcon(this.getClass().getResource("/resources/piece1.png"));
		this.player2Image = new ImageIcon(this.getClass().getResource("/resources/piece2.png"));
	}

	private void setUpGUI(String playerName) {
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setBackground(Color.DARK_GRAY);
		
		this.setTitle("Tsoro Yematatu - Nome do Player: " + playerName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayField.setIcon(boardImage);
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

	
	private void setupButtons() {
		for (int i = 0; i < buttons.length; i++) {
			List<Integer> position = positionsButtons.get(i);
			buttons[i].setBounds(position.get(0), position.get(1), position.get(2), position.get(3));

			buttons[i].setOpaque(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].setFocusable(false);
			buttons[i].setIcon(defaultImage);
			buttons[i].setDisabledIcon(defaultImage);
			buttons[i].setForeground(Color.WHITE);
			buttons[i].setActionCommand(String.valueOf(i));

			this.add(buttons[i]);
		}
	}
	
	/**
	 * @return the displayField
	 */
	public JLabel getDisplayField() {
		return displayField;
	}

	/**
	 * @param displayField the displayField to set
	 */
	public void setDisplayField(JLabel displayField) {
		this.displayField = displayField;
	}

	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return boardImage;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImageIcon image) {
		this.boardImage = image;
	}

	/**
	 * @return the defaultImage
	 */
	public ImageIcon getDefaultImage() {
		return defaultImage;
	}

	/**
	 * @param defaultImage the defaultImage to set
	 */
	public void setDefaultImage(ImageIcon defaultImage) {
		this.defaultImage = defaultImage;
	}

	/**
	 * @return the player1Image
	 */
	public ImageIcon getPlayer1Image() {
		return player1Image;
	}

	/**
	 * @param player1Image the player1Image to set
	 */
	public void setPlayer1Image(ImageIcon player1Image) {
		this.player1Image = player1Image;
	}

	/**
	 * @return the player2Image
	 */
	public ImageIcon getPlayer2Image() {
		return player2Image;
	}

	/**
	 * @param player2Image the player2Image to set
	 */
	public void setPlayer2Image(ImageIcon player2Image) {
		this.player2Image = player2Image;
	}

	/**
	 * @return the buttons
	 */
	public JButton[] getButtons() {
		return buttons;
	}

	/**
	 * @param buttons the buttons to set
	 */
	public void setButtons(JButton[] buttons) {
		this.buttons = buttons;
	}

	/**
	 * @return the positionsButtons
	 */
	public HashMap<Integer, List<Integer>> getPositionsButtons() {
		return positionsButtons;
	}

	/**
	 * @param positionsButtons the positionsButtons to set
	 */
	public void setPositionsButtons(HashMap<Integer, List<Integer>> positionsButtons) {
		this.positionsButtons = positionsButtons;
	}

	/**
	 * @return the chatView
	 */
	public ChatView getChatView() {
		return chatView;
	}

	/**
	 * @param chatView the chatView to set
	 */
	public void setChatView(ChatView chatView) {
		this.chatView = chatView;
	}

}
