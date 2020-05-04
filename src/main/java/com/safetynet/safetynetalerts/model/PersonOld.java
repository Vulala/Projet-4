package com.safetynet.safetynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "firestations", "medicalrecords" })
public class PersonOld {
    
    private List<Person> listPersons;
    
    public List<Person> getListPersons() {
        return listPersons;
    }
    
    public void setListPersons(List<Person> person) {
        this.listPersons = person;
    }
    
}
