package com.docu.person;

import java.util.HashMap;
import java.util.Set;

import com.docu.main.DEFS;

public class Person {

	private HashMap<String, String> _mapFields = new HashMap<>();

	public String getId() {
		return _mapFields.get(DEFS.FIELD_ID);
	}

	public Person setId(String id) {
		_mapFields.put(DEFS.FIELD_ID, id);
		return this;
	}

	public String getName() {
		return _mapFields.get(DEFS.FIELD_NAME);
	}

	public Person setName(String name) {
		_mapFields.put(DEFS.FIELD_NAME, name);
		return this;
	}

	public String getSurname() {
		return _mapFields.get(DEFS.FIELD_SURNAME);
	}

	public Person setSurname(String surname) {
		_mapFields.put(DEFS.FIELD_SURNAME, surname);
		return this;
	}

	public String getOccupation() {
		return _mapFields.get(DEFS.FIELD_OCCUPATION);
	}

	public Person setOccupation(String occupation) {
		_mapFields.put(DEFS.FIELD_OCCUPATION, occupation);
		return this;
	}

	public String getEmail() {
		return _mapFields.get(DEFS.FIELD_EMAIL);
	}

	public Person setEmail(String email) {
		_mapFields.put(DEFS.FIELD_EMAIL, email);
		return this;
	}

	public String getCity() {
		return _mapFields.get(DEFS.FIELD_CITY);
	}

	public Person setCity(String city) {
		_mapFields.put(DEFS.FIELD_CITY, city);
		return this;
	}

	public String getPostcode() {
		return _mapFields.get(DEFS.FIELD_POSTCODE);
	}

	public Person setPostcode(String postcode) {
		_mapFields.put(DEFS.FIELD_POSTCODE, postcode);
		return this;
	}

	public String getAddress1() {
		return _mapFields.get(DEFS.FIELD_ADDRESS_1);
	}

	public Person setAddress1(String address1) {
		_mapFields.put(DEFS.FIELD_ADDRESS_1, address1);
		return this;
	}

	public String getAddress2() {
		return _mapFields.get(DEFS.FIELD_ADDRESS_2);
	}

	public Person setAddress2(String address2) {
		_mapFields.put(DEFS.FIELD_ADDRESS_2, address2);
		return this;
	}

	public String toCsv(Set<String> fields) {
		StringBuilder sb = new StringBuilder();

		for(String field : fields) {
			if(sb.length() > 0) {
				sb.append(DEFS.CSV_DELIMITER);
			}

			String value = _mapFields.get(field);
			if(value == null) {
				value = "";
			}

			sb.append(value);
		}

		return sb.toString();
	}
}
