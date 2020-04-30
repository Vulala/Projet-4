package com.safetynet.safetynetalerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.JSONReader;
import com.safetynet.safetynetalerts.model.Person;

@Repository
public class PersonDaoImpl implements PersonDAO {
    
    @Autowired
    private JSONReader jsonReader;
    
    @Override
    public Person findAll() {
        return jsonReader.getPersons();
    }
    
    @Override
    public List<Person> listFindAll() {
        return jsonReader.getListPersons();
    }
    
    @Override
    public Person findById(String firstName) {
        for (Person person : jsonReader.getListPersons()) {
            if ((person.getPersonsFirstName()).equals(firstName)) {
                return person;
                
            }
            
        }
        return null;
    }
    
    @Override
    public List<Person> save(Person person) {
        List<Person> savePerson = jsonReader.getListPersons();
        savePerson.add(person);
        return savePerson;
    }
    
    @Override
    public void deleteById(String firstName) {
        List<Person> deletePerson = jsonReader.getListPersons();
        deletePerson.removeIf(person -> person.getPersonsFirstName().equals(firstName));
    }
    
}
