package com.safetynet.safetynetalerts.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.safetynetalerts.JSONReader;
import com.safetynet.safetynetalerts.model.Person;

@Component
public class JSONDataObject {
    
    private Person person;
    private Firestation firestation;
    private MedicalRecord medicalRecord;
    private JSONReader jsonReader;
    private List<JSONDataObject> jsonDataObject;// = jsonReader.getData();
    
    public JSONDataObject() {
    }
    
    public JSONDataObject(Person person, Firestation firestation, MedicalRecord medicalRecord) {
        this.person = person;
        this.firestation = firestation;
        this.medicalRecord = medicalRecord;
    }
    
    public List<JSONDataObject> jsonDataObject() {
        jsonDataObject.addAll(jsonReader.getData());
        return jsonDataObject;
    }
    
    public Person getPerson() {
        return person;
    }
    
    public Firestation getFirestation() {
        return firestation;
    }
    
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
    
    public String getAddress() {
        return firestation.getAddress();
    }
    
    public int getStation() {
        return firestation.getStation();
    }
    
    public String getFirstName() {
        return medicalRecord.getFirstName();
    }
    
    public String getLastName() {
        return medicalRecord.getLastName();
    }
    
    public String getBirthdate() {
        return medicalRecord.getBirthdate();
    }
    
    public String getMedications() {
        return medicalRecord.getMedications();
    }
    
    public String getAllergies() {
        return medicalRecord.getAllergies();
    }
    
    public String getCity() {
        return person.getCity();
    }
    
    public int getZip() {
        return person.getZip();
    }
    
    public String getPhone() {
        return person.getPhone();
    }
    
    public String getEmail() {
        return person.getEmail();
    }
    
    public List<JSONDataObject> getJsonDataObject() {
        return jsonDataObject;
    }
    
}