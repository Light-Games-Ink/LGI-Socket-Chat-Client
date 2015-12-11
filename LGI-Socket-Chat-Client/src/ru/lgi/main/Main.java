/**
 * 
 */
package ru.lgi.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.bind.attachment.AttachmentMarshaller;

/**
 * @author LaughingMaan
 *
 */
public class Main {
	MainWindow window;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
			SocketAddress serverAddr = new InetSocketAddress(args[0], Integer.parseInt(args[1]));
			Future<Void> result = channel.connect(serverAddr);
			result.get();
			System.out.println("Conected"); // change to label
			Attachment attach = new Attachment();
			attach.channel = channel;
			attach.buffer = ByteBuffer.allocate(2048);
			attach.isRead = false;
			attach.mainThread = Thread.currentThread();

			Charset cs = Charset.forName("UTF-8");
			String msg = "Helllo";
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
		}

	}

}
