package com.safetynet.safetynetalerts.service.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.safetynet.safetynetalerts.model.Person;

@JsonFilter("EndPointsFilters")
public class PersonCRUDObject {

	private List<Person> listPersons;
	private Person person;

	public PersonCRUDObject(List<Person> listPersons) {
		super();
		this.listPersons = listPersons;
	}

	public PersonCRUDObject(Person person) {
		super();
		this.person = person;
	}

	public List<Person> getListPersons() {
		return listPersons;
	}

	public Person getPerson() {
		return person;
	}

}
