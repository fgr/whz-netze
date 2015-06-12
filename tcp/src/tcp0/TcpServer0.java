package tcp0;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TcpServer0 {
	public static void main(String[] args) throws Exception {
		int backlog = 0;
		try (ServerSocket serverSocket = new ServerSocket(2015, backlog)) {
			System.out.println("Server: Waiting for TCP connection on port " //
					+ serverSocket.getLocalPort());

			for (;;) {
				try (Socket clientSocket = serverSocket.accept()) {
					System.out.println("Server: connection established on server port " //
							+ clientSocket.getLocalPort());
					System.out.println("Server:                           client port " //
							+ clientSocket.getPort());

					Thread.sleep(TimeUnit.SECONDS.toMillis(1));

					String data = new Date().toString();
					byte[] rawdata = data.getBytes();
					clientSocket.getOutputStream().write(rawdata);

					System.out.println("Server: Number of bytes sent: " + rawdata.length);
				}
			}
		}
	}
}

// Übung 2: mit Pause -> Backlog = 0
// -> Multi-Server?

// Übung 3: Hello -> World

// Übung 4: Hello mal X -> X-mal World
