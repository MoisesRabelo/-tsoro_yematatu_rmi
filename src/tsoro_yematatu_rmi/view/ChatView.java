package tsoro_yematatu_rmi.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatView {
	private final static String TEXT_BUTTON = "Enviar";
	
	private JTextArea chatArea;
	private JButton chatButton;
	private JScrollPane chatScrollPane;
	private JTextField chatTextField;
	
	/**
	 * Constructor
	 */
	public ChatView() {
		this.chatArea = new JTextArea();
		this.chatButton = new JButton();
		this.chatScrollPane = new JScrollPane();
		this.chatTextField = new JTextField();
	}

	/**
	 * 
	 */
	public void setUpGUI() {
		chatTextField.setBounds(750, 550, 300, 40);
		
		chatButton.setBounds(1070, 550, 80, 40);
		chatButton.setText(TEXT_BUTTON);
		
		chatArea.setBackground(Color.BLACK);
		chatArea.setForeground(Color.GREEN);
		
		chatScrollPane.setBounds(750, 240, 400, 300);
		chatScrollPane.setViewportView(chatArea);
		
		
		chatArea.append("-> BEM-VINDO ^^");
	}

	/**
	 * @return the chatArea
	 */
	public JTextArea getChatArea() {
		return chatArea;
	}

	/**
	 * @return the chatButton
	 */
	public JButton getChatButton() {
		return chatButton;
	}

	/**
	 * @return the chatScrollPane
	 */
	public JScrollPane getChatScrollPane() {
		return chatScrollPane;
	}

	/**
	 * @return the chatTextField
	 */
	public JTextField getChatTextField() {
		return chatTextField;
	}

}
