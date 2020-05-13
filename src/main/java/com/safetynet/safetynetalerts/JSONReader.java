package com.safetynet.safetynetalerts;

import java.io.File;

import org.tinylog.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.JSONDataObject;

public class JSONReader {

	/**
	 * {@link JSONReader} is used to read and deserialize a JSON file. The path file
	 * to the JSON file is set in the private {@link File} variable.
	 * 
	 * @return A {@link JSONDataObject} containing the JSON informations, accessible
	 *         with the getter : {@link getData}.
	 */

	private JSONDataObject jsonDataObject;
	private ObjectMapper objectMapper = new ObjectMapper();
	private File file = new File("src/main/resources/data.json");

	private JSONDataObject readJSON() throws Exception {
		JSONDataObject jsonDataObject = objectMapper.readValue(file, JSONDataObject.class);

		if (jsonDataObject != null) {
			Logger.info("<--- data.json file correctly deserialized");
			return jsonDataObject;
		} else {
			Logger.error("while trying to read the data.json file");
			return null;
		}

	}

	public JSONDataObject getData() throws Exception {
		Logger.info("Trying to read the data.json file --->");
		jsonDataObject = readJSON();
		return jsonDataObject;
	}

}