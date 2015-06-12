package maze.server.com;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import maze.common.com.Config;
import maze.common.model.CellConnectionDirection;
import maze.server.model.Cell;
import maze.server.model.Maze;
import maze.server.model.impl.MazeBuilder;

/** A <em>brain-dead</em> global-state maze server. */
public class MazeServer1 {
	public static void main(String[] args) throws IOException {
		Maze maze = buildMaze();

		// Java NIO statt ServerSocket!
		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.socket().bind(new InetSocketAddress(Config.MAZE_SERVER_TCP_PORT));

			while (true) {
				// 0. wait for client to connect
				SocketChannel c = serverSocketChannel.accept();
				// 1. send current cell to the client which just connected
				sendCellToClient(maze.currentCell(), c);
				// 2. close connection
				c.close();
			}

		}
	}

	private static Maze buildMaze() {
		return MazeBuilder.INSTANCE//
				.firstRow()//
				.cell("A").cell("B")//
				//
				.nextRow()//
				.cell("C").cell("D")//
				//
				.nextRow()//
				.cell("E").cell("F")//
				//
				.connectCells()//
				.connect("A", "B")//
				.connect("A", "C")//
				.connect("C", "E")//
				.connect("B", "D")//
				.connect("D", "C")//
				.connect("E", "F")//
				//
				.entry("A")//
				.exit("F")//
				//
				.buildMaze();
	}

	private static void sendCellToClient(Cell cell, SocketChannel c) throws IOException {
		ByteBuffer b = ByteBuffer.allocate(4096);
		// ensure network byte order
		b.order(ByteOrder.BIG_ENDIAN);

		// write cell's ID (attention: this is a hack since we use char instead
		// of string!)
		char id = cell.id().toCharArray()[0];
		b.putChar(id);

		// write cell's name (attention: may be null!)

		// write cell's neighbors

		// send data to client
		b.flip();
		while (b.hasRemaining())
			c.write(b);

		// sender
		{
			CellConnectionDirection d;
			d.ordinal();
		}
		{
			// receiver
			int o = 0;
			CellConnectionDirection d //
			= CellConnectionDirection.values()[0];
		}
	}
}
