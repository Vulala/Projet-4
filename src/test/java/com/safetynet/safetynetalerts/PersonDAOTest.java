package com.safetynet.safetynetalerts;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.safetynetalerts.dao.PersonDaoImpl;
import com.safetynet.safetynetalerts.model.Person;

public class PersonDAOTest {

	private List<Person> listPersons;
	private PersonDaoImpl personDaoImpl;

	@BeforeEach
	public void setUp() throws Exception {
		personDaoImpl = new PersonDaoImpl(listPersons);
	}

	@Test
	public void findAll() {
		// ARRANGE
		List<Person> listPerson = new ArrayList<Person>(); // method return value
		// ACT
		listPerson = personDaoImpl.findAll();
		// ASSERT
		assertThat(listPerson.toString(), containsString("John"));
	}

	@Test
	public void findById() {
		// ARRANGE
		Person person = new Person(); // method return value
		String firstNameAndlastName = "JohnBoyd"; // parameter
		// ACT
		person = personDaoImpl.findById(firstNameAndlastName);
		// ASSERT
		assertThat("JohnBoyd", containsString(person.getfirstNameAndlastName()));

	}

	@Test
	public void findByLastName() {
		// ARRANGE
		List<Person> listPerson = new ArrayList<Person>(); // method return value
		String lastName = "Boyd"; // parameter
		// ACT
		listPerson = personDaoImpl.findByLastName(lastName);
		// ASSERT
		assertThat(listPerson.toString(), containsString("Boyd"));
		assertThat(listPerson.toString(), not(containsString("Stelzer")));

	}

	@Test
	public void findByAddress() {
		// ARRANGE
		List<Person> listPerson = new ArrayList<Person>(); // method return value
		String address = "834 Binoc Ave"; // parameter
		// ACT
		listPerson = personDaoImpl.findByAddress(address);
		// ASSERT
		assertThat(listPerson.toString(), containsString("Carman"));
		assertThat(listPerson.toString(), not(containsString("Stelzer")));

	}

	@Test
	public void findEmailByCity() {
		// ARRANGE
		List<Person> listPerson = new ArrayList<Person>(); // method return value
		String city = "Culver"; // parameter
		// ACT
		listPerson = personDaoImpl.findEmailByCity(city);
		// ASSERT
		assertThat(listPerson.toString(), containsString("Culver"));
		assertThat(listPerson.toString(), containsString("jaboyd@email.com"));

	}

	@Test
	public void save() {
		// ARRANGE
		Person person = new Person(); // parameter
		person.setFirstName("AAAA");
		// ACT
		personDaoImpl.save(person);
		// ASSERT
		listPersons = personDaoImpl.findAll();
		assertThat(listPersons.toString(), containsString("AAAA"));

	}

	@Test
	public void update() {
		// ARRANGE
		String firstNameAndlastName = "JohnBoyd"; // parameter
		Person person = new Person(); // parameter
		person.setFirstName("AAAA");
		person.setCity("BBBB");
		// ACT
		personDaoImpl.update(firstNameAndlastName, person);
		// ASSERT
		listPersons = personDaoImpl.findAll();
		assertThat(listPersons.toString(), containsString("BBBB"));
		assertThat(listPersons.toString(), not(containsString("AAAA")));
		assertThat(listPersons.toString(), containsString("email=null"));

	}

	@Test
	public void deleteById() {
		// ARRANGE
		String firstNameAndlastName = "JohnBoyd"; // parameter
		// ACT
		personDaoImpl.deleteById(firstNameAndlastName);
		// ASSERT
		listPersons = personDaoImpl.findAll();
		assertThat(listPersons.toString(), not(containsString("John")));
	}
}