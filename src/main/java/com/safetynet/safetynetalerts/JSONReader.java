package com.safetynet.safetynetalerts;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Person;

public class JSONReader {
    
    private static Person persons;
    @JsonIgnore
    private static List<Person> listPersons;
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static File file = new File("src/main/resources/data.json");
    
    public static void readJSON() throws Exception {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        Person person = objectMapper.readValue(file, Person.class);
        List<Person> listPerson = objectMapper.readValue(file, new TypeReference<List<Person>>() {
        });
        System.out.println("JSONReader OK");
        persons = person;
        listPersons = listPerson;
        
    }
    
    public Person getPersons() {
        return persons;
    }
    
    public static void setPersons(Person persons) {
        JSONReader.persons = persons;
    }
    
    public List<Person> getListPersons() {
        return listPersons;
    }
    
    public static void setListPersons(List<Person> listPersons) {
        JSONReader.listPersons = listPersons;
    }
}