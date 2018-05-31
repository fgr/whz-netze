package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient1 {
	public static void main(String args[]) throws Exception {
		// BufferedReader inFromUser = new BufferedReader(new
		// InputStreamReader(System.in));

		DatagramSocket clientSocket = new DatagramSocket();
		// InetAddress serverIpAddress = InetAddress.getByName("localhost");
		InetAddress serverIpAddress = InetAddress.getByName("141.32.20.176");

		// String sentence = "Test from client"; // inFromUser.readLine();
		// byte[] sendData = sentence.getBytes();
		byte[] sendData = new byte[65_000];
		DatagramPacket sendPacket = //
				new DatagramPacket(sendData, sendData.length, serverIpAddress, 9876);
		clientSocket.send(sendPacket);

		// for (int i = 0;; i++) {
		// byte[] sendData = ("Paket #" + i).getBytes();
		//
		// DatagramPacket sendPacket = //
		// new DatagramPacket(sendData, sendData.length, serverIpAddress, 9876);
		// clientSocket.send(sendPacket);
		// }

		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);

		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		clientSocket.close();
	}
}
