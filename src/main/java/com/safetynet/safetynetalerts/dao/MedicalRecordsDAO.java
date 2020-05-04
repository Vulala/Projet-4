package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface MedicalRecordsDAO {
    
    public List<MedicalRecord> findAll();
    
    public List<MedicalRecord> findById(String firstName);
    
    public List<MedicalRecord> save(MedicalRecord medicalRecord);
    
    public void deleteById(String firstName);
}
