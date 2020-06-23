package com.safetynet.safetynetalerts;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.safetynet.safetynetalerts.controller.EndpointsURLsController;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.EndpointsURLsService;
import com.safetynet.safetynetalerts.service.object.EndpointsURLsObject;

@WebMvcTest(EndpointsURLsController.class)
public class EndpointsURLsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EndpointsURLsService endpointsURLsService;

	@Test
	public void showPersonsByFirestation() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		long children = 0;
		long adults = 0;
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons, adults, children);
		when(endpointsURLsService.showPersonsByFirestation(any(int.class))).thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/firestation?stationNumber=0")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showPersonsByFirestation(any(int.class));
	}

	@Test
	public void showChildrenByAddress() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		List<Long> age = null;
		long children = 0;
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons, listMedicalRecords, age,
				children);
		when(endpointsURLsService.showChildrenByAddress(any(String.class))).thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/childAlert?address=AAAA")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showChildrenByAddress(any(String.class));
	}

	@Test
	public void showPhoneNumbersByFirestation() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons);
		when(endpointsURLsService.showPhoneNumbersByFirestation(any(int.class))).thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/phoneAlert?firestation=0")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showPhoneNumbersByFirestation(any(int.class));
	}

	@Test
	public void showPersonsByAddress() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		Firestation firestations = new Firestation();
		List<Long> age = null;
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons, listMedicalRecords,
				firestations, age);
		when(endpointsURLsService.showPersonsByAddress(any(String.class))).thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/fire?address=AAAA")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showPersonsByAddress(any(String.class));
	}

	@Test
	public void showPersonsAddressByFirestation() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		List<Long> age = null;
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons, listMedicalRecords, age);
		when(endpointsURLsService.showPersonsAddressByFirestation(any(int.class))).thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/flood/stations?stations=0")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showPersonsAddressByFirestation(any(int.class));
	}

	@Test
	public void showPersonInfoByPerson() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		List<MedicalRecord> listMedicalRecords = new ArrayList<MedicalRecord>();
		List<Long> age = null;
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons, listMedicalRecords, age);
		when(endpointsURLsService.showPersonInfoByPerson(any(String.class), any(String.class)))
				.thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/personInfo?firstName=AAAA&lastName=BBBB")).andDo(print())
				.andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showPersonInfoByPerson(any(String.class), any(String.class));
	}

	@Test
	public void showMailsByCity() throws Exception {
		// ARRANGE
		List<Person> listPersons = new ArrayList<Person>();
		EndpointsURLsObject endpointsURLsCRUDObject = new EndpointsURLsObject(listPersons);
		when(endpointsURLsService.showMailsByCity(any(String.class))).thenReturn(endpointsURLsCRUDObject);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/communityEmail?city=AAAA")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(endpointsURLsService, times(1)).showMailsByCity(any(String.class));
	}

}
