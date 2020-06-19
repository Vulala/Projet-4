package com.safetynet.safetynetalerts;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.dao.MedicalRecordDaoImpl;
import com.safetynet.safetynetalerts.model.MedicalRecord;

public class MedicalRecordDAOTest {

	private List<MedicalRecord> medicalRecords;
	private MedicalRecordDaoImpl medicalRecordImpl;

	@BeforeEach
	public void setUp() throws Exception {
		medicalRecordImpl = new MedicalRecordDaoImpl(medicalRecords);
	}

	@Test
	public void findAll() {
		// ARRANGE
		List<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>(); // method return value
		// ACT
		listMedicalRecord = medicalRecordImpl.findAll();
		// ASSERT
		assertThat(listMedicalRecord.toString(), containsString("John"));
	}

	@Test
	public void findId() {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord(); // method return value
		String firstNameAndlastName = "JohnBoyd"; // parameter
		// ACT
		medicalRecord = medicalRecordImpl.findById(firstNameAndlastName);
		// ASSERT
		assertThat(medicalRecord.getFirstName(), containsString("John"));
	}

	@Test
	public void findByFirstName() {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord(); // method return value
		String firstName = "John"; // parameter
		// ACT
		medicalRecord = medicalRecordImpl.findByFirstName(firstName);
		// ASSERT
		assertThat(medicalRecord.getFirstName(), containsString("John"));
	}

	@Test
	public void findByLastName() {
		// ARRANGE
		List<MedicalRecord> medicalRecord = new ArrayList<MedicalRecord>(); // method return value
		String lastName = "Boyd"; // parameter
		// ACT
		medicalRecord = medicalRecordImpl.findByLastName(lastName);
		// ASSERT
		assertThat(medicalRecord.toString(), containsString("John"));
		assertThat(medicalRecord.toString(), containsString("Allison"));
	}

	@Test
	public void save() {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord(); // parameter
		List<String> allergies = new ArrayList<String>();
		allergies.add("BBBB");
		medicalRecord.setFirstName("AAAA");
		medicalRecord.setAllergies(allergies);
		// ACT
		medicalRecordImpl.save(medicalRecord);
		// ASSERT
		medicalRecords = medicalRecordImpl.findAll();
		assertThat(medicalRecords.toString(), containsString("AAAA"));
		assertThat(medicalRecords.toString(), containsString("BBBB"));
	}

	@Test
	public void update() {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord(); // parameter
		String firstNameAndlastName = "JohnBoyd"; // parameter
		List<String> allergies = new ArrayList<String>();
		allergies.add("BBBB");
		medicalRecord.setFirstName("AAAA");
		medicalRecord.setAllergies(allergies);
		// ACT
		medicalRecordImpl.update(firstNameAndlastName, medicalRecord);
		// ASSERT
		medicalRecords = medicalRecordImpl.findAll();
		assertThat(medicalRecords.toString(), containsString("BBBB"));
		assertThat(medicalRecords.toString(), not(containsString("AAAA")));
	}

	@Test
	public void delete() {
		// ARRANGE
		String firstNameAndlastName = "JohnBoyd"; // parameter
		// ACT
		medicalRecordImpl.deleteById(firstNameAndlastName);
		// ASSERT
		medicalRecords = medicalRecordImpl.findAll();
		assertThat(medicalRecords.toString(), not(containsString("John")));
	}
}
