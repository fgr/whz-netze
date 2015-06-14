package serialisierung1;

import java.io.OutputStream;
import java.net.Socket;

public class CredentialsClient2 {
	private static byte[] serializeStringAsCharachters(String str) {
		int len = str.length();
		String lenstr = Integer.toString(len);
		int lenlen = lenstr.length();
		if (lenlen > 9) {
			throw new IllegalArgumentException("Cannot handle strings with more than 10^9 characters!");
		}

		byte[] bytes = new byte[1 + lenlen + len];
		bytes[0] = (byte) (lenlen + '0');
		System.arraycopy(lenstr.getBytes(), 0, bytes, 1, lenlen);
		System.arraycopy(str.getBytes(), 0, bytes, lenlen + 1, len);

		// for debugging
		System.out.format("String '%s' was serialized in our custom format as '", str);
		for (byte b : bytes) {
			System.out.print((char) b);
		}
		System.out.println("'");
		// debugging end

		return bytes;
	}

	public static void main(String[] args) throws Exception {
		Credentials c = new Credentials("arthur", "geheim42");

		try (Socket clientSocket = new Socket("localhost", 2016)) {
			OutputStream out = clientSocket.getOutputStream();
			out.write(serializeStringAsCharachters(c.login));
			out.write(serializeStringAsCharachters(c.password));
		}
	}
}
