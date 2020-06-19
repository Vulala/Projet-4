package com.safetynet.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.safetynet.safetynetalerts.controller.FirestationCRUDController;
import com.safetynet.safetynetalerts.dao.FirestationDAO;
import com.safetynet.safetynetalerts.model.Firestation;

@WebMvcTest(FirestationCRUDController.class)
public class FirestationCRUDControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FirestationDAO firestationDAO;

	@Test
	public void showAllFirestations() throws Exception {
		// ARRANGE
		List<Firestation> listFirestation = new ArrayList<Firestation>();
		when(firestationDAO.findAll()).thenReturn(listFirestation);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/firestation/")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationDAO, times(1)).findAll();
	}

	@Test
	public void showFirestationById() throws Exception {
		// ARRANGE
		Firestation firestation = new Firestation();
		String address = "AAAA";
		firestation.setAddress(address);
		when(firestationDAO.findById(any(String.class))).thenReturn(firestation);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/firestation/Somewhere")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationDAO, times(1)).findById(any(String.class));
	}

	@Disabled
	@Test
	public void addFirestation() throws Exception {
		// ARRANGE
		List<Firestation> listFirestation = new ArrayList<Firestation>();
		Firestation firestation = new Firestation();
		when(firestationDAO.save(firestation)).thenReturn(listFirestation);

		// ACT
		MvcResult mvcResult = mockMvc
				.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"AAAA\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 201);
		verify(firestationDAO, times(1)).save(firestation);
	}

	@Test
	public void updateFirestation() throws Exception {
		// ARRANGE
		Firestation firestation = new Firestation();
		String address = "AAAA";
		firestation.setAddress(address);
		when(firestationDAO.update(any(String.class), (any(Firestation.class)))).thenReturn(firestation);

		// ACT
		MvcResult mvcResult = mockMvc.perform(
				put("/firestation/Somewhere").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"AAAA\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationDAO, times(1)).update(any(String.class), (any(Firestation.class)));
	}

	@Test
	public void deleteFirestation() throws Exception {
		// ARRANGE
		Mockito.doNothing().when(firestationDAO).deleteById("firstNameAndlastName");

		// ACT
		MvcResult mvcResult = mockMvc.perform(delete("/firestation/Somewhere")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationDAO, times(1)).deleteById(any(String.class));
	}
}
