package com.safetynet.safetynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "firestations", "medicalrecords" })
public class Person {
    
    private List<Persons> listPersons;
    
    public List<Persons> getPersons() {
        return listPersons;
    }
    
    public void setPersons(List<Persons> persons) {
        this.listPersons = persons;
    }
    
    @JsonIgnore
    public String getPersonsFirstName() {
        return ((Persons) listPersons).getFirstName();
    }
    
}
