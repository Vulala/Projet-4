package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.dao.PersonDAO;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.object.PersonCRUDObject;

@Service
public class PersonCRUDService {

	/**
	 * Service used to interact with the DAO layer
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
