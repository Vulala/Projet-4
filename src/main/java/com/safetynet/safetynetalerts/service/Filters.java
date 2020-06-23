package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Filters {

	/**
	 * Filter used in the Controllers to sort out the JSON file for the
	 * EndpointsURLs and EndpointsCRUD.
	 */

	static SimpleBeanPropertyFilter noFilter = SimpleBeanPropertyFilter.serializeAll();
	public static FilterProvider serializeAll = new SimpleFilterProvider().addFilter("EndPointsFilters", noFilter);

	// PersonCRUD

	static SimpleBeanPropertyFilter personWithOutList = SimpleBeanPropertyFilter.serializeAllExcept("listPersons");
	public static FilterProvider personNoList = new SimpleFilterProvider().addFilter("EndPointsFilters",
			personWithOutList);

	static SimpleBeanPropertyFilter personWithOutPerson = SimpleBeanPropertyFilter.serializeAllExcept("person");
	public static FilterProvider personNoPerson = new SimpleFilterProvider().addFilter("EndPointsFilters",
			personWithOutPerson);

	// FirestationCRUD

	static SimpleBeanPropertyFilter firestationWithOutList = SimpleBeanPropertyFilter
			.serializeAllExcept("listFirestation");
	public static FilterProvider firestationNoList = new SimpleFilterProvider().addFilter("EndPointsFilters",
			firestationWithOutList);

	static SimpleBeanPropertyFilter firestationWithOutFirestation = SimpleBeanPropertyFilter
			.serializeAllExcept("firestation");
	public static FilterProvider firestationNoFirestation = new SimpleFilterProvider().addFilter("EndPointsFilters",
			firestationWithOutFirestation);

	// MedicalRecordCRUD

	static SimpleBeanPropertyFilter medicalRecordWithOutList = SimpleBeanPropertyFilter
			.serializeAllExcept("listMedicalRecords");
	public static FilterProvider medicalRecordNoList = new SimpleFilterProvider().addFilter("EndPointsFilters",
			medicalRecordWithOutList);

	static SimpleBeanPropertyFilter medicalRecordWithOutMedicalRecord = SimpleBeanPropertyFilter
			.serializeAllExcept("medicalRecord");
	public static FilterProvider medicalRecordNoMedicalRecord = new SimpleFilterProvider().addFilter("EndPointsFilters",
			medicalRecordWithOutMedicalRecord);

	// EndpointsURLs

	static SimpleBeanPropertyFilter firestationStationNumber = SimpleBeanPropertyFilter
			.serializeAllExcept("firestations", "medications", "allergies", "city", "zip", "email", "birthdate");
	public static FilterProvider firestationStationNumberFilter = new SimpleFilterProvider()
			.addFilter("EndPointsFilters", firestationStationNumber);

	static SimpleBeanPropertyFilter childAlert = SimpleBeanPropertyFilter.serializeAllExcept("firestations",
			"medications", "allergies", "address", "city", "zip", "phone", "email");
	public static FilterProvider childAlertFilter = new SimpleFilterProvider().addFilter("EndPointsFilters",
			childAlert);

	static SimpleBeanPropertyFilter phoneAlert = SimpleBeanPropertyFilter.serializeAllExcept("firestations",
			"medicalrecords", "firstName", "lastName", "address", "city", "zip", "email");
	public static FilterProvider phoneAlertFilter = new SimpleFilterProvider().addFilter("EndPointsFilters",
			phoneAlert);

	static SimpleBeanPropertyFilter fireAddress = SimpleBeanPropertyFilter.serializeAllExcept("address", "firstName",
			"city", "zip", "email");
	public static FilterProvider fireAddressFilter = new SimpleFilterProvider().addFilter("EndPointsFilters",
			fireAddress);

	static SimpleBeanPropertyFilter floodStations = SimpleBeanPropertyFilter.serializeAllExcept("zip", "firstName",
			"address", "city", "email", "firestations");
	public static FilterProvider floodStationsFilter = new SimpleFilterProvider().addFilter("EndPointsFilters",
			floodStations);

	static SimpleBeanPropertyFilter personInfo = SimpleBeanPropertyFilter.serializeAllExcept("firestations",
			"firstName", "city", "zip", "phone");
	public static FilterProvider personInfoFilter = new SimpleFilterProvider().addFilter("EndPointsFilters",
			personInfo);

	static SimpleBeanPropertyFilter communityEmail = SimpleBeanPropertyFilter.serializeAllExcept("firestations",
			"medicalrecords", "firstName", "lastName", "address", "city", "zip", "phone");
	public static FilterProvider communityEmailFilter = new SimpleFilterProvider().addFilter("EndPointsFilters",
			communityEmail);

}
