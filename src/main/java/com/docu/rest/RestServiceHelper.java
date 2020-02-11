package com.docu.rest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

import com.docu.main.DEFS;
import com.docu.person.Person;
import com.docu.person.PersonUtils;
import com.docu.person.PersonsHandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestServiceHelper {

	public static Optional<Properties> readRestServiceProperties() {
		try {
			InputStream input = new FileInputStream(DEFS.FILE_REST_SERVICE_PROPERTIES);

			Properties prop = new Properties();
			prop.load(input);

			return Optional.of(prop);
		} catch(FileNotFoundException e) {
			System.err.println(e.getMessage());

			return Optional.empty();
		} catch(IOException e) {
			System.err.println(e.getMessage());

			return Optional.empty();
		}
	}

	public static Optional<String> readRestData(Properties prop) {
		HttpURLConnection conn = null;
		BufferedReader in = null;
		StringBuilder content = new StringBuilder();

		try {
			URL url = new URL(prop.getProperty(DEFS.PROP_REST_URL) + "/" + DEFS.REST_TABLE_NAME_PERSONS);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");

			int status = conn.getResponseCode();

			if(status == 200) {
				in = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				String inputLine;
				while((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
			}

			conn.disconnect();
		} catch(IOException | IllegalArgumentException e) {
			System.err.println(e.getMessage());
		} finally {
			if(conn != null) {
				conn.disconnect();
			}
			if(in != null) {
				try {
					in.close();
				} catch(IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}

		if(content != null && content.length() > 0) {
			return Optional.of(content.toString());
		} else {
			return Optional.empty();
		}
	}

	public static void getAndStorePersonsData(String content) {
		JsonElement elements = new JsonParser().parse(content);
		JsonArray array = elements.getAsJsonArray();
		PersonsHandler handler = PersonsHandler.getInstance();

		for(JsonElement element : array) {
			JsonObject object = element.getAsJsonObject();

			String id = object.get(DEFS.FIELD_ID).getAsString();
			String name = object.get(DEFS.FIELD_NAME).getAsString();
			String surname = object.get(DEFS.FIELD_SURNAME).getAsString();
			String occupation = object.get(DEFS.FIELD_OCCUPATION).getAsString();

			Person person = new Person();
			person.setId(id).setName(name).setSurname(surname).setOccupation(occupation);

			PersonUtils.addPerson(person);
		}
	}
}
