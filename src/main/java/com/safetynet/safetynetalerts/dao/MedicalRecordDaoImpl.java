package com.safetynet.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.JSONReader;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDAO {

	/**
	 * Implementation of the interface {@link MedicalRecordDAO} used to define
	 * operations for the type {@link MedicalRecord}.
	 * 
	 * @Method findAll() is used to get all the medical records present in the
	 *         data.json file, return type: {@link List}<{@link MedicalRecord}>.
	 * @Method findById() is used to return a specific {@link MedicalRecord} if
	 *         present in the data.json file, else return null.
	 * @Method findByFirstName() is used to return a specific {@link MedicalRecord}
	 *         if present in the data.json file, else return null.
	 * @Method findByLastName() is used to return a specific
	 *         {@link List}<{@link MedicalRecord}> depending of the parameter.
	 * @Method save() is used to add a new medical record, return type:
	 *         {@link List}<{@link MedicalRecord}>.
	 * @Method update() is used to update a specific medical record, return type:
	 *         {@link MedicalRecord}.
	 * @Method delete() is used to delete a specific medical record, return type:
	 *         void.
	 */

	private List<MedicalRecord> medicalRecords;

	public MedicalRecordDaoImpl(List<MedicalRecord> medicalRecord) throws Exception {
		super();
		medicalRecord = new JSONReader().getData().getMedicalRecords();
		this.medicalRecords = medicalRecord;
	}

	@Override
	public List<MedicalRecord> findAll() {
		return medicalRecords;
	}

	@Override
	public MedicalRecord findById(String firstNameAndlastName) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstNameAndlastName().equals(firstNameAndlastName)) {
				return medicalRecord;
			}
		}
		return null;
	}

	@Override
	public MedicalRecord findByFirstName(String firstName) {
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equals(firstName)) {
				return medicalRecord;
			}
		}
		return null;
	}

	@Override
	public List<MedicalRecord> findByLastName(String lastName) {
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getLastName().equals(lastName)) {
				listMedicalRecords.add(medicalRecord);
			}
		}
		return listMedicalRecords;
	}

	@Override
	public List<MedicalRecord> save(MedicalRecord medicalRecord) {
		List<MedicalRecord> saveMedicalRecord = medicalRecords;
		saveMedicalRecord.add(medicalRecord);
		return saveMedicalRecord;
	}

	@Override
	public MedicalRecord update(String firstNameAndlastName, MedicalRecord medicalRecord) {
		for (MedicalRecord updateMedicalRecord : medicalRecords) {
			if (updateMedicalRecord.getFirstNameAndlastName().equals(firstNameAndlastName)) {
				updateMedicalRecord.setBirthdate(medicalRecord.getBirthdate());
				updateMedicalRecord.setMedications(medicalRecord.getMedications());
				updateMedicalRecord.setAllergies(medicalRecord.getAllergies());
				return updateMedicalRecord;
			}
		}
		return null;
	}

	@Override
	public void deleteById(String firstNameAndlastName) {
		List<MedicalRecord> deleteMedicalRecord = medicalRecords;
		deleteMedicalRecord
				.removeIf(medicalRecord -> medicalRecord.getFirstNameAndlastName().equals(firstNameAndlastName));

	}

}
