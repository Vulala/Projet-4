package com.safetynet.safetynetalerts.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.JSONReader;
import com.safetynet.safetynetalerts.model.Firestation;

@Repository
public class FirestationDaoImpl implements FirestationDAO {

	/**
	 * Implementation of the interface {@link FirestationDAO} used to define CRUD
	 * operations for the type {@link Firestation}.
	 * 
	 * @Method findAll() is used to get all the firestations present in the
	 *         data.json file, return type: {@link List}<{@link Firestation}>.
	 * @Method findById() is used to return a specific {@link Firestation} if
	 *         present in the data.json file, else return null.
	 * @Method save() is used to add a new firestation, return type:
	 *         {@link List}<{@link Firestation}>.
	 * @Method update() is used to update a specific firestation, return
	 *         type:{@link Firestation}.
	 * @Method delete() is used to delete a specific firestation, return type: void.
	 */

	private List<Firestation> firestations;

	public FirestationDaoImpl(List<Firestation> firestion) throws Exception {
		super();
		firestion = new JSONReader().getData().getFirestations();
		this.firestations = firestion;
	}

	@Override
	public List<Firestation> findAll() {
		return firestations;
	}

	@Override
	public Firestation findById(String firestationAddress) {
		for (Firestation address : firestations) {
			if ((address.getAddress()).equals(firestationAddress)) {
				return address;
			}
		}
		return null;
	}

	@Override
	public List<Firestation> save(Firestation firestation) {
		List<Firestation> saveFirestation = firestations;
		saveFirestation.add(firestation);
		return saveFirestation;
	}

	@Override
	public Firestation update(String firestationAddress, Firestation firestation) {
		for (Firestation updatefirestation : firestations) {
			if ((updatefirestation.getAddress()).equals(firestationAddress)) {
				updatefirestation.setStation(firestation.getStation());
				return updatefirestation;
			}
		}
		return null;
	}

	@Override
	public void deleteById(String firestationAddress) {
		List<Firestation> deleteFirestation = firestations;
		deleteFirestation.removeIf(firestation -> firestation.getAddress().equals(firestationAddress));
	}

}
