package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.Firestation;

public interface FirestationDAO {

	/**
	 * Interface used to define CRUD operations for the type {@link Firestation}
	 */

	public List<Firestation> findAll();

	public Firestation findById(String firestationAddress);

	public List<Firestation> save(Firestation firestation);

	public Firestation update(String firestationAddress, Firestation firestation);

	public void deleteById(String firestationAddress);

}
