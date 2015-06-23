package tls1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TlsServer1 {

	public static void main(String[] args) throws IOException {
		int port = 1234;

		// Fuer TCP-Verbindungsaufbau eine normale TCP-Socket verwenden
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			Socket socket = serverSocket.accept();

			// Standard-SSLSocketFactory verwenden
			SSLSocketFactory sf = ((SSLSocketFactory) SSLSocketFactory.getDefault());

			// Nachdem Verbindung aufgebaut wurde, verschluesselte Verbindung
			// verwenden
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			SSLSocket s = (SSLSocket) (sf.createSocket(socket, remoteAddress.getHostName(), socket.getPort(), true));

			// Festlegen, dass diese Seite der Verbindung der Server ist
			s.setUseClientMode(false);

			// Festlegen, dass sich Client gegenueber Server authentifizieren
			// muss
			s.setNeedClientAuth(false);

			// Die erlaubten Protokolle festlegen
			s.setEnabledProtocols(s.getSupportedProtocols());
			s.setEnabledCipherSuites(s.getSupportedCipherSuites());

			// TLS-Handshake initialisieren
			s.startHandshake();

			// Socket-Variable durch SSLSocket ersetzen
			socket = s;

			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

			// Ab hier sicher lesen und schreiben
			dout.writeUTF("Eine geheime Nachricht.");
		}
	}

}
