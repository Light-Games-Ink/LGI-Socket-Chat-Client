/**
 * 
 */
package ru.lgi.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

/**
 * @author LaughingMaan
 *
 */
class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

	@Override
	public void completed(Integer result, Attachment attach) {
		if (attach.isRead) {
			attach.buffer.flip();
			Charset cs = Charset.forName("UTF-8");
			int limits = attach.buffer.limit();
			byte[] bytes = new byte[limits];
			attach.buffer.get(bytes, 0, limits);
			String msg = new String(bytes, cs);
			System.out.format("Server responded: " + msg);
			try {
				msg = this.getTextFromUser();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (msg.equalsIgnoreCase("bye")) {
				attach.mainThread.interrupt();
				return;
			}
			attach.buffer.clear();
			byte[] data = msg.getBytes();
			attach.buffer.put(data);
			attach.buffer.flip();
			attach.isRead = false;
			attach.channel.write(attach.buffer, attach, this);
		} else {
			attach.isRead = true;
			attach.buffer.clear();
			attach.channel.read(attach.buffer, attach, this);
		}
	}

	private String getTextFromUser() throws Exception {
		System.out.println("Please enter a message (Bye to quit):");
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		String msg = consoleReader.readLine();
		return msg;
	}

	@Override
	public void failed(Throwable exc, Attachment attach) {

	}

}
