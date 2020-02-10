package com.docu.database.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class CsvUtils {

	private JsonNode getJsonTree() {
		JsonNode jsonTree = null;
		try {
			jsonTree = new ObjectMapper().readTree(new URL(Utils.url));
		} catch(IOException e) {
			System.err.printf("Mapping JsonTree failed: ", e);
		}

		return jsonTree;
	}

	public void writeCsv() {
		CsvMapper csvMapper = new CsvMapper();
		Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = this.getJsonTree().elements().next();
		firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
		CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

		try {
			csvMapper.writerFor(JsonNode.class)
					.with(csvSchema)
					.writeValue(new File(Utils.csvPath), this.getJsonTree());
		} catch(IOException e) {
			System.err.printf("Writing file failed: ", e);
		}
	}
}
