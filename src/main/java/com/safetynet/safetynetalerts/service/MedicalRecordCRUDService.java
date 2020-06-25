package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.MedicalRecordCRUDController;
import com.safetynet.safetynetalerts.dao.MedicalRecordDAO;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.object.MedicalRecordCRUDObject;

@Service
public class MedicalRecordCRUDService {

	/**
	 * Service used to interact with the DAO layer.<br>
	 * <br>
	 * This {@link MedicalRecordCRUDService} is specific for the requests from the
	 * {@link MedicalRecordCRUDController}.<br>
	 * It get the requests from the {@link MedicalRecordCRUDController} and return
	 * the data into a specific {@link MedicalRecordCRUDObject}.<br>
	 * <br>
	 * This service make use of the {@link MedicalRecordDAO} interface.<br>
	 */

	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	public MedicalRecordCRUDObject findAll() {
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		listMedicalRecords = medicalRecordDAO.findAll();

		return new MedicalRecordCRUDObject(listMedicalRecords);
	}

	public MedicalRecordCRUDObject findById(String address) {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord = medicalRecordDAO.findById(address);

		return new MedicalRecordCRUDObject(medicalRecord);
	}

	public MedicalRecordCRUDObject save(MedicalRecord medicalRecord) {
		List<MedicalRecord> listMedicalRecord = medicalRecordDAO.save(medicalRecord);
		return new MedicalRecordCRUDObject(listMedicalRecord);
	}

	public MedicalRecordCRUDObject update(String address, MedicalRecord medicalRecord) {
		MedicalRecord medicalRecordUpdated = medicalRecordDAO.update(address, medicalRecord);
		return new MedicalRecordCRUDObject(medicalRecordUpdated);
	}

	public void deleteById(String address) {
		medicalRecordDAO.deleteById(address);
	}
}
