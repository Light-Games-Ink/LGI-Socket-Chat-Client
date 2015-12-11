/**
 * 
 */
package ru.lgi.main;

import java.awt.Cursor;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.bind.attachment.AttachmentMarshaller;

/**
 * @author LaughingMaan
 *
 */
public class Main {
	static MainWindow window;
	static String msg;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		window = new MainWindow();
		window.setVisible(true);
		
		for(int i = 0; i<1000;i++){
			window.userTextArea.append("1");
		}
		/*try {
			AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
			SocketAddress serverAddr = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
			Future<Void> result = channel.connect(serverAddr);
			result.get();
			window.JLabel1.setText("Connected"); // change to label
			Attachment attach = new Attachment();
			attach.channel = channel;
			attach.buffer = ByteBuffer.allocate(2048);
			attach.isRead = false;
			attach.mainThread = Thread.currentThread();

			Charset cs = Charset.forName("UTF-8");
			msg = "Helllo";
			byte[] data = msg.getBytes(cs);
			attach.buffer.put(data);
			attach.buffer.flip();

			ReadWriteHandler readWriteHandler = new ReadWriteHandler();
			channel.write(attach.buffer, attach, readWriteHandler);
			attach.mainThread.join();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}*/
		window.userTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(!window.userTextArea.getText().equals("")){
						msg = window.userTextArea.getText();
						window.chatTextArea.append(msg);
						window.userTextArea.setText("");
					}
					else{
						window.userTextArea.setText("");
					}
					
					
					//window.usersList.setText(window.userTextArea.getText());
				}
			}
		});
		;
	}

}
