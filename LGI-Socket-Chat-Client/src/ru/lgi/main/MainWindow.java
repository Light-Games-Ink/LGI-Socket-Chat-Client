/**
 * 
 */
package ru.lgi.main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


/**
 * @author tails217
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JLabel statusLabel;
	@SuppressWarnings("rawtypes")
	DefaultListModel listModel;
	@SuppressWarnings("rawtypes")
	JList usersList;
	JTextArea userTextArea;
	JEditorPane chatTextEditor;
	JButton sendButton, settingsButton, reconnectButton;
	private JScrollPane scrollPane_1;
	GridBagConstraints constraints = new GridBagConstraints();
	private JPanel panel_1;
	/**
	 * @constructor
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(639, 559);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);


		listModel = new DefaultListModel();
		usersList = new JList(listModel); // ??
		usersList.setBounds(468, 6, 163, 370);
		getContentPane().add(usersList);

		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		sendButton.setBounds(339, 496, 117, 29);
		getContentPane().add(sendButton);

		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		settingsButton.setBounds(506, 389, 117, 29);
		getContentPane().add(settingsButton);

		reconnectButton = new JButton("Reconnect");
		reconnectButton.setEnabled(false);
		reconnectButton.setBounds(506, 430, 117, 29);
		getContentPane().add(reconnectButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 452, 370);
		getContentPane().add(panel);
				panel.setLayout(null);
		
				chatTextEditor = new JEditorPane();
				chatTextEditor.setContentType("text/html");
				chatTextEditor.setEditable(false);
				
				
				JScrollPane scrollPane = new JScrollPane(chatTextEditor);
				scrollPane.setBounds(0, 0, 452, 370);
				panel.add(scrollPane);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
				panel_1 = new JPanel();
				panel_1.setBounds(6, 389, 450, 98);
				getContentPane().add(panel_1);
				panel_1.setLayout(null);
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(0, 0, 450, 99);
				panel_1.add(scrollPane_1);
				scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				
						userTextArea = new JTextArea();
						scrollPane_1.setViewportView(userTextArea);
						userTextArea.setLineWrap(true);
						userTextArea.setWrapStyleWord(true);
						userTextArea.setEditable(true);
						statusLabel = new JLabel();
						statusLabel.setBounds(6, 509, 155, 16);
						getContentPane().add(statusLabel);
						statusLabel.setFocusable(false);
						statusLabel.setFont(new Font("Dialog", Font.BOLD, 11));

	}
}

