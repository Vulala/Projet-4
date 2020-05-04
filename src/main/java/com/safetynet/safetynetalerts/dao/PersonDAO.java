package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.JSONDataObject;
import com.safetynet.safetynetalerts.model.Person;

public interface PersonDAO {
    
    public List<Person> findAll();
    
    public JSONDataObject findById(String firstName);
    
    public List<Person> save(Person person);
    
    public void deleteById(String firstName);
    
}
