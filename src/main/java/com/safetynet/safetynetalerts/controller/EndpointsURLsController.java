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

	// Endpoints URLs

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
