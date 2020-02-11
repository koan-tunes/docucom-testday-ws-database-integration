package com.docu.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Properties;

import com.docu.person.Person;
import com.docu.person.PersonsHandler;
import com.docu.main.DEFS;
import com.docu.person.PersonUtils;

public class DatabaseHelper {

	public static Optional<Properties> readDatabaseProperties() {
		try {
			InputStream input = new FileInputStream(DEFS.FILE_DATABASE_PROPERTIES);

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

	public static Optional<Connection> openDatabaseConnection(Properties prop) {
		String driver = prop.getProperty(DEFS.PROP_DATABASE_DRIVER);
		String url = prop.getProperty(DEFS.PROP_DATABASE_URL);
		String user = prop.getProperty(DEFS.PROP_DATABASE_USER);
		String password = prop.getProperty(DEFS.PROP_DATABASE_PASSWORD);

		if(!driver.isEmpty() && !url.isEmpty() && !user.isEmpty()) {
			try {
				Connection conn = DatabaseUtils.getDbConnection(driver, url, user, password);

				return Optional.of(conn);
			} catch(Exception e) {
				System.err.println(e.getMessage());

				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}

	public static void getAndStorePersonsTable(Connection conn) {
		Statement statement = null;
		ResultSet set = null;
		Optional<ResultSet> resultTable = null;

		try {
			statement = conn.createStatement();
			set = statement.executeQuery("SELECT * FROM docucom_schema.person");
			storePersonsFromDatabase(set);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if(statement != null) {
				try {
					statement.close();
				} catch(SQLException e) {
					System.err.println(e.getMessage());
				}
			}
			if(set != null) {
				try {
					set.close();
				} catch(SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	private static void storePersonsFromDatabase(ResultSet setPersons) {
		try {
			PersonsHandler handler = PersonsHandler.getInstance();

			while(setPersons.next()) {
				String id = setPersons.getString(DEFS.FIELD_ID_DATABASE);
				String email = setPersons.getString(DEFS.FIELD_EMAIL);
				String city = setPersons.getString(DEFS.FIELD_CITY);
				String postcode = setPersons.getString(DEFS.FIELD_POSTCODE);
				String address1 = setPersons.getString(DEFS.FIELD_ADDRESS_1);
				String address2 = setPersons.getString(DEFS.FIELD_ADDRESS_2);

				Person person = new Person();
				person.setId(id)
						.setEmail(email)
						.setCity(city)
						.setPostcode(postcode)
						.setAddress1(address1)
						.setAddress2(address2);

				PersonUtils.addPerson(person);
			}
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
