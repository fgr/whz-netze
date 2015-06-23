package tls1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * 
 * {@link TlsClient2}s verwenden nur den Trust Store (aber keinen Key Store), da
 * sie sich gegenüber {@link TlsServer2} nicht authentifizieren muessen.
 * 
 * Für TLS-Debug-Ausgaben folgendes als Argumente an JVM übergeben:
 * -Djavax.net.debug=ssl,handshake
 * 
 * @author Frank Grimm
 *
 */
public class TlsClient2 {

	public static void main(String args[]) throws Exception {
		String hostname = "localhost";
		int port = 1234;

		// Trust Store laden
		InputStream trustStoreIS = new FileInputStream("keys/myclienttruststore.jks");
		char[] trueStorePassphrase = "clientpw".toCharArray();
		KeyStore ksTrust = KeyStore.getInstance("JKS");
		ksTrust.load(trustStoreIS, trueStorePassphrase);

		// TrustManager initialisieren
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(ksTrust);

		// SSLContext mit TrustManagern initialisieren
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null /* Keine KeyManager */, tmf.getTrustManagers(), null);

		// Get your own custom SSLSocketFactory
		SSLSocketFactory sf = sslContext.getSocketFactory();

		try (SSLSocket secureSocket = (SSLSocket) (sf.createSocket(hostname, port))) {
			// Wir sind die Client-Seite der Verbindung
			secureSocket.setUseClientMode(true);

			// Die erlaubten Protokolle festlegen
			secureSocket.setEnabledProtocols(secureSocket.getSupportedProtocols());
			secureSocket.setEnabledCipherSuites(secureSocket.getSupportedCipherSuites());

			DataInputStream din = new DataInputStream(secureSocket.getInputStream());

			// Ab hier sicher lesen und schreiben
			String msg = din.readUTF();
			System.out.println("Empfangene Nachricht: " + msg);
		}
	}
}
