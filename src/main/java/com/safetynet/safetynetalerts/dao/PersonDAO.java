package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface PersonDAO {
    
    public Person findAll();
    
    public List<Person> listFindAll();
    
    public Person findById(String firstName);
    
    public List<Person> save(Person person);
    
    public void deleteById(String firstName);
    
}
