package com.docu.main;

import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

import com.docu.person.PersonsHandler;
import com.docu.database.DatabaseHelper;
import com.docu.rest.RestServiceHelper;

public class MergePersons {
	/**
	 * Process:
	 * - Read database properties
	 * - Open DB connection
	 * - Read persons-table and store data in persons-objects
	 * - Read REST properties
	 * - Read data from Rest service
	 * - Update persons objects
	 * - Write to CSV
	 */
	public static void main(String[] args) {
		// Read properties
		Optional<Properties> optionalDatabaseProperties = DatabaseHelper.readDatabaseProperties();

		if(optionalDatabaseProperties.isPresent()) {
			System.out.println("Database properties read.");
			Properties databaseProperties = optionalDatabaseProperties.get();

			// Open DB connection
			Optional<Connection> optionalConnection = DatabaseHelper.openDatabaseConnection(databaseProperties);

			if(optionalConnection.isPresent()) {
				System.out.println("Connection to database opened.");
				Connection conn = optionalConnection.get();

				// Read persons-table and store data in persons-objects
				System.out.println("Read persons from database and store temporarily.");
				DatabaseHelper.getAndStorePersonsTable(conn);

				// Read data from Rest service
				Optional<Properties> optionalRestProperties = RestServiceHelper.readRestServiceProperties();

				if(optionalRestProperties.isPresent()) {
					System.out.println("REST service properties read.");
					Properties restProperties = optionalRestProperties.get();

					// Read data from Rest service
					Optional<String> optionalRestData = RestServiceHelper.readRestData(restProperties);

					if(optionalRestData.isPresent()) {
						System.out.println("Data from REST service received.");
						String content = optionalRestData.get();

						// Update persons objects
						System.out.println("Parse data from REST service and store temporarily.");
						RestServiceHelper.getAndStorePersonsData(content);

						System.out.println("At this point, the data from the database and the REST service is joined.");

						System.out.println("Write the joined data.");
						PersonsHandler.getInstance().writeFileCsv();
					}
				}
			}
		}
	}

}
