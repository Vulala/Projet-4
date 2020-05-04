package com.safetynet.safetynetalerts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.JSONReader;
import com.safetynet.safetynetalerts.model.JSONDataObject;
import com.safetynet.safetynetalerts.model.Person;

@Repository
public class PersonDaoImpl implements PersonDAO {
    
    @Autowired
    private JSONReader jsonReader; // /!\ Un seul accès au JSONReader, au début de
                                   // l'application. Ensuite traitement de l'objet
                                   // retourné, plus aucun autre accès à la classe. Doit
                                   // retourner une liste.
    private List<JSONDataObject> jsonDataObject;
    
    @Override
    public List<Person> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public JSONDataObject findById(String firstName) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<Person> save(Person person) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void deleteById(String firstName) {
        // TODO Auto-generated method stub
        
    }
    
    /**
    @Override
    public List<Person> findAll() {
        return jsonDataObject;
    }
    
    
    @Override
    public JSONDataObject findById(String firstName) {
        for (JSONDataObject jsonData : jsonDataObject) {
            if ((jsonData.getFirstName()).equals(firstName)) {
                return jsonData;
                
            }
            
        }
        return null;
    }
    
    @Override
    public List<Person> save(Person person) {
        List<Person> savePerson = jsonDataObject.getJsonDataObject();
        savePerson.add(person);
        return savePerson;
    }
    
    @Override
    public void deleteById(String firstName) {
        List<Person> deletePerson = jsonReader.getJsonDataObject();
        deletePerson.removeIf(person -> person.getFirstName().equals(firstName));
    }*/
    
}
