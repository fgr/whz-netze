package com.example.tutorial.main;

import java.net.Socket;

import com.example.tutorial.AddressBookProtos.Person;

public class PersonReader1 {	
	public static void main(String[] args) throws Exception {
		try (Socket clientSocket = new Socket("localhost", 2016)) {
			Person p = Person.parseFrom(clientSocket.getInputStream());
			System.out.println("Client: Received person data\n" + p);
		}
	}
}
