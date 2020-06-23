package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.object.FirestationCRUDObject;

@Service
public class FirestationCRUDService {

	/**
	 * Service used to interact with the DAO layer
	 */

	@Autowired
	private FirestationDAO firestationDAO;

	public FirestationCRUDObject findAll() {
		List<Firestation> listFirestations = new ArrayList<Firestation>();
		listFirestations = firestationDAO.findAll();

		return new FirestationCRUDObject(listFirestations);
	}

	public FirestationCRUDObject findById(String address) {
		Firestation firestation = new Firestation();
		firestation = firestationDAO.findById(address);

		return new FirestationCRUDObject(firestation);
	}

	public FirestationCRUDObject save(Firestation firestation) {
		List<Firestation> listFirestation = firestationDAO.save(firestation);
		return new FirestationCRUDObject(listFirestation);
	}

	public FirestationCRUDObject update(String address, Firestation firestation) {
		Firestation firestationUpdated = firestationDAO.update(address, firestation);
		return new FirestationCRUDObject(firestationUpdated);
	}

	public void deleteById(String address) {
		firestationDAO.deleteById(address);
	}
}