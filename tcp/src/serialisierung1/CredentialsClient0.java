package serialisierung1;

import java.io.OutputStream;
import java.net.Socket;

public class CredentialsClient0 {
	public static void main(String[] args) throws Exception {
		Credentials c = new Credentials("arthur", "geheim42");
		try (Socket clientSocket = new Socket("localhost", 2016)) {
			OutputStream out = clientSocket.getOutputStream();
			out.write(c.login.getBytes());
			out.write(c.password.getBytes());
		}
	}
}
