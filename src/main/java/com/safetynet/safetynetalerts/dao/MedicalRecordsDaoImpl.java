package com.safetynet.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordsDaoImpl implements MedicalRecordsDAO {
    
    @Override
    public List<MedicalRecord> findAll() {
        return null;
    }
    
    @Override
    public List<MedicalRecord> findById(String firstName) {
        return null;
    }
    
    @Override
    public List<MedicalRecord> save(MedicalRecord medicalRecord) {
        return null;
    }
    
    @Override
    public void deleteById(String firstName) {
        
    }
    
}
