package com.safetynet.safetynetalerts.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tinylog.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.Filters;
import com.safetynet.safetynetalerts.service.MedicalRecordCRUDService;
import com.safetynet.safetynetalerts.service.object.MedicalRecordCRUDObject;

@RestController
public class MedicalRecordCRUDController {

	// CRUD operations on /medicalRecord

	@Autowired
	private MedicalRecordCRUDService medicalRecordCRUDService;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value = "/medicalRecord")
	public String showAllMedicalRecords() throws JsonProcessingException {
		Logger.info("GET request of : /medicalRecord");
		String response = mapper.writer(Filters.medicalRecordNoMedicalRecord)
				.writeValueAsString(medicalRecordCRUDService.findAll());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public String showMedicalRecordById(@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("GET request of : /medicalRecord/" + firstNameAndlastName);
		String response = mapper.writer(Filters.medicalRecordNoList)
				.writeValueAsString(medicalRecordCRUDService.findById(firstNameAndlastName));
		Logger.info("Success");
		return response;
	}

	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<Void> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		Logger.info("POST request on : /medicalRecord");
		MedicalRecordCRUDObject medicalRecordCRUDObject = medicalRecordCRUDService.save(medicalRecord);
		if (medicalRecord == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(medicalRecordCRUDObject.getListMedicalRecords()).toUri();
		Logger.info("Success");
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public String updateMedicalRecord(@RequestBody MedicalRecord medicalRecord,
			@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("PUT request on : /medicalRecord/" + firstNameAndlastName);
		String response = mapper.writer(Filters.medicalRecordNoList)
				.writeValueAsString(medicalRecordCRUDService.update(firstNameAndlastName, medicalRecord));
		Logger.info("Success");
		return response;
	}

	@DeleteMapping(value = "/medicalRecord/{firstNameAndlastName}")
	public void deleteMedicalRecord(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /medicalRecord/" + firstNameAndlastName);
		medicalRecordCRUDService.deleteById(firstNameAndlastName);
		Logger.info("Success");
	}
}
