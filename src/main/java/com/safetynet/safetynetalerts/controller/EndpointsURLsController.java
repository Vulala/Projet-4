package com.safetynet.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.AdultsAndChildren;
import com.safetynet.safetynetalerts.model.Age;
import com.safetynet.safetynetalerts.service.EndpointsURLsService;
import com.safetynet.safetynetalerts.service.Filters;
import com.safetynet.safetynetalerts.service.object.EndpointsURLsObject;

@RestController
public class EndpointsURLsController {

	/**
	 * Rest controller for the EndpointsURLs.<br>
	 * <br>
	 * The {@link EndpointsURLsController} use a service layer to get all the data.
	 * {@link EndpointsURLsService}.<br>
	 * It also use an {@link ObjectMapper} and the {@link Filters} class for the
	 * response.<br>
	 * <br>
	 * 
	 * The <i>/firestation?stationNumber</i> endpoint is used to <b>GET</b> the list
	 * of the people covered by the stationNumber set as parameter. <br>
	 * Handled by the <i>showPersonsByFirestation</i> method, it show all the
	 * persons deserved by this <i>stationNumber</i>, showing the <i>firstName</i>,
	 * <i>lastName</i>, <i>address</i> and <i>phone</i> of the concerned people. It
	 * show also a count of the numbers of adults and children (-18yo) deserved
	 * bythis <i>stationNumber</i>.<br>
	 * <br>
	 * 
	 * The <i>/childAlert?address</i> endpoint is used to <b>GET</b> the list of the
	 * children (-18yo) living at the address set as parameter.<br>
	 * Handled by the <i>showChildrenByAddress</i> method, it show all the children
	 * present at this address, showing the <i>firstName</i>, <i>lastName</i>,
	 * <i>age</i>, <i>children</i> and the <i>adults</i> of this address. If there
	 * is no children, then the url is empty.<br>
	 * <br>
	 * 
	 * The <i>/phoneAlert?firestation</i> endpoint is used to <b>GET</b> all the
	 * phone numbers of the people covered by the station set as parameter.<br>
	 * Handled by the <i>showPhoneNumbersByFirestation</i> method, it show all the
	 * phone numbers of the people deserved by this firestation.<br>
	 * <br>
	 * 
	 * The <i>/fire?address</i> endpoint is used to <b>GET</b> the list of the
	 * people living at the address set as parameter plus the station_number who
	 * deserve this address.<br>
	 * Handled by the <i>showPersonsByAddress</i> method, it show all the persons
	 * living at this <i>address</i> with their <i>lastName</i>, <i>phone</i>,
	 * <i>age</i> and the <i>medicalRecord</i> of each person.<br>
	 * <br>
	 * 
	 * The <i>/flood/stations</i> endpoint is used to <b>GET</b> a list of all the
	 * persons deserved by the station set as parameter.<br>
	 * Handled by the <i>showPersonsAddressByFirestation</i> method, it show all the
	 * persons deserved by this <i>station</i> gathered by address, with their
	 * <i>lastName</i>, <i>phone</i> and <i>age</i>, it also show the
	 * <i>medicalRecord</i> of every persons.<br>
	 * <br>
	 * 
	 * The <i>/personInfo</i> endpoint is used to <b>GET</b> the person set as
	 * parameters.<br>
	 * Handled by the <i>showPersonInfoByPerson</i> method, it show all the
	 * informations about this person: <i>lastName</i>, <i>address</i>, <i>age</i>,
	 * <i>email</i>, <i>medicalRecord</i> and if there is different persons with the
	 * same <i>lastName</i>, then nobody is missing.<br>
	 * <br>
	 * 
	 * The <i>/communityEmail</i> endpoint is used to <b>GET</b> all the email of
	 * the people living in the city set as parameter.<br>
	 * Handled by the <i>showMailsByCity</i> method, it show all emails of the
	 * persons living in that <i>city</i>.<br>
	 */

	@Autowired
	private EndpointsURLsService endpointsURLsService;
	@Autowired
	private ObjectMapper mapper;

