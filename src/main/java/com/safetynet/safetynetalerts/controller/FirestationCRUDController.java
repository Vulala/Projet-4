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
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.Filters;
import com.safetynet.safetynetalerts.service.FirestationCRUDService;
import com.safetynet.safetynetalerts.service.object.FirestationCRUDObject;

@RestController
public class FirestationCRUDController {

	/**
	 * Rest controller for <b>CRUD</b> operations on /firestation. <br>
	 * <br>
	 * The <b>GET</b> <i>/firestation/</i> endpoint is used to <b>READ</b> all the
	 * firestations. <br>
	 * The <b>GET</b> <i>/firestation/{address}</i> endpoint is used to <b>READ</b>
	 * a specific firestation. <br>
	 * The <b>POST</b> <i>/firestation</i> endpoint is used to <b>CREATE</b> a
	 * firestation. <br>
	 * The <b>PUT</b> <i>/firestation</i> endpoint is used to <b>UPDATE</b> a
	 * firestation. <br>
	 * The <b>DELETE</b> <i>/firestation/{address}</i> endpoint is used to
	 * <b>DELETE</b> a specific firestation. <br>
	 * <br>
	 * The {@link FirestationCRUDController} use a service layer to get all the data
	 * {@link FirestationCRUDService}.<br>
	 * It also use an {@link ObjectMapper} and the {@link Filters} class for the
	 * response.
	 */

	@Autowired
	private FirestationCRUDService firestationCRUDService;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value = "/firestation/")
	public String showAllFirestations() throws JsonProcessingException {
		Logger.info("GET request of : /firestation");
		String response = mapper.writer(Filters.firestationNoFirestation)
				.writeValueAsString(firestationCRUDService.findAll());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/firestation/{address}")
	public String showFirestationById(@PathVariable String address) throws JsonProcessingException {
		Logger.info("GET request of : /firestation/" + address);
		String response = mapper.writer(Filters.firestationNoList)
				.writeValueAsString(firestationCRUDService.findById(address));
		Logger.info("Success");
		return response;
	}

	@PostMapping(value = "/firestation")
	public ResponseEntity<Void> addFirestation(@RequestBody Firestation firestation) {
		Logger.info("POST request on : /firestation");
		FirestationCRUDObject firestationCRUDObject = firestationCRUDService.save(firestation);
		if (firestation == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(firestationCRUDObject.getListFirestation()).toUri();
		Logger.info("Success");
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/firestation/{address}")
	public String updateFirestation(@RequestBody Firestation firestation, @PathVariable String address)
			throws JsonProcessingException {
		Logger.info("PUT request on : /firestation/" + address);
		String response = mapper.writer(Filters.firestationNoList)
				.writeValueAsString(firestationCRUDService.update(address, firestation));
		Logger.info("Success");
		return response;
	}

	@DeleteMapping(value = "/firestation/{address}")
	public void deleteFirestation(@PathVariable String address) {
		Logger.info("DELETE request of : /firestation/" + address);
		firestationCRUDService.deleteById(address);
		Logger.info("Success");
	}
}
