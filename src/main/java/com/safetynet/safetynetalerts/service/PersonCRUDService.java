package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.PersonCRUDController;
import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.object.PersonCRUDObject;

@Service
public class PersonCRUDService {

	/**
	 * Service used to interact with the DAO layer.<br>
	 * <br>
	 * This {@link PersonCRUDService} is specific for the requests from the
	 * {@link PersonCRUDController}.<br>
	 * It get the requests from the {@link PersonCRUDController} and return the data
	 * into a specific {@link PersonCRUDObject}.<br>
	 * <br>
	 * This service make use of the {@link PersonDAO} interface.<br>
	 */

	@Autowired
	private PersonDAO personDAO;

	public PersonCRUDObject findAll() {
		List<Person> listPersons = new ArrayList<Person>();
		listPersons = personDAO.findAll();

		return new PersonCRUDObject(listPersons);
	}

	public PersonCRUDObject findById(String address) {
		Person person = new Person();
		person = personDAO.findById(address);

		return new PersonCRUDObject(person);
	}

	public PersonCRUDObject save(Person person) {
		List<Person> listPerson = personDAO.save(person);
		return new PersonCRUDObject(listPerson);
	}

	public PersonCRUDObject update(String address, Person person) {
		Person personUpdated = personDAO.update(address, person);
		return new PersonCRUDObject(personUpdated);
	}

	public void deleteById(String address) {
		personDAO.deleteById(address);
	}
}
