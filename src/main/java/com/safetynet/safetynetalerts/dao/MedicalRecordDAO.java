package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.MedicalRecord;

public interface MedicalRecordDAO {

	/**
	 * Interface used to define CRUD operations for the type {@link MedicalRecord}
	 */

	public List<MedicalRecord> findAll();

	public MedicalRecord findById(String firstNameAndlastName);

	public List<MedicalRecord> save(MedicalRecord medicalRecord);

	public MedicalRecord update(String firstNameAndlastName, MedicalRecord medicalRecord);

	public void deleteById(String firstNameAndlastName);
}
