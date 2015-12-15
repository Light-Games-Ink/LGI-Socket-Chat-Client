/**
 * 
 */
package ru.lgi.main;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * @author LaughingMaan
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JLabel statusLabel;
	JList<?> usersList; // ?
	// JTextArea userTextArea;
	JEditorPane chatTextEditor, userTextArea;
	JButton sendButton, settingsButton, reconnectButton;
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
		statusLabel.setForeground(Color.BLACK);
		statusLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		statusLabel.setBounds(6, 506, 100, 16);
		getContentPane().add(statusLabel);
		

		usersList = new JList<Object>(); // ??
		usersList.setBounds(468, 6, 163, 370);
		getContentPane().add(usersList);

		sendButton = new JButton("Send");
		sendButton.setBounds(339, 496, 117, 29);
		getContentPane().add(sendButton);

		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		settingsButton.setBounds(506, 389, 117, 29);
		getContentPane().add(settingsButton);
		
		chatTextEditor = new JEditorPane();
		chatTextEditor.setContentType("text/html");
		chatTextEditor.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatTextEditor);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 6, 450, 370);
		getContentPane().add(scrollPane);
		  
		userTextArea = new JEditorPane();
		userTextArea.setContentType("text/plain");
		userTextArea.setEditable(true);
		scrollPane_1 = new JScrollPane(userTextArea);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(6, 385, 450, 99);
		getContentPane().add(scrollPane_1);
		
		reconnectButton = new JButton("Reconnect");
		reconnectButton.setEnabled(false);
		reconnectButton.setBounds(506, 430, 117, 29);
		getContentPane().add(reconnectButton);

	}
}
