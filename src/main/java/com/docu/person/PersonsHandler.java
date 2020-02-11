package com.docu.person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import com.docu.main.DEFS;

public class PersonsHandler {
	private static PersonsHandler _this = null;
	HashMap<String, Person> _mapPersons = new HashMap<>();

	public static PersonsHandler getInstance() {
		if(_this == null) {
			_this = new PersonsHandler();
		}

		return _this;
	}

	public PersonsHandlerResult addPerson(Person newPerson) {
		PersonsHandlerResult result = new PersonsHandlerResult();
		String id = newPerson.getId();

		if(_mapPersons.containsKey(id)) {
			Person existingPerson = _mapPersons.get(id);

			String name = newPerson.getName();
			String surname = newPerson.getSurname();
			String occupation = newPerson.getOccupation();
			String email = newPerson.getEmail();
			String city = newPerson.getCity();
			String postcode = newPerson.getPostcode();
			String address1 = newPerson.getAddress1();
			String address2 = newPerson.getAddress2();

			if(name != null && !name.isEmpty()) {
				existingPerson.setName(name);
			}
			if(surname != null && !surname.isEmpty()) {
				existingPerson.setSurname(surname);
			}
			if(occupation != null && !occupation.isEmpty()) {
				existingPerson.setOccupation(occupation);
			}
			if(email != null && !email.isEmpty()) {
				existingPerson.setEmail(email);
			}
			if(city != null && !city.isEmpty()) {
				existingPerson.setCity(city);
			}
			if(postcode != null && !postcode.isEmpty()) {
				existingPerson.setPostcode(postcode);
			}
			if(address1 != null && !address1.isEmpty()) {
				existingPerson.setAddress1(address1);
			}
			if(address2 != null && !address2.isEmpty()) {
				existingPerson.setAddress2(address2);
			}

			result.setAction(DEFS.ACTION_UPDATED);
		} else {
			_mapPersons.put(id, newPerson);

			result.setAction(DEFS.ACTION_NEW);
		}

		return result;
	}

	public String toCsv() {
		// Create a set to ensure the correct order
		Set<String> fields = new LinkedHashSet<>();

		fields.add(DEFS.FIELD_ID);
		fields.add(DEFS.FIELD_NAME);
		fields.add(DEFS.FIELD_SURNAME);
		fields.add(DEFS.FIELD_EMAIL);
		fields.add(DEFS.FIELD_OCCUPATION);
		fields.add(DEFS.FIELD_POSTCODE);
		fields.add(DEFS.FIELD_CITY);
		fields.add(DEFS.FIELD_ADDRESS_1);
		fields.add(DEFS.FIELD_ADDRESS_2);

		// Print header
		StringBuilder sb = new StringBuilder();

		for(String field : fields) {
			if(sb.length() > 0) {
				sb.append(DEFS.CSV_DELIMITER);
			}

			sb.append(field);
		}

		sb.append(DEFS.CRLF);

		// Print persons
		for(String id : _mapPersons.keySet()) {
			Person person = _mapPersons.get(id);
			sb.append(person.toCsv(fields));
			sb.append(DEFS.CRLF);
		}

		return sb.toString();
	}

	public void writeFileCsv() {
		String output = toCsv();
		FileWriter csvWriter = null;

		try {
			File file = new File(DEFS.FILE_CSV_OUTPUT);
			file.getParentFile().mkdirs();
			csvWriter = new FileWriter(file, false);
			csvWriter.append(output);

			csvWriter.flush();
		} catch(IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				csvWriter.close();
			} catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
