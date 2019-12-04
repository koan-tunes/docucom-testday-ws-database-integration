package com.docu.database;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Arrays;

import com.docu.database.utils.CsvUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class Main {

	public static void main(String args[]) throws Exception {
		CsvUtils csvUtils = new CsvUtils();
		csvUtils.writeCsv();
//		try {
//			Connection con = new DatabaseUtils().getDbConnection();
//			System.out.println(con.getCatalog());
//		} catch(Exception e) {
//			System.err.println(e);
//		}

	}
}
