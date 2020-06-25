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
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.Filters;
import com.safetynet.safetynetalerts.service.PersonCRUDService;
import com.safetynet.safetynetalerts.service.object.PersonCRUDObject;

@RestController
public class PersonCRUDController {

	/**
	 * Rest controller for <b>CRUD</b> operations on /person. <br>
	 * <br>
	 * The <b>GET</b> <i>/person</i> endpoint is used to <b>READ</b> all the
	 * persons. <br>
	 * The <b>GET</b> <i>/person/{firstNameAndlastName}</i> endpoint is used to
	 * <b>READ</b> a specific person. <br>
	 * The <b>POST</b> <i>/person</i> endpoint is used to <b>CREATE</b> a person.
	 * <br>
	 * The <b>PUT</b> <i>/person</i> endpoint is used to <b>UPDATE</b> a person.
	 * <br>
	 * The <b>DELETE</b> <i>/person/{firstNameAndlastName}</i> endpoint is used to
	 * <b>DELETE</b> a specific person. <br>
	 * <br>
	 * The {@link PersonCRUDController} use a service layer to get all the data
	 * {@link PersonCRUDService}.<br>
	 * It also use an {@link ObjectMapper} and the {@link Filters} class for the
	 * response.
	 */

	@Autowired
	private PersonCRUDService personCRUDService;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value = "/person")
	public String showAllPersons() throws JsonProcessingException {
		Logger.info("GET request of : /person");
		String response = mapper.writer(Filters.personNoPerson).writeValueAsString(personCRUDService.findAll());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/person/{firstNameAndlastName}")
	public String showPersonById(@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("GET request of : /person/" + firstNameAndlastName);
		String response = mapper.writer(Filters.personNoList)
				.writeValueAsString(personCRUDService.findById(firstNameAndlastName));
		Logger.info("Success");
		return response;
	}

	@PostMapping(value = "/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		Logger.info("POST request on : /person");
		PersonCRUDObject personCRUDObject = personCRUDService.save(person);
		if (person == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(personCRUDObject.getListPersons()).toUri();
		Logger.info("Success");
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/person/{firstNameAndlastName}")
	public String updatePerson(@RequestBody Person person, @PathVariable String firstNameAndlastName)
			throws JsonProcessingException {
		Logger.info("PUT request on : /person/" + firstNameAndlastName);
		String response = mapper.writer(Filters.personNoList)
				.writeValueAsString(personCRUDService.update(firstNameAndlastName, person));
		Logger.info("Success");
		return response;
	}

	@DeleteMapping(value = "/person/{firstNameAndlastName}")
	public void deletePerson(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /person/" + firstNameAndlastName);
		personCRUDService.deleteById(firstNameAndlastName);
		Logger.info("Success");
	}
}
