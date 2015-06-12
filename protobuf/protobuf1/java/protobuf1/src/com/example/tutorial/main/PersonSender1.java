package com.example.tutorial.main;

import java.net.ServerSocket;
import java.net.Socket;

import com.example.tutorial.AddressBookProtos.Person;

public class PersonSender1 {
	public static void main(String[] args) throws Exception {
		Person john = //
		Person.newBuilder() //
				.setId(1234) //
				.setName("John Doe") //
				.setEmail("jdoe@example.com") //
				.addPhone( //
						Person.PhoneNumber.newBuilder() //
								.setNumber("555-4321") //
								.setType(Person.PhoneType.HOME)) //
				.build();

		// for (;;)
		{
			try (ServerSocket serverSocket = new ServerSocket(2016)) {
				System.out.println("Server: Waiting for TCP connection on port " //
						+ serverSocket.getLocalPort());
				try (Socket s = serverSocket.accept()) {
					System.out.println("Server: Connection established on server port " //
							+ s.getPort());
					john.writeTo(s.getOutputStream());
					System.out.println("Server: Number of bytes sent " //
							+ john.toByteArray().length);
					System.out.println("Server: Sent data to client; closing the connection.");
				}
			}
		}
	}
}
