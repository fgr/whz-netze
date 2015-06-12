package com.example.tutorial.main;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.example.tutorial.AddressBookProtos.AddressBook;
import com.example.tutorial.AddressBookProtos.Person;

public class AddressBookWriter1 {
	// This function fills in a Person message based on user input.
	static Person PromptForAddress(BufferedReader stdin, PrintStream stdout) throws IOException {
		Person.Builder person = Person.newBuilder();

		stdout.print("Enter person ID: ");
		person.setId(Integer.valueOf(stdin.readLine()));

		stdout.print("Enter name: ");
		person.setName(stdin.readLine());

		stdout.print("Enter email address (blank for none): ");
		String email = stdin.readLine();
		if (email.length() > 0) {
			person.setEmail(email);
		}

		while (true) {
			stdout.print("Enter a phone number (or leave blank to finish): ");
			String number = stdin.readLine();
			if (number.length() == 0) {
				break;
			}

			Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber(number);

			stdout.print("Is this a mobile, home, or work phone? ");
			String type = stdin.readLine();
			if (type.equals("mobile")) {
				phoneNumber.setType(Person.PhoneType.MOBILE);
			} else if (type.equals("home")) {
				phoneNumber.setType(Person.PhoneType.HOME);
			} else if (type.equals("work")) {
				phoneNumber.setType(Person.PhoneType.WORK);
			} else {
				stdout.println("Unknown phone type.  Using default.");
			}

			person.addPhone(phoneNumber);
		}

		return person.build();
	}

	public static void main(String[] args) throws Exception {
		AddressBook.Builder addressBook = AddressBook.newBuilder();

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

		addressBook.addPerson(john);

		// Write the new address book back to disk.
		FileOutputStream output = new FileOutputStream("addressbook1");
		addressBook.build().writeTo(output);
		output.close();
	}
}
