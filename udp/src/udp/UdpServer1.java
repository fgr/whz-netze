package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer1 {
	public static void main(String args[]) throws Exception {
		try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
			while (true) {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				
				String sentence = new String(receivePacket.getData());
				System.out.println("RECEIVED: " + sentence);

				
				InetAddress ipAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				
				String capitalizedSentence = sentence.toUpperCase();
				byte[] sendData = capitalizedSentence.getBytes();
				
				DatagramPacket sendPacket =//
						new DatagramPacket(sendData, sendData.length, ipAddress, port);
				serverSocket.send(sendPacket);
			}
		}
	}
}
