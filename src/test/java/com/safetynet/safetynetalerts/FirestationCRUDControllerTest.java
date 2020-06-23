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

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.safetynet.safetynetalerts.controller.FirestationCRUDController;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.FirestationCRUDService;
import com.safetynet.safetynetalerts.service.object.FirestationCRUDObject;

@WebMvcTest(FirestationCRUDController.class)
public class FirestationCRUDControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FirestationCRUDService firestationCRUDService;

	@Test
	public void showAllFirestations() throws Exception {
		// ARRANGE
		List<Firestation> listFirestation = new ArrayList<Firestation>();
		FirestationCRUDObject firestationCRUDObject = new FirestationCRUDObject(listFirestation);
		when(firestationCRUDService.findAll()).thenReturn(firestationCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/firestation/")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationCRUDService, times(1)).findAll();
	}

	@Test
	public void showFirestationById() throws Exception {
		// ARRANGE
		Firestation firestation = new Firestation();
		FirestationCRUDObject firestationCRUDObject = new FirestationCRUDObject(firestation);
		when(firestationCRUDService.findById(any(String.class))).thenReturn(firestationCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/firestation/Somewhere")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationCRUDService, times(1)).findById(any(String.class));
	}

	@Test
	public void addFirestation() throws Exception {
		// ARRANGE
		List<Firestation> listFirestation = new ArrayList<Firestation>();
		FirestationCRUDObject firestationCRUDObject = new FirestationCRUDObject(listFirestation);
		when(firestationCRUDService.save(any(Firestation.class))).thenReturn(firestationCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc
				.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"AAAA\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 201);
		verify(firestationCRUDService, times(1)).save((any(Firestation.class)));
	}

	@Test
	public void updateFirestation() throws Exception {
		// ARRANGE
		Firestation firestation = new Firestation();
		FirestationCRUDObject firestationCRUDObject = new FirestationCRUDObject(firestation);
		when(firestationCRUDService.update(any(String.class), any(Firestation.class))).thenReturn(firestationCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(
				put("/firestation/Somewhere").contentType(MediaType.APPLICATION_JSON).content("{\"address\":\"AAAA\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationCRUDService, times(1)).update(any(String.class), any(Firestation.class));
	}

	@Test
	public void deleteFirestation() throws Exception {
		// ARRANGE
		Mockito.doNothing().when(firestationCRUDService).deleteById("firstNameAndlastName");

		// ACT
		MvcResult mvcResult = mockMvc.perform(delete("/firestation/Somewhere")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(firestationCRUDService, times(1)).deleteById(any(String.class));
	}
}
