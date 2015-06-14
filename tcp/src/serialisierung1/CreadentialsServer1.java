package serialisierung1;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CreadentialsServer1 {
	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(2016)) {
			System.out.println("Server: Waiting for TCP connection on port " //
					+ serverSocket.getLocalPort());
			try (Socket s = serverSocket.accept()) {
				System.out.println("Server: Connection established on server port " //
						+ s.getPort());

				String login = "";
				String password = "";
				boolean loginRead = false;

				InputStream in = s.getInputStream();

				int b;
				while ((b = in.read()) != -1) {
					if (b == 0) {
						// Marker dividing password from login string read
						loginRead = true;
					} else {
						if (!loginRead)
							// append character to login
							login += (char) b;
						else {
							// append character to password
							password += (char) b;
						}
					}
				}

				Credentials c = new Credentials(login, password);
				System.out.println("Server: Read creadenials " + c);
			}
		}
	}
}
