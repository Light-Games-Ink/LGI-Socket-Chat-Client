/**
 * https://www.youtube.com/watch?v=ML_IjppR_SU
 */
package ru.lgi.main;

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
		try {

			port = 5674;
			EventMachine machine = new EventMachine();
			socket = machine.getNIOService().openSocket("localhost", port);
			socket.setPacketReader(new AsciiLinePacketReader());
			socket.setPacketWriter(new AsciiLinePacketWriter());
			machine.start();
			socket.listen(new SocketObserver() {

				@Override
				public void packetSent(NIOSocket socket, Object tag) {

				}

				@Override
				public void packetReceived(NIOSocket socket, byte[] packet) {
					// new message!!
					try {
						msg = new String(packet, cs);
						window.chatTextEditor.setText(window.chatTextEditor.getText() + msg); // check
																								// it

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}

				@Override
				public void connectionOpened(NIOSocket nioSocket) {
					window.statusLabel.setText("Connected");

				}

				@Override
				public void connectionBroken(NIOSocket nioSocket, Exception exception) {
					JOptionPane.showMessageDialog(null, "Connection broken");

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
