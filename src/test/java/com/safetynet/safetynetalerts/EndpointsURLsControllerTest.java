package com.safetynet.safetynetalerts;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.safetynet.safetynetalerts.controller.EndpointsURLsController;
import com.safetynet.safetynetalerts.service.EndpointsURLsService;

@WebMvcTest(EndpointsURLsController.class)
public class EndpointsURLsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EndpointsURLsService endpointsURLsService;

	@Test
	public void showPersonsByFirestation() throws Exception {
		// ARRANGE
		int stationNumber = 0;
		Mockito.doNothing().when(endpointsURLsService).showPersonsByFirestation(stationNumber);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/firestation?stationNumber=0")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Test
	public void showChildrenByAddress() throws Exception {
		// ARRANGE
		String address = "AAAA";
		Mockito.doNothing().when(endpointsURLsService).showChildrenByAddress(address);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/childAlert?address=AAAA")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Test
	public void showPhoneNumbersByFirestation() throws Exception {
		// ARRANGE
		int firestation = 0;
		Mockito.doNothing().when(endpointsURLsService).showPhoneNumbersByFirestation(firestation);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/phoneAlert?firestation=0")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Test
	public void showPersonsByAddress() throws Exception {
		// ARRANGE
		String address = "AAAA";
		Mockito.doNothing().when(endpointsURLsService).showPersonsByAddress(address);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/fire?address=AAAA")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Test
	public void showPersonsAddressByFirestation() throws Exception {
		// ARRANGE
		int firestation = 0;
		Mockito.doNothing().when(endpointsURLsService).showPersonsAddressByFirestation(firestation);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/flood/stations?stations=0")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Test
	public void showPersonInfoByPerson() throws Exception {
		// ARRANGE
		String firstName = "AAAA";
		String lastName = "BBBB";
		Mockito.doNothing().when(endpointsURLsService).showPersonInfoByPerson(firstName, lastName);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/personInfo?firstName=AAAA&lastName=BBBB")).andDo(print())
				.andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Test
	public void showMailsByCity() throws Exception {
		// ARRANGE
		String city = "AAAA";
		Mockito.doNothing().when(endpointsURLsService).showMailsByCity(city);

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/communityEmail?city=AAAA")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

}
