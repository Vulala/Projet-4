package com.safetynet.safetynetalerts;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.model.Firestation;

public class FirestationDAOTest {

	private FirestationDaoImpl firestationDaoImpl;
	private List<Firestation> listFirestations;

	@BeforeEach
	public void setUp() throws Exception {
		firestationDaoImpl = new FirestationDaoImpl(listFirestations);
	}

	@Test
	public void findAll() {
		// ARRANGE
		List<Firestation> listFirestation; // method return value
		// ACT
		listFirestation = firestationDaoImpl.findAll();
		// ASSERT
		assertThat(listFirestation.toString(), containsString("1509 Culver St"));
	}

	@Test
	public void findbyId() {
		// ARRANGE
		Firestation firestation = new Firestation(); // method return value
		String firestationAddress = "1509 Culver St"; // parameter
		// ACT
		firestation = firestationDaoImpl.findById(firestationAddress);
		// ASSERT
		assertThat(firestation.getAddress(), containsString("1509 Culver St"));
		assertThat(firestation.getAddress(), not(containsString("112 Steppes Pl")));
	}

	@Test
	public void findAddressByStation() {
		// ARRANGE
		List<Firestation> listFirestation; // method return value
		int station = 3; // parameter
		// ACT
		listFirestation = firestationDaoImpl.findAddressByStation(station);
		// ASSERT
		assertThat(listFirestation.toString(), containsString("1509 Culver St"));
	}

	@Test
	public void save() {
		// ARRANGE
		Firestation firestation = new Firestation(); // parameter
		firestation.setAddress("AAAA");
		firestation.setStation(0);
		// ACT
		listFirestations = firestationDaoImpl.save(firestation);
		// ASSERT
		assertThat(listFirestations.toString(), containsString("AAAA"));
		assertThat(listFirestations.toString(), containsString("0"));
	}

	@Test
	public void update() {
		// ARRANGE
		String firestationAddress = "1509 Culver St"; // parameter
		Firestation firestation = new Firestation(); // parameter
		firestation.setAddress("AAAA");
		firestation.setStation(0);
		// ACT
		firestationDaoImpl.update(firestationAddress, firestation);
		// ASSERT
		listFirestations = firestationDaoImpl.findAll();
		assertThat(listFirestations.toString(), containsString("0"));
		assertThat(listFirestations.toString(), not(containsString("AAAA")));
	}

	@Test
	public void deleteById() {
		// ARRANGE
		String firestationAddress = "1509 Culver St"; // parameter
		List<Firestation> listFirestation = new ArrayList<Firestation>();
		// ACT
		firestationDaoImpl.deleteById(firestationAddress);
		// ASSERT
		listFirestation = firestationDaoImpl.findAll();
		assertThat(listFirestation.toString(), not(containsString("1509 Culver St")));
	}
}
