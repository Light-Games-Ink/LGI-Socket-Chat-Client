/**
 * https://www.youtube.com/watch?v=ML_IjppR_SU
 */
package ru.lgi.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;

import naga.NIOService;
import naga.NIOSocket;
import naga.SocketObserverAdapter;

/**
 * @author LaughingMaan
 *
 */
public class Main {
	static MainWindow window;
	static String msg;
	//private static Attachment attach;
	//private static ReadWriteHandler readWriteHandler;
	private static Charset cs = Charset.forName("UTF-8");;
	//private static AsynchronousSocketChannel channel;
	private static int port;
	private static NIOSocket socket;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		window = new MainWindow();
		window.setVisible(true);
		try { 
			port = 5676;
			NIOService service = new NIOService();
			socket = service.openSocket("localhost", port);
			socket.isOpen();
			if(socket.socket().isConnected()){window.statusLabel.setText("Connected");}
			else{window.statusLabel.setText("Unable to connect");}
			socket.listen(new SocketObserverAdapter(){
				@Override 
				public void packetReceived(NIOSocket socket, byte[] packet) {
					//Check the packet, then made a broadcast
					msg = new String(packet,cs);
					window.chatTextEditor.setText(window.chatTextEditor.getText() + msg + " End of message");
					
					
			}});
			

			/*channel = AsynchronousSocketChannel.open();
			SocketAddress serverAddr = new InetSocketAddress("localhost", 5678);
			Future<Void> result = channel.connect(serverAddr);
			result.get();
			window.statusLabel.setText("Connected"); // change to label
			attach = new Attachment();
			attach.channel = channel;
			attach.buffer = ByteBuffer.allocate(2048);
			attach.isRead = false;
			attach.mainThread = Thread.currentThread();*/

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
						msg = window.userTextArea.getText();
						//window.chatTextArea.setText(window.chatTextArea.getText() + msg);
						
						if(!socket.write(msg.getBytes())){
							JOptionPane.showMessageDialog(null, "Queque error!");
						}
						window.userTextArea.setText("");
					} else {
						window.userTextArea.setText("");
					}

					// window.usersList.setText(window.userTextArea.getText());
				}
			}
		});
		
	}

	/*private static void sendMsg(String msg) {
		cs = Charset.forName("UTF-8");
		msg = window.chatTextArea.getText();
		window.userTextArea.setText("");
		byte[] data = msg.getBytes(cs);
		attach.buffer.put(data);
		attach.buffer.flip();
		readWriteHandler = new ReadWriteHandler();
		channel.write(attach.buffer, attach, readWriteHandler);
		try {
			attach.mainThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

}
