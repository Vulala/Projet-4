package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tinylog.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.dao.MedicalRecordDAO;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.AdultsAndChildren;
import com.safetynet.safetynetalerts.model.Age;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.AgeCalculator;
import com.safetynet.safetynetalerts.service.Filters;

@RestController
public class AppController {

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private FirestationDAO firestationDAO;
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	private ObjectMapper mapper = new ObjectMapper();

	// CRUD operations on /person

	@GetMapping(value = "/person")
	public String listPerson() throws JsonProcessingException {
		Logger.info("GET request of : /person");
		return mapper.writer(Filters.serializeAll).writeValueAsString(personDAO.findAll());
	}

	@GetMapping(value = "/person/{firstNameAndlastName}")
	public String showPersonById(@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("GET request of : /person/{firstNameAndlastName}");
		return mapper.writer(Filters.serializeAll).writeValueAsString(personDAO.findById(firstNameAndlastName));
	}

	@PostMapping(value = "/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		Logger.info("POST request on : /person");
		List<Person> savePerson = personDAO.save(person);
		if (person == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savePerson.get(1)).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/person/{firstNameAndlastName}")
	public String updatePerson(@RequestBody Person person, @PathVariable String firstNameAndlastName)
			throws JsonProcessingException {
		Logger.info("PUT request on : /person/{firstNameAndlastName}");
		return mapper.writer(Filters.serializeAll).writeValueAsString(personDAO.update(firstNameAndlastName, person));
	}

	@DeleteMapping(value = "/person/{firstNameAndlastName}")
	public void deletePerson(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /person/{firstNameAndlastName}");
		personDAO.deleteById(firstNameAndlastName);
	}

	// CRUD operations on /firestation

	@GetMapping(value = "/firestation/")
	public String listFirestation() throws JsonProcessingException {
		Logger.info("GET request of : /firestation");
		return mapper.writer(Filters.serializeAll).writeValueAsString(firestationDAO.findAll());
	}

	@GetMapping(value = "/firestation/{address}")
	public String showFirestationById(@PathVariable String address) throws JsonProcessingException {
		Logger.info("GET request of : /firestation/{address}");
		return mapper.writer(Filters.serializeAll).writeValueAsString(firestationDAO.findById(address));
	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		Logger.info("POST request on : /firestation");
		List<Firestation> saveFirestation = firestationDAO.save(firestation);
		if (firestation == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(saveFirestation.get(1)).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/firestation/{address}")
	public String updateFirestation(@RequestBody Firestation firestation, @PathVariable String address)
			throws JsonProcessingException {
		Logger.info("PUT request on : /firestation/{address}");
		return mapper.writer(Filters.serializeAll).writeValueAsString(firestationDAO.update(address, firestation));
	}

	@DeleteMapping(value = "/firestation/{address}")
	public void deleteFirestation(@PathVariable String address) {
		Logger.info("DELETE request of : /firestation/{address}");
		firestationDAO.deleteById(address);
	}

	// CRUD operations on /medicalRecord

	@GetMapping(value = "/medicalRecord")
	public String listMedicalRecord() throws JsonProcessingException {
		Logger.info("GET request of : /medicalRecord");
		return mapper.writer(Filters.serializeAll).writeValueAsString(medicalRecordDAO.findAll());
	}

	@GetMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public String showMedicalRecordById(@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("GET request of : /medicalRecord/{firstNameAndlastName}");
		return mapper.writer(Filters.serializeAll).writeValueAsString(medicalRecordDAO.findById(firstNameAndlastName));
	}

	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		Logger.info("POST request on : /medicalRecord");
		List<MedicalRecord> saveMedicalRecord = medicalRecordDAO.save(medicalRecord);
		if (medicalRecord == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(saveMedicalRecord.get(1))
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public String updateMedicalRecord(@RequestBody MedicalRecord medicalRecord,
			@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("PUT request on : /medicalRecord/{firstNameAndlastName}");
		return mapper.writer(Filters.serializeAll)
				.writeValueAsString(medicalRecordDAO.update(firstNameAndlastName, medicalRecord));
	}

	@DeleteMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public void deleteMedicalRecord(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /medicalRecord/{firstNameAndlastName}");
		medicalRecordDAO.deleteById(firstNameAndlastName);
	}

	// Endpoints URLs

	@GetMapping(value = "/firestation{stationNumber}")
	public String showPersonsByFirestation(@RequestParam(value = "stationNumber") int stationNumber)
			throws JsonProcessingException {
		Logger.info("GET request of : /firestation{stationNumber}");

		AgeCalculator ageCalculator = new AgeCalculator();
		List<Person> listPerson = new ArrayList<Person>();
		for (Firestation firestation : firestationDAO.findAddressByStation(stationNumber)) {
			List<Person> listPerson2 = personDAO.findPersonByAddress(firestation.getAddress());
			listPerson.addAll(listPerson2);

			List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
			for (Person person : listPerson2) {
				MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
				listMedicalRecords.add(medicalRecord);
				ageCalculator.calculateDate(medicalRecord.getBirthdate());
			}

		}
		long adults = ageCalculator.getAdults();
		long children = ageCalculator.getChildren();
		return mapper.writer(Filters.firestationStationNumberFilter).writeValueAsString(listPerson)
				+ mapper.writeValueAsString(new AdultsAndChildren(adults, children));
	}

	@GetMapping(value = "/childAlert{address}")
	public String showChildrenByAddress(@RequestParam(value = "address") String address)
			throws JsonProcessingException {
		Logger.info("GET request of : /childAlert{address}");

		List<Person> listPerson = new ArrayList<Person>();
		List<Person> listPerson2 = personDAO.findPersonByAddress(address);
		listPerson.addAll(listPerson2);

		AgeCalculator ageCalculator = new AgeCalculator();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		for (Person person : listPerson2) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecords.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}
		long children = ageCalculator.getChildren();
		if (children == 0) {
			return null;
		} else {
			List<Long> age = ageCalculator.getListAge();
			return mapper.writer(Filters.childAlertFilter).writeValueAsString(listPerson)
					+ mapper.writeValueAsString(new Age(age));
		}
	}

	@GetMapping(value = "/phoneAlert{firestation}")
	public String showPhoneNumbersByFirestation(@RequestParam(value = "firestation") int firestation)
			throws JsonProcessingException {
		Logger.info("GET request of : /phoneAlert{firestation}");

		List<Person> listPerson = new ArrayList<Person>();
		for (Firestation firestation1 : firestationDAO.findAddressByStation(firestation)) {
			listPerson.addAll(personDAO.findPersonByAddress(firestation1.getAddress()));
		}
		return mapper.writer(Filters.phoneAlertFilter).writeValueAsString(listPerson);
	}

	@GetMapping(value = "/fire{address}")
	public String showPersonsByAddress(@RequestParam(value = "address") String address) throws JsonProcessingException {
		Logger.info("GET request of : /fire{address}");
		Firestation firestation = firestationDAO.findById(address);

		List<Person> listPerson = new ArrayList<Person>();
		List<Person> listPerson2 = personDAO.findPersonByAddress(firestation.getAddress());
		listPerson.addAll(listPerson2);

		AgeCalculator ageCalculator = new AgeCalculator();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		for (Person person : listPerson2) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecords.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		List<Long> age = ageCalculator.getListAge();
		return mapper.writer(Filters.fireAddressFilter).writeValueAsString(firestation)
				+ mapper.writer(Filters.fireAddressFilter).writeValueAsString(listPerson)
				+ mapper.writer(Filters.fireAddressFilter).writeValueAsString(listMedicalRecords)
				+ mapper.writeValueAsString(new Age(age));
	}

	@GetMapping(value = "/flood/stations{stations}")
	public String showPersonsAddressByFirestation(@RequestParam(value = "stations") int stations)
			throws JsonProcessingException {
		Logger.info("GET request of : /flood/stations{stations}");

		AgeCalculator ageCalculator = new AgeCalculator();
		List<Person> listPerson = new ArrayList<Person>();
		for (Firestation firestation : firestationDAO.findAddressByStation(stations)) {
			List<Person> listPerson2 = personDAO.findPersonByAddress(firestation.getAddress());
			listPerson.addAll(listPerson2);
		}

		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		for (Person person : listPerson) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecords.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		List<Long> age = ageCalculator.getListAge();
		return mapper.writer(Filters.floodStationsFilter).writeValueAsString(listPerson)
				+ mapper.writer(Filters.floodStationsFilter).writeValueAsString(listMedicalRecords)
				+ mapper.writeValueAsString(new Age(age));
	}

	@GetMapping(value = "/personInfo{firstName}{lastName}")
	public String showPersonInfoByPerson(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) throws JsonProcessingException {
		Logger.info("GET request of : /personInfo{firstName}{lastName}");
		List<MedicalRecord> listMedicalRecord = medicalRecordDAO.findByLastName(lastName);

		List<Person> listPerson = new ArrayList<Person>();
		List<Person> listPerson2 = personDAO.findByLastName(lastName);
		listPerson.addAll(listPerson2);

		AgeCalculator ageCalculator = new AgeCalculator();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		for (Person person : listPerson2) {
			MedicalRecord medicalRecord = medicalRecordDAO.findByFirstName(person.getFirstName());
			listMedicalRecords.add(medicalRecord);
			ageCalculator.calculateDate(medicalRecord.getBirthdate());
		}

		List<Long> age = ageCalculator.getListAge();
		return mapper.writer(Filters.personInfoFilter).writeValueAsString(listPerson)
				+ mapper.writer(Filters.personInfoFilter).writeValueAsString(listMedicalRecord)
				+ mapper.writeValueAsString(new Age(age));
	}

	@GetMapping(value = "/communityEmail{city}")
	public String showMailsByCity(@RequestParam String city) throws JsonProcessingException {
		Logger.info("GET request of : /communityEmail{city}");
		List<Person> listPerson = personDAO.findEmailByCity(city);
		return mapper.writer(Filters.communityEmailFilter).writeValueAsString(listPerson);
	}

}
