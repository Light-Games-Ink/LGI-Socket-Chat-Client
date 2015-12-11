/**
 * 
 */
package ru.lgi.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.TextArea;

/**
 * @author LaughingMaan
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JLabel statusLabel;
	JList<?> usersList; // ?
	JTextArea chatTextArea, userTextArea;
	JButton sendButton, settingsButton;
	private JScrollPane scrollPane_1;

	/**
	 * @constructor
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLocation((this.getX() - 800 / 2), (this.getY() - 600 / 2));
		setSize(639, 550);
		setResizable(false);
		getContentPane().setLayout(null);
		statusLabel = new JLabel();
		statusLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		statusLabel.setText("sample text");
		statusLabel.setBounds(6, 506, 100, 16);
		getContentPane().add(statusLabel);
		

		usersList = new JList<Object>(); // ??
		usersList.setBounds(468, 6, 163, 370);
		getContentPane().add(usersList);

		sendButton = new JButton("Send");
		sendButton.setBounds(339, 496, 117, 29);
		getContentPane().add(sendButton);

		settingsButton = new JButton("Settings");
		settingsButton.setBounds(516, 388, 117, 29);
		getContentPane().add(settingsButton);
		
		chatTextArea = new JTextArea(20,35);
		chatTextArea.setEditable(false);
		chatTextArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(chatTextArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 6, 450, 370);
		getContentPane().add(scrollPane);
		
		userTextArea = new JTextArea(5,6);
		userTextArea.setLineWrap(true);
		userTextArea.setEditable(true);
		scrollPane_1 = new JScrollPane(userTextArea);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 385, 450, 99);
		getContentPane().add(scrollPane_1);

	}
}
