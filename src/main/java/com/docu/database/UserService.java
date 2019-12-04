package com.docu.database;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

public class UserService {

	private JSONArray getUsersAsJSON() {
		try {
			return new JSONArray(IOUtils.toString(new URL(Utils.url), Charset.forName("UTF-8")));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		for(Object a : getUsersAsJSON()) {
			//wird mit db vergliechen und neue user erstellt
			users.add(this.readPerson(a.toString()));
		}
		return users;
	}

	private User readPerson(String json) {
		final User user;
		ObjectMapper mapper = new ObjectMapper();
		try {
			user = mapper.readValue(json, User.class);
			return user;
		} catch(Exception e) {
			System.err.println(e);
		}

		return null;
	}

}
