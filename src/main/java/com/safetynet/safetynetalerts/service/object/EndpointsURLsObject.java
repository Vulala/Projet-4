package com.safetynet.safetynetalerts.service.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

@JsonFilter("EndPointsFilters")
public class EndpointsURLsObject {

	private List<Person> listPersons;
	private List<MedicalRecord> listMedicalRecords;
	private Firestation firestations;
	private long adults;
	private long children;
	private List<Long> age;

	public EndpointsURLsObject(List<Person> listPersons, long adults, long children) {
		super();
		this.listPersons = listPersons;
		this.adults = adults;
		this.children = children;
	}

	public EndpointsURLsObject(List<Person> listPersons, List<MedicalRecord> listMedicalRecords, List<Long> age,
			long children) {
		super();
		this.listPersons = listPersons;
		this.listMedicalRecords = listMedicalRecords;
		this.age = age;
		this.children = children;
	}

	public EndpointsURLsObject(List<Person> listPersons) {
		super();
		this.listPersons = listPersons;
	}

	public EndpointsURLsObject(List<Person> listPersons, List<MedicalRecord> listMedicalRecords,
			Firestation firestations, List<Long> age) {
		super();
		this.listPersons = listPersons;
		this.listMedicalRecords = listMedicalRecords;
		this.firestations = firestations;
		this.age = age;
	}

	public EndpointsURLsObject(List<Person> listPersons, List<MedicalRecord> listMedicalRecords, List<Long> age) {
		super();
		this.listPersons = listPersons;
		this.listMedicalRecords = listMedicalRecords;
		this.age = age;
	}

	public List<Person> getListPersons() {
		return listPersons;
	}

	public List<MedicalRecord> getListMedicalRecords() {
		return listMedicalRecords;
	}

	public Firestation getFirestations() {
		return firestations;
	}

	public long getAdults() {
		return adults;
	}

	public long getChildren() {
		return children;
	}

	public List<Long> getAge() {
		return age;
	}

}
