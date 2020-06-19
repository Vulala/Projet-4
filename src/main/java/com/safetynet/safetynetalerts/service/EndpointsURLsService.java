package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.dao.MedicalRecordDAO;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@Service
public class EndpointsURLsService {

	// Service layer for the EndpointsURLsController

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private FirestationDAO firestationDAO;
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private List<Person> listPersons;
	private List<MedicalRecord> listMedicalRecords;
	private Firestation firestations;
	private long adults;
	private long children;
	private List<Long> age;

	public void showPersonsByFirestation(int stationNumber) {
		Logger.info("Service handling : /firestation{stationNumber}");

		AgeCalculator ageCalculator = new AgeCalculator();
		List<Person> listPersonLocal = new ArrayList<Person>();
		for (Firestation firestation : firestationDAO.findAddressByStation(stationNumber)) {
			List<Person> listPerson2 = personDAO.findByAddress(firestation.getAddress());
			listPersonLocal.addAll(listPerson2);

			List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
			for (Person person : listPerson2) {
				MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
				listMedicalRecords.add(medicalRecord);
				ageCalculator.calculateDate(medicalRecord.getBirthdate());
			}
		}

		adults = ageCalculator.getAdults();
		children = ageCalculator.getChildren();
		listPersons = listPersonLocal;
		Logger.info("Service successfully handled : /firestation{stationNumber}");
	}

	public void showChildrenByAddress(String address) {
		Logger.info("Service handling : /childAlert{address}");

		List<Person> listPersonLocal = new ArrayList<Person>();
		List<Person> listPerson2 = personDAO.findByAddress(address);
		listPersonLocal.addAll(listPerson2);

		AgeCalculator ageCalculator = new AgeCalculator();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		for (Person person : listPerson2) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecords.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		age = ageCalculator.getListAge();
		children = ageCalculator.getChildren();
		listPersons = listPersonLocal;
		Logger.info("Service successfully handled : /childAlert{address}");
	}

	public void showPhoneNumbersByFirestation(int firestation) {
		Logger.info("Service handling : /phoneAlert{firestation}");

		List<Person> listPersonLocal = new ArrayList<Person>();
		for (Firestation firestation1 : firestationDAO.findAddressByStation(firestation)) {
			listPersonLocal.addAll(personDAO.findByAddress(firestation1.getAddress()));
		}

		listPersons = listPersonLocal;
		Logger.info("Service successfully handled : /phoneAlert{firestation}");
	}

	public void showPersonsByAddress(String address) {
		Logger.info("Service handling : /fire{address}");
		Firestation firestationLocal = firestationDAO.findById(address);

		List<Person> listPersonLocal = new ArrayList<Person>();
		List<Person> listPerson2 = personDAO.findByAddress(firestationLocal.getAddress());
		listPersonLocal.addAll(listPerson2);

		AgeCalculator ageCalculator = new AgeCalculator();
		List<MedicalRecord> listMedicalRecordsLocal = new ArrayList<MedicalRecord>();
		for (Person person : listPerson2) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecordsLocal.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		age = ageCalculator.getListAge();
		listPersons = listPersonLocal;
		listMedicalRecords = listMedicalRecordsLocal;
		firestations = firestationLocal;
		Logger.info("Service successfully handled : /fire{address}");
	}

	public void showPersonsAddressByFirestation(int stations) {
		Logger.info("Service handling : /flood/stations{stations}");

		AgeCalculator ageCalculator = new AgeCalculator();
		List<Person> listPersonLocal = new ArrayList<Person>();
		for (Firestation firestation : firestationDAO.findAddressByStation(stations)) {
			List<Person> listPerson2 = personDAO.findByAddress(firestation.getAddress());
			listPersonLocal.addAll(listPerson2);
		}

		List<MedicalRecord> listMedicalRecordsLocal = new ArrayList<MedicalRecord>();
		for (Person person : listPersonLocal) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecordsLocal.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		age = ageCalculator.getListAge();
		listPersons = listPersonLocal;
		listMedicalRecords = listMedicalRecordsLocal;
		Logger.info("Service successfully handled : /flood/stations{stations}");
	}

	public void showPersonInfoByPerson(String firstName, String lastName) {
		Logger.info("Service handling : /personInfo{firstName}{lastName}");

		List<Person> listPersonLocal = new ArrayList<Person>();
		List<Person> listPerson2 = personDAO.findByLastName(lastName);
		listPersonLocal.addAll(listPerson2);

		AgeCalculator ageCalculator = new AgeCalculator();
		List<MedicalRecord> listMedicalRecordsLocal = new ArrayList<MedicalRecord>();
		for (Person person : listPerson2) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecordsLocal.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		age = ageCalculator.getListAge();
		listPersons = listPersonLocal;
		listMedicalRecords = listMedicalRecordsLocal;
		Logger.info("Service successfully handled : /personInfo{firstName}{lastName}");
	}

	public void showMailsByCity(String city) {
		Logger.info("Service handling : /communityEmail{city}");
		List<Person> listPersonLocal = personDAO.findEmailByCity(city);

		listPersons = listPersonLocal;
		Logger.info("Service successfully handled : /communityEmail{city}");
	}

	public List<Person> getListPersons() {
		return listPersons;
	}

	public List<MedicalRecord> getListMedicalRecords() {
		return listMedicalRecords;
	}

	public Firestation getFirestations() {
		return firestations;
	}

	public long getAdults() {
		return adults;
	}

	public long getChildren() {
		return children;
	}

	public List<Long> getAge() {
		return age;
	}

}
