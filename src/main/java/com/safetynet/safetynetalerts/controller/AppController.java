package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tinylog.Logger;

import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.dao.MedicalRecordDAO;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@RestController
public class AppController {

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private FirestationDAO firestationDAO;
	@Autowired
	private MedicalRecordDAO medicalRecordDAO;

	@RequestMapping("/")
	public String viewHomePage() {
		Logger.info("Request of : /");
		return "index";
	}

	// RequestMapping needed for Cucumber

	@RequestMapping("/actuator/health")
	public String showHealthActuator(Model model) {
		Logger.info("Request of : actuator/health");
		return "actuator/health";
	}

	@RequestMapping("/actuator/info")
	public String showInfoActuator(Model model) {
		Logger.info("Request of : info");
		return "actuator/info";
	}

	@RequestMapping("/actuator/metrics")
	public String showMetricsActuator(Model model) {
		Logger.info("Request of : metrics");
		return "actuator/metrics";
	}

	@RequestMapping("/actuator/httptrace")
	public String showHttptraceActuator(Model model) {
		Logger.info("Request of : httptrace");
		return "actuator/httptrace";
	}

	@RequestMapping("actuator/release-notes")
	public String showReleaseNotesActuator(Model model) {
		Logger.info("Request of : release-notes");
		return "actuator/release-notes";
	}

	// CRUD operations on /person

	@GetMapping(value = "/person")
	public List<Person> listPerson() {
		Logger.info("GET request of : /person");
		return personDAO.findAll();
	}

	@GetMapping(value = "/person/{firstNameAndlastName}")
	public Person showPersonById(@PathVariable String firstNameAndlastName) {
		Logger.info("GET request of : /person/{firstNameAndlastName}");
		return personDAO.findById(firstNameAndlastName);
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
	public Person updatePerson(@RequestBody Person person, @PathVariable String firstNameAndlastName) {
		Logger.info("PUT request on : /person/{firstNameAndlastName}");
		return personDAO.update(firstNameAndlastName, person);
	}

	@DeleteMapping(value = "/person/{firstNameAndlastName}")
	public void deletePerson(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /person/{firstNameAndlastName}");
		personDAO.deleteById(firstNameAndlastName);
	}

	// CRUD operations on /firestation

	@GetMapping(value = "/firestation")
	public List<Firestation> listFirestation() {
		Logger.info("GET request of : /firestation");
		return firestationDAO.findAll();
	}

	@GetMapping(value = "/firestation/{address}")
	public Firestation showFirestationById(@PathVariable String address) {
		Logger.info("GET request of : /firestation/{address}");
		return firestationDAO.findById(address);
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
	public Firestation updateFirestation(@RequestBody Firestation firestation, @PathVariable String address) {
		Logger.info("PUT request on : /firestation/{address}");
		return firestationDAO.update(address, firestation);
	}

	@DeleteMapping(value = "/firestation/{address}")
	public void deleteFirestation(@PathVariable String address) {
		Logger.info("DELETE request of : /firestation/{address}");
		firestationDAO.deleteById(address);
	}

	// CRUD operations on /medicalRecord

	@GetMapping(value = "/medicalRecord")
	public List<MedicalRecord> listMedicalRecord() {
		Logger.info("GET request of : /medicalRecord");
		return medicalRecordDAO.findAll();
	}

	@GetMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public MedicalRecord showMedicalRecordById(@PathVariable String firstNameAndlastName) {
		Logger.info("GET request of : /medicalRecord/{firstNameAndlastName}");
		return medicalRecordDAO.findById(firstNameAndlastName);
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
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord,
			@PathVariable String firstNameAndlastName) {
		Logger.info("PUT request on : /medicalRecord/{firstNameAndlastName}");
		return medicalRecordDAO.update(firstNameAndlastName, medicalRecord);
	}

	@DeleteMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public void deleteMedicalRecord(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /medicalRecord/{firstNameAndlastName}");
		medicalRecordDAO.deleteById(firstNameAndlastName);
	}
}
