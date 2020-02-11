package com.docu.person;

import com.docu.person.Person;
import com.docu.person.PersonsHandler;
import com.docu.person.PersonsHandlerResult;

public class PersonUtils {
	public static void addPerson(Person person) {
		PersonsHandler handler = PersonsHandler.getInstance();
		PersonsHandlerResult result = handler.addPerson(person);

		StringBuilder sb = new StringBuilder();
		if(result.isNew()) {
			sb.append("- Person added: ");
		} else {
			sb.append("- Person updated: ");
		}
		sb.append(person.getId());

		System.out.println(sb.toString());
	}
}
