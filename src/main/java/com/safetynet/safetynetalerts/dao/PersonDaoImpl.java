package com.safetynet.safetynetalerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.JSONReader;

@Repository
public class PersonDaoImpl implements PersonDAO {

	/**
	 * Implementation of the interface {@link PersonDAO} used to define operations
	 * for the type {@link Person}.
	 * 
	 * @Method findAll() is used to <b>GET</b> all the persons present in the
	 *         data.json file, return type: {@link List}<{@link Person}>.
	 * @Method findById() is used to return a specific {@link Person} if present in
	 *         the data.json file, else return null.
	 * @Method findByLastName() is used to return a {@link List}<{@link Person}>
	 *         depending of the parameter.
	 * @Method findByAddress() is used to return {@link List}<{@link Person}>
	 *         depending of the parameter.
	 * @Method findEmailByCity() is used to return {@link List}<{@link Person}>
	 *         depending of the parameter.
	 * @Method save() is used to <b>CREATE</b> a new person, return type:
	 *         {@link List}<{@link Person}>.
	 * @Method update() is used to <b>UPDATE</b> a specific person, return type:
	 *         {@link Person}.
	 * @Method delete() is used to <b>DELETE</b> a specific person, return type:
	 *         void.
	 */

	private List<Person> persons;

	public PersonDaoImpl(List<Person> person) throws Exception {
		super();
		person = new JSONReader().getData().getPersons();
		this.persons = person;
	}

	@Override
	public List<Person> findAll() {
		return persons;
	}

	@Override
	public Person findById(String firstNameAndlastName) {
		for (Person person : persons) {
			if ((person.getfirstNameAndlastName()).equals(firstNameAndlastName)) {
				return person;
			}
		}
		return null;
	}

	@Override
	public List<Person> findByLastName(String lastName) {
		List<Person> listPerson = new ArrayList<Person>();
		for (Person person : persons) {
			if ((person.getLastName()).equals(lastName)) {
				listPerson.add(person);
			}
		}
		return listPerson;
	}

	@Override
	public List<Person> findByAddress(String address) {
		List<Person> listPerson = new ArrayList<Person>();
		for (Person person : persons) {
			if ((person.getAddress()).equals(address)) {
				listPerson.add(person);
			}
		}
		return listPerson;
	}

	@Override
	public List<Person> findEmailByCity(String city) {
		List<Person> listPerson = new ArrayList<Person>();
		for (Person person : persons) {
			if ((person.getCity()).equals(city)) {
				listPerson.add(person);
			}
		}
		return listPerson;
	}

	@Override
	public List<Person> save(Person person) {
		List<Person> savePerson = persons;
		savePerson.add(person);
		return savePerson;
	}

	@Override
	public Person update(String firstNameAndlastName, Person person) {
		for (Person updatePerson : persons) {
			if ((updatePerson.getfirstNameAndlastName()).equals(firstNameAndlastName)) {
				updatePerson.setAddress(person.getAddress());
				updatePerson.setCity(person.getCity());
				updatePerson.setEmail(person.getEmail());
				updatePerson.setPhone(person.getPhone());
				updatePerson.setZip(person.getZip());
				return updatePerson;
			}
		}
		return null;
	}

	@Override
	public void deleteById(String firstNameAndlastName) {
		List<Person> deletePerson = persons;
		deletePerson.removeIf(person -> person.getfirstNameAndlastName().equals(firstNameAndlastName));
	}

}
