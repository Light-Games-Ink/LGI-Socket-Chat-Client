/**
 * https://www.youtube.com/watch?v=ML_IjppR_SU
 */
package ru.lgi.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import naga.NIOSocket;
import naga.SocketObserver;
import naga.eventmachine.EventMachine;
import naga.packetreader.AsciiLinePacketReader;
import naga.packetwriter.AsciiLinePacketWriter;

/**
 * @author LaughingMaan
 *
 */
public class Main {
	static MainWindow window;
	static SettingsWindow settings = new SettingsWindow();
	private static String msg, host;
	private static Charset cs = Charset.forName("windows-1251");
	private static int port;
	private static NIOSocket socket;
	static byte[] content;
	static EventMachine machine;
	static SocketObserver observer;
	static boolean colorFlag = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		window = new MainWindow();
		window.setVisible(true);
		window.settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.setVisible(true);
			}
		});

		host = "127.0.0.1";
		port = 5674;

		window.chatTextEditor.setText("<h2>Welcome to LGI chat!</h2>");
		configureConnection();

		window.userTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isControlDown()) {
					sendFromForm(true);
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()){
					window.userTextArea.setText(window.userTextArea.getText()+"\n");
				}

			}

		});
		window.reconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				configureConnection();
			}
		});
		window.sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendFromForm(false);
			}
		});
		settings.applyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				socket.write(("&C" + getHexColor(settings.selectedColor.getBackground().darker())).getBytes(cs));
				settings.dispose();
			}
			
		});
	}

	protected static void sendFromForm(boolean isEnter) {
		if(isEnter)
			sendPacket(window.userTextArea.getText().substring(0, window.userTextArea.getText().length() - 1));
		else sendPacket(window.userTextArea.getText().substring(0, window.userTextArea.getText().length()));
		
		window.userTextArea.setText("");

	}

	public static void configureConnection() {
		try {
			machine = new EventMachine();
			socket = machine.getNIOService().openSocket(host, port);
			socket.setPacketReader(new AsciiLinePacketReader());
			socket.setPacketWriter(new AsciiLinePacketWriter());
			machine.start();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
		}

		socket.listen(new SocketObserver() {

			@Override
			public void packetSent(NIOSocket socket, Object tag) {

			}

			@SuppressWarnings("unchecked")
			@Override
			public void packetReceived(NIOSocket socket, byte[] packet) {
				// new message!!
				try {
					msg = new String(packet, cs);
					if (msg.contains("&ULR")) {
						// user list request;
						String[] users = msg.split("&ULR");
						window.listModel.clear();
						for (String user : users) {
							window.listModel.addElement(user);
						}
						// window.usersList.setListData(users);
					} else if (msg.contains("logged in.") || msg.contains("left the chat")) {
						socket.write("&ULR".getBytes(cs));
					} else {
						window.chatTextEditor.setText(window.chatTextEditor.getText().substring(0,
								window.chatTextEditor.getText().length() - 18) + "\n" + msg + "<br></body></html>");
						window.chatTextEditor.setCaretPosition(window.chatTextEditor.getDocument().getLength());
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

			@Override
			public void connectionOpened(NIOSocket nioSocket) {
				window.statusLabel.setForeground(Color.green);
				window.statusLabel.setText("Connected");
				window.reconnectButton.setEnabled(false);

			}

			@Override
			public void connectionBroken(NIOSocket nioSocket, Exception exception) {
				window.statusLabel.setForeground(Color.red);
				window.statusLabel.setText("Connection broken");
				window.reconnectButton.setEnabled(true);
				window.listModel.clear();
				socket.close();

			}
		});
	}

	public static void sendPacket(String msg) {
		if (!msg.equals("")) {
				socket.write(msg.getBytes(cs));
		}
	}

	/**
	 * @author alee
	 *
	 */
	public static String getHexColor(Color color) {
		final String hex = Integer.toHexString(color.getRGB()).toUpperCase();
		return "#" + hex.substring(2, hex.length());
	}
}
