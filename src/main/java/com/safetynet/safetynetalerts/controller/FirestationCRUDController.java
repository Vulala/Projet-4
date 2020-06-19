package com.safetynet.safetynetalerts.controller;

import java.net.URI;
import java.util.List;

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
import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.Filters;

@RestController
public class FirestationCRUDController {

	// CRUD operations on /firestation

	@Autowired
	private FirestationDAO firestationDAO;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value = "/firestation/")
	public String showAllFirestations() throws JsonProcessingException {
		Logger.info("GET request of : /firestation");
		String response = mapper.writer(Filters.serializeAll).writeValueAsString(firestationDAO.findAll());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/firestation/{address}")
	public String showFirestationById(@PathVariable String address) throws JsonProcessingException {
		Logger.info("GET request of : /firestation/{address}");
		String response = mapper.writer(Filters.serializeAll).writeValueAsString(firestationDAO.findById(address));
		Logger.info("Success");
		return response;
	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		Logger.info("POST request on : /firestation");
		List<Firestation> saveFirestation = firestationDAO.save(firestation);
		if (firestation == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(saveFirestation.get(1)).toUri();
		Logger.info("Success");
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/firestation/{address}")
	public String updateFirestation(@RequestBody Firestation firestation, @PathVariable String address)
			throws JsonProcessingException {
		Logger.info("PUT request on : /firestation/{address}");
		String response = mapper.writer(Filters.serializeAll)
				.writeValueAsString(firestationDAO.update(address, firestation));
		Logger.info("Success");
		return response;
	}

	@DeleteMapping(value = "/firestation/{address}")
	public void deleteFirestation(@PathVariable String address) {
		Logger.info("DELETE request of : /firestation/{address}");
		firestationDAO.deleteById(address);
		Logger.info("Success");
	}
}