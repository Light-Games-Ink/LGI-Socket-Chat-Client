/**
 * 
 */
package ru.lgi.main;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @author LaughingMaan
 *
 */
class Attachment {
	AsynchronousSocketChannel channel;
	ByteBuffer buffer;
	Thread mainThread;
	boolean isRead;
}
