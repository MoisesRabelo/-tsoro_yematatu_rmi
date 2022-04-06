package tsoro_yematatu_rmi.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class ChatView {
	private final static String TEXT_BUTTON = "Enviar";
	
	private JTextArea chatArea;
	private JButton chatButton;
	private JScrollPane chatScrollPane;
	private JTextField chatTextField;
	
	public ChatView() {
		this.chatArea = new JTextArea();
		this.chatButton = new JButton();
		this.chatScrollPane = new JScrollPane();
		this.chatTextField = new JTextField();
	}

	public void setUpGUI() {
		chatTextField.setBounds(750, 550, 300, 40);

		chatButton.setText(TEXT_BUTTON);
		chatButton.setBounds(1070, 550, 80, 39);
		
		chatArea.setBackground(Color.BLACK);
		chatArea.setForeground(Color.GREEN);
		
		chatArea.setEditable(false);
//		chatArea.setColumns(20);
//		chatArea.setRows(5);		
		chatArea.setWrapStyleWord(true);
		chatArea.setLineWrap(true);
		
		chatArea.append("----- WELCOME TO TSORO YEMATATU -----");
		chatArea.append("\n----- If you want to surrender write !surrender in the chat -----");
		chatArea.append("\n----- If you want to request a draw write !draw in the chat -----");


		DefaultCaret caret = (DefaultCaret) chatArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		chatScrollPane.setViewportView(chatArea);
		chatScrollPane.setBounds(750, 240, 400, 300);
	}

	/**
	 * @return the chatArea
	 */
	public JTextArea getChatArea() {
		return chatArea;
	}

	/**
	 * @param chatArea the chatArea to set
	 */
	public void setChatArea(JTextArea chatArea) {
		this.chatArea = chatArea;
	}

	/**
	 * @return the chatButton
	 */
	public JButton getChatButton() {
		return chatButton;
	}

	/**
	 * @param chatButton the chatButton to set
	 */
	public void setChatButton(JButton chatButton) {
		this.chatButton = chatButton;
	}

	/**
	 * @return the chatScrollPane
	 */
	public JScrollPane getChatScrollPane() {
		return chatScrollPane;
	}

	/**
	 * @param chatScrollPane the chatScrollPane to set
	 */
	public void setChatScrollPane(JScrollPane chatScrollPane) {
		this.chatScrollPane = chatScrollPane;
	}

	/**
	 * @return the chatTextField
	 */
	public JTextField getChatTextField() {
		return chatTextField;
	}

	/**
	 * @param chatTextField the chatTextField to set
	 */
	public void setChatTextField(JTextField chatTextField) {
		this.chatTextField = chatTextField;
	}
	
}
