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
	private static String msg;
	private static Charset cs = Charset.forName("UTF-8");
	private static int port;
	private static NIOSocket socket;
	static byte[] content;

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
		try {

			port = 5674;
			EventMachine machine = new EventMachine();
			socket = machine.getNIOService().openSocket("localhost", port);
			socket.setPacketReader(new AsciiLinePacketReader());
			socket.setPacketWriter(new AsciiLinePacketWriter());
			machine.start();
			window.chatTextEditor.setText("Welcome to LGI chat!");
			socket.listen(new SocketObserver() {

				@Override
				public void packetSent(NIOSocket socket, Object tag) {

				}

				@Override
				public void packetReceived(NIOSocket socket, byte[] packet) {
					// new message!!
					try {
						msg = new String(packet, cs);
						//int index1 = window.chatTextEditor.getText().lastIndexOf(window.chatTextEditor.getText());
						String setT = String.join("\n",window.chatTextEditor.getText(), msg);
						
						window.chatTextEditor.setText(setT); 														// it

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}

				@Override
				public void connectionOpened(NIOSocket nioSocket) {
					window.statusLabel.setForeground(Color.green);
					window.statusLabel.setText("Connected");

				}

				@Override
				public void connectionBroken(NIOSocket nioSocket, Exception exception) {
					window.statusLabel.setForeground(Color.red);
					window.statusLabel.setText("Connection broken");
					socket.close();

				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
		}
		window.userTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		window.userTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isControlDown()) {
					if (!window.userTextArea.getText().equals("")) {
						socket.write(window.userTextArea.getText()
								.substring(0, window.userTextArea.getText().length() - 1).getBytes(cs));

						window.userTextArea.setText("");

					} else {
						window.userTextArea.setText("");
					}

				}
			}
		});

	}

}