	@GetMapping(value = "/firestation{stationNumber}")
	public String showPersonsByFirestation(@RequestParam(value = "stationNumber") int stationNumber)
			throws JsonProcessingException {
		Logger.info("GET request of : /firestation{stationNumber}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showPersonsByFirestation(stationNumber);

		String response = mapper.writeValueAsString(
				new AdultsAndChildren(endpointsURLsObject.getAdults(), endpointsURLsObject.getChildren()))
				+ mapper.writer(Filters.firestationStationNumberFilter)
						.writeValueAsString(endpointsURLsObject.getListPersons());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/childAlert{address}")
	public String showChildrenByAddress(@RequestParam(value = "address") String address)
			throws JsonProcessingException {
		Logger.info("GET request of : /childAlert{address}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showChildrenByAddress(address);
		long children = endpointsURLsObject.getChildren();

		if (children == 0) {
			Logger.info("Success");
			return null;
		} else {
			String response = mapper.writer(Filters.childAlertFilter)
					.writeValueAsString(endpointsURLsObject.getListPersons())
					+ mapper.writeValueAsString(new Age(endpointsURLsObject.getAge()));
			Logger.info("Success");
			return response;
		}
	}

	@GetMapping(value = "/phoneAlert{firestation}")
	public String showPhoneNumbersByFirestation(@RequestParam(value = "firestation") int firestation)
			throws JsonProcessingException {
		Logger.info("GET request of : /phoneAlert{firestation}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showPhoneNumbersByFirestation(firestation);

		String response = mapper.writer(Filters.phoneAlertFilter)
				.writeValueAsString(endpointsURLsObject.getListPersons());
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/fire{address}")
	public String showPersonsByAddress(@RequestParam(value = "address") String address) throws JsonProcessingException {
		Logger.info("GET request of : /fire{address}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showPersonsByAddress(address);

		String response = mapper.writer(Filters.fireAddressFilter)
				.writeValueAsString(endpointsURLsObject.getFirestations())
				+ mapper.writer(Filters.fireAddressFilter).writeValueAsString(endpointsURLsObject.getListPersons())
				+ mapper.writer(Filters.fireAddressFilter)
						.writeValueAsString(endpointsURLsObject.getListMedicalRecords())
				+ mapper.writeValueAsString(new Age(endpointsURLsObject.getAge()));
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/flood/stations{stations}")
	public String showPersonsAddressByFirestation(@RequestParam(value = "stations") int stations)
			throws JsonProcessingException {
		Logger.info("GET request of : /flood/stations{stations}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showPersonsAddressByFirestation(stations);

		String response = mapper.writer(Filters.floodStationsFilter)
				.writeValueAsString(endpointsURLsObject.getListPersons())
				+ mapper.writer(Filters.floodStationsFilter)
						.writeValueAsString(endpointsURLsObject.getListMedicalRecords())
				+ mapper.writeValueAsString(new Age(endpointsURLsObject.getAge()));
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/personInfo{firstName}{lastName}")
	public String showPersonInfoByPerson(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) throws JsonProcessingException {
		Logger.info("GET request of : /personInfo{firstName}{lastName}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showPersonInfoByPerson(firstName, lastName);

		String response = mapper.writer(Filters.personInfoFilter)
				.writeValueAsString(endpointsURLsObject.getListPersons())
				+ mapper.writer(Filters.personInfoFilter)
						.writeValueAsString(endpointsURLsObject.getListMedicalRecords())
				+ mapper.writeValueAsString(new Age(endpointsURLsObject.getAge()));
		Logger.info("Success");
		return response;
	}

	@GetMapping(value = "/communityEmail{city}")
	public String showMailsByCity(@RequestParam String city) throws JsonProcessingException {
		Logger.info("GET request of : /communityEmail{city}");

		EndpointsURLsObject endpointsURLsObject = endpointsURLsService.showMailsByCity(city);

		String response = mapper.writer(Filters.communityEmailFilter)
				.writeValueAsString(endpointsURLsObject.getListPersons());
		Logger.info("Success");
		return response;
	}

}
