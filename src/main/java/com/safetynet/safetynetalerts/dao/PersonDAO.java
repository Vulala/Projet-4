package com.safetynet.safetynetalerts.dao;

import java.util.List;

import com.safetynet.safetynetalerts.model.Person;

public interface PersonDAO {

	/**
	 * Interface used to define CRUD operations for the type {@link Person}
	 */

	public List<Person> findAll();

	public Person findById(String firstNameAndlastName);

	public List<Person> save(Person person);

	public Person update(String firstNameAndlastName, Person person);

	public void deleteById(String firstNameAndlastName);

}
