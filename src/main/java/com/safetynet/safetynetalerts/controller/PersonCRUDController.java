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
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.Filters;

@RestController
public class PersonCRUDController {

	// CRUD operations on /person

	@Autowired
	private PersonDAO personDAO;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value = "/person")
	public String showAllPersons() throws JsonProcessingException {
		Logger.info("GET request of : /person");
		String response = mapper.writer(Filters.serializeAll).writeValueAsString(personDAO.findAll());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/person/{firstNameAndlastName}")
	public String showPersonById(@PathVariable String firstNameAndlastName) throws JsonProcessingException {
		Logger.info("GET request of : /person/{firstNameAndlastName}");
		String response = mapper.writer(Filters.serializeAll)
				.writeValueAsString(personDAO.findById(firstNameAndlastName));
		Logger.info("Success");
		return response;
	}

	@PostMapping(value = "/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		Logger.info("POST request on : /person");
		List<Person> savePerson = personDAO.save(person);
		if (person == null) {
			return ResponseEntity.noContent().build();

		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(savePerson.get(1)).toUri();
		Logger.info("Success");
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/person/{firstNameAndlastName}")
	public String updatePerson(@RequestBody Person person, @PathVariable String firstNameAndlastName)
			throws JsonProcessingException {
		Logger.info("PUT request on : /person/{firstNameAndlastName}");
		String response = mapper.writer(Filters.serializeAll)
				.writeValueAsString(personDAO.update(firstNameAndlastName, person));
		Logger.info("Success");
		return response;
	}

	@DeleteMapping(value = "/person/{firstNameAndlastName}")
	public void deletePerson(@PathVariable String firstNameAndlastName) {
		Logger.info("DELETE request of : /person/{firstNameAndlastName}");
		personDAO.deleteById(firstNameAndlastName);
		Logger.info("Success");
	}
}
