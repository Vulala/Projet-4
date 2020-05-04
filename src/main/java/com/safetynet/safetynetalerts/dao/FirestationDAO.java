package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.Firestation;

public interface FirestationDAO {
    
    public List<Firestation> findAll();
    
    public List<Firestation> findById(String address);
    
    public List<Firestation> save(Firestation firestation);
    
    public void deleteById(String address);
    
}
