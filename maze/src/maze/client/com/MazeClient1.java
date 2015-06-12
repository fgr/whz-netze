package maze.client.com;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

import maze.common.com.CellDto;
import maze.common.com.Config;

/**
 * A maze client connecting to the <em>brain-dead</em> global-state maze server.
 */
public class MazeClient1 {
	public static void main(String[] args) throws IOException {
		final String mazeServerIP = "127.0.0.1";
		
		// Java NIO statt Socket!
		try (SocketChannel socketChannel = SocketChannel.open()) {
			socketChannel.connect(new InetSocketAddress(mazeServerIP, Config.MAZE_SERVER_TCP_PORT));

			System.out.println(readCellFromServer(socketChannel));
		}
	}

	private static CellDto readCellFromServer(SocketChannel socketChannel) throws IOException {
		ByteBuffer b = ByteBuffer.allocate(4096);
		b.clear();
		// ensure network byte order
		b.order(ByteOrder.BIG_ENDIAN);

		// put data received from socket into ByteBuffer
		int bytesRead = socketChannel.read(b);
		System.out.println("received bytes: " + bytesRead);
		
		b.position(0);
		// read cell's ID
		System.out.println("received ID '" + b.getChar() + "'.");

		// read cell's name (attention: may be null!)

		// read cell's neighbors
		
		return null;
	}
}
