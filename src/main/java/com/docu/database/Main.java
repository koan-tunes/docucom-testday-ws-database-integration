package com.docu.database;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class Main {

//	private String getAllUsers = "select * from person";
//	private String url = "jdbc:postgresql://localhost:5432/docucom_database";
//	private String user = "postgres";
//	private String password = "";

	public static void main(String args[]) throws Exception {
		System.out.printf("test");

//		try {
//			Connection con = new DatabaseUtils().getDbConnection();
//			System.out.println(con.getCatalog());
//		} catch(Exception e) {
//			System.err.println(e);
//		}

		UserService userService = new UserService();

		System.out.println(userService.getUsers().get(1).getName());


		String csvFile = "/resources/users.csv";
//		FileWriter writer = new FileWriter(csvFile);


		JsonNode jsonTree = new ObjectMapper().readTree(new URL(Utils.url));
		System.out.println(jsonTree.toString());

		Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
		CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

		CsvMapper csvMapper = new CsvMapper();
		csvMapper.writerFor(JsonNode.class)
				.with(csvSchema)
				.writeValue(new File("src/main/resources/users.csv"), jsonTree);


	}
}
