package com.safetynet.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Firestation;

@Repository
public class FirestationDaoImpl implements FirestationDAO {
    
    @Override
    public List<Firestation> findAll() {
        return null;
    }
    
    @Override
    public List<Firestation> findById(String address) {
        return null;
    }
    
    @Override
    public List<Firestation> save(Firestation firestation) {
        return null;
    }
    
    @Override
    public void deleteById(String address) {
        
    }
    
}
