package com.safetynet.safetynetalerts;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.JSONDataObject;

public class JSONReader {
    
    // private static Person persons;
    @JsonIgnore
    private static List<JSONDataObject> jsonDataObject;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("src/main/resources/data.json");
    
    public static void readJSON() throws Exception {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        // Person person = objectMapper.readValue(file, Person.class);
        List<JSONDataObject> listJSONDataObject = objectMapper.readValue(file,
                new TypeReference<List<JSONDataObject>>() {
                });
        jsonDataObject = listJSONDataObject;
        System.out.println("JSONReader OK" + jsonDataObject);
    }
    
    /**
    public static void getListOfPersonsFromJson() {
        JsonFactory jsonFactory = objectMapper.getFactory();
        JsonParser jsonParser;
        try {
            jsonParser = jsonFactory.createParser(file);
            JsonNode node = objectMapper.readTree(jsonParser);
            int persons = 3;
            for (int i = 0; i < persons; i++) {
                System.out.println("persons" + node.get("listPersons").get(i).get("firstName").asText());
                
            }
            jsonParser.close();
            
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }*/
    /**  
    public Person getJSONPerson() {
        return persons;
    }
    
    public static void setPersons(Person person) {
        JSONReader.persons = person;
    }*/
    
    public List<JSONDataObject> getData() {
        return jsonDataObject;
    }
    
}