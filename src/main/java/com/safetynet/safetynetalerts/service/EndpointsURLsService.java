package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import com.safetynet.safetynetalerts.controller.EndpointsURLsController;
import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.dao.MedicalRecordDAO;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.object.EndpointsURLsObject;

@Service
public class EndpointsURLsService {

	/**
	 * Service used to interact with the DAO layer.<br>
	 * <br>
	 * This {@link EndpointsURLsService} is specific for the requests from the
	 * {@link EndpointsURLsController}.<br>
	 * It get the requests from the {@link EndpointsURLsController} and return the
	 * data into a specific {@link EndpointsURLsObject}.<br>
	 * <br>
	 * This service make use of the three DAO interfaces. {@link PersonDAO},
	 * {@link FirestationDAO}, {@link MedicalRecordDAO}.<br>
	 * It also use the {@link AgeCalculator} class to retrieve the age of a person
	 * and the number of children, adults.<br>
	 * <br>
	 */

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private FirestationDAO firestationDAO;
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	public EndpointsURLsObject showPersonsByFirestation(int stationNumber) {
		Logger.info("Service handling : /firestation{stationNumber}");

		AgeCalculator ageCalculator = new AgeCalculator();
		List<Person> listPersonLocal = new ArrayList<Person>();
		for (Firestation firestation : firestationDAO.findAddressByStation(stationNumber)) {
			List<Person> listPerson2 = personDAO.findByAddress(firestation.getAddress());
			listPersonLocal.addAll(listPerson2);

			for (Person person : listPerson2) {
				MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
				ageCalculator.calculateDate(medicalRecord.getBirthdate());
			}
		}

		Logger.info("Service successfully handled : /firestation{stationNumber}");
		return new EndpointsURLsObject(listPersonLocal, ageCalculator.getAdults(), ageCalculator.getChildren());
	}

	public EndpointsURLsObject showChildrenByAddress(String address) {
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

		Logger.info("Service successfully handled : /childAlert{address}");
		return new EndpointsURLsObject(listPersonLocal, listMedicalRecords, ageCalculator.getListAge(),
				ageCalculator.getChildren());
	}

	public EndpointsURLsObject showPhoneNumbersByFirestation(int firestation) {
		Logger.info("Service handling : /phoneAlert{firestation}");

		List<Person> listPersonLocal = new ArrayList<Person>();
		for (Firestation firestation1 : firestationDAO.findAddressByStation(firestation)) {
			listPersonLocal.addAll(personDAO.findByAddress(firestation1.getAddress()));
		}

		Logger.info("Service successfully handled : /phoneAlert{firestation}");
		return new EndpointsURLsObject(listPersonLocal);
	}

	public EndpointsURLsObject showPersonsByAddress(String address) {
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

		Logger.info("Service successfully handled : /fire{address}");
		return new EndpointsURLsObject(listPersonLocal, listMedicalRecordsLocal, firestationLocal,
				ageCalculator.getListAge());
	}

	public EndpointsURLsObject showPersonsAddressByFirestation(int stations) {
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

		Logger.info("Service successfully handled : /flood/stations{stations}");
		return new EndpointsURLsObject(listPersonLocal, listMedicalRecordsLocal, ageCalculator.getListAge());
	}

	public EndpointsURLsObject showPersonInfoByPerson(String firstName, String lastName) {
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

		Logger.info("Service successfully handled : /personInfo{firstName}{lastName}");
		return new EndpointsURLsObject(listPersonLocal, listMedicalRecordsLocal, ageCalculator.getListAge());
	}

	public EndpointsURLsObject showMailsByCity(String city) {
		Logger.info("Service handling : /communityEmail{city}");
		List<Person> listPersonLocal = personDAO.findEmailByCity(city);

		Logger.info("Service successfully handled : /communityEmail{city}");
		return new EndpointsURLsObject(listPersonLocal);
	}

}
