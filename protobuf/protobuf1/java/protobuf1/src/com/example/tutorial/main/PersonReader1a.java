package com.example.tutorial.main;

import java.net.Socket;
import java.util.Arrays;

import com.example.tutorial.AddressBookProtos.Person;

public class PersonReader1a {
	public static void main(String[] args) throws Exception {
		byte[] rawdata = new byte[0];
		try (Socket clientSocket = new Socket("localhost", 2016)) {
			int len = 0;
			while (len != -1) {
				final int bufsize = 3;
				byte[] buf = new byte[bufsize];
				len = clientSocket.getInputStream().read(buf);
				if (len == -1) {
					// End of data reached
					break;
				} else if(len == 0) {
					// No data available, but end not yet reached
					continue;
				}
				int newlen = rawdata.length + len;
				byte[] newrawdata = Arrays.copyOf(rawdata, newlen);
				System.arraycopy(buf, 0, newrawdata, rawdata.length, len);
				rawdata = newrawdata;
			}
		}
		System.out.format("Client: Number of bytes received:%d%n", rawdata.length);
		Person p = Person.parseFrom(rawdata);
		System.out.format("Client: Received person data%n%s%n", p);
	}
}
