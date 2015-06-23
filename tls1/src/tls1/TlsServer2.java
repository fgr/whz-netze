package tls1;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * Für TLS-Debug-Ausgaben folgendes als Argumente an JVM übergeben:
 * -Djavax.net.debug=ssl,handshake
 * 
 * @author Frank Grimm
 *
 */
public class TlsServer2 {
	public static void main(String[] args) throws Exception {
		// Key Store laden
		InputStream keyStoreIS = new FileInputStream("keys/myserverkeystore.jks");
		char[] keyStorePassphrase = "geheim123".toCharArray();
		KeyStore ksKeys = KeyStore.getInstance("JKS");
		ksKeys.load(keyStoreIS, keyStorePassphrase);

		// KeyManagerFactory inialisieren
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		char[] keyManagerPassphrase = "ganzgeheim".toCharArray();
		kmf.init(ksKeys, keyManagerPassphrase);

		// SSLContext mit KeyManagern initialisieren
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null /* Keine TrustManager */, null);

		// SSLSocketFactory holen
		SSLSocketFactory sf = sslContext.getSocketFactory();

		// Port, an dem Server auf Verbindungen wartet
		int port = 1234;

		// Fuer TCP-Verbindungsaufbau eine normale TCP-Socket verwenden
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			Socket socket = serverSocket.accept();

			// Nachdem Verbindung aufgebaut wurde, verschluesselte Verbindung
			// verwenden
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			SSLSocket s = (SSLSocket) (sf.createSocket(socket, remoteAddress.getHostName(), socket.getPort(), true));

			// Festlegen, dass diese Seite der Verbindung der Server ist
			s.setUseClientMode(false);

			// Festlegen, dass sich Client nicht gegenueber Server
			// authentifizieren muss
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
