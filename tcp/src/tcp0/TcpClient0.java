package tcp0;

import java.net.Socket;

public class TcpClient0 {
	public static void main(String[] args) throws Exception {
		try (Socket clientSocket = new Socket("localhost", 2015)) {

			byte[] rawdata = new byte[1024];
			int numBytesRcvd = clientSocket.getInputStream().read(rawdata);
			String data = new String(rawdata, 0, numBytesRcvd);
			System.out.printf("Client: Number of bytes received from server: %d%n", numBytesRcvd);
			System.out.printf("Client: Received data from server '%s'%n", data);
		}
	}
}
