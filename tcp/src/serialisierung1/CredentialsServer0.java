package serialisierung1;

import java.net.ServerSocket;
import java.net.Socket;

public class CredentialsServer0 {
	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(2016)) {
			System.out.println("Server: Waiting for TCP connection on port " //
					+ serverSocket.getLocalPort());
			try (Socket s = serverSocket.accept()) {
				System.out.println("Server: Connection established on server port " //
						+ s.getPort());

				byte buf[] = new byte[1024];
				int len = s.getInputStream().read(buf);
				String login = new String(buf, 0, len);

				len = s.getInputStream().read(buf);
				String password = (len == -1) ? null : new String(buf, 0, len);

				Credentials c = new Credentials(login, password);
				System.out.println("Server: Read creadenials " + c);
			}
		}
	}
}
