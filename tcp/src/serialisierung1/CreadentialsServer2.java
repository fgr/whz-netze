package serialisierung1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CreadentialsServer2 {
	private static String deserializseStringFromCharachters(InputStream in) throws IOException {
		int lenlen = in.read();
		if (lenlen == -1)
			throw new IOException("Stream is closed!");
		lenlen -= '0';
		if (lenlen > 9) {
			throw new IOException("Cannot handle strings with more than 10^9 characters!");
		}

		byte[] lenbuf = new byte[lenlen];
		int bytesread = in.read(lenbuf);
		if (bytesread != lenlen) {
			throw new IOException(String.format("Expected to read %d bytes of data, but got %d.", lenlen, bytesread));
		}

		String lenstr = new String(lenbuf);
		int len = Integer.parseInt(lenstr);

		byte[] strbuf = new byte[len];
		bytesread = in.read(strbuf);
		if (bytesread == -1) {
			throw new IOException("End of stream reached before string data was read.");
		}
		while (bytesread < len) {
			int bytesread2 = in.read(strbuf, bytesread - 1, len - bytesread - 1);
			if (bytesread2 == -1) {
				break;
			}
			bytesread += bytesread2;
		}
		if (bytesread != len) {
			throw new IOException(String.format("Expected to read %d bytes of data, but got %d.", len, bytesread));
		}
		String str = new String(strbuf);
		return str;
	}

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(2016)) {
			System.out.println("Server: Waiting for TCP connection on port " //
					+ serverSocket.getLocalPort());
			try (Socket s = serverSocket.accept()) {
				System.out.println("Server: Connection established on server port " //
						+ s.getPort());

				InputStream in = s.getInputStream();
				String login = deserializseStringFromCharachters(in);
				String password = deserializseStringFromCharachters(in);

				Credentials c = new Credentials(login, password);
				System.out.println("Server: Read creadenials " + c);
			}
		}
	}
}
