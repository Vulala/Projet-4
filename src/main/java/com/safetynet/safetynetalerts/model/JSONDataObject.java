package com.safetynet.safetynetalerts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.safetynet.safetynetalerts.service.JSONReader;

@JsonFilter("EndPointsFilters")
public class JSONDataObject {

	/**
	 * {@link JSONDataObject} is used by {@link JSONReader}, presenting all the
	 * data.json fields. Each fields are represented by each model class in the
	 * model package. {@link JSONDataObject} is a model itself for
	 * {@link JSONReader}.
	 */

	private List<Person> persons;
	private List<Firestation> firestations;
	private List<MedicalRecord> medicalRecords;

	public JSONDataObject() {
	}

	public JSONDataObject(List<Person> person, List<Firestation> firestation, List<MedicalRecord> medicalRecord) {
		this.persons = person;
		this.firestations = firestation;
		this.medicalRecords = medicalRecord;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> person) {
		this.persons = person;
	}

	public List<Firestation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<Firestation> firestation) {
		this.firestations = firestation;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalrecords(List<MedicalRecord> medicalRecord) {
		this.medicalRecords = medicalRecord;
	}

	@Override
	public String toString() {
		return "JSONDataObject [persons=" + persons + ", firestations=" + firestations + ", medicalRecords="
				+ medicalRecords + "]";
	}

}