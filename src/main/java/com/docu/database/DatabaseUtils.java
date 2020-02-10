package com.docu.database;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DatabaseUtils {

	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost:5432/docucom_database";
	private String user = "postgres";
	private String password = "";


	/**
	 * Tries to return a valid connection to a database defined by the parameters
	 *
	 * @param driver
	 * 		name of the driver
	 * @param url
	 * 		connection string
	 * @param user
	 * @param password
	 * @return connection to the database
	 *
	 * @throws Exception
	 * 		if either any driver or SQL exception occurred
	 */
	public static Connection getDbConnection(final String driver, final String url, final String user,
			final String password) throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}

	public Connection getDbConnection() throws Exception {
		return getDbConnection(this.driver, this.url, this.user, this.password);
	}
}
