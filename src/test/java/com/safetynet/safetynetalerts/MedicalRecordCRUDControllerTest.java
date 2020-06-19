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

import com.safetynet.safetynetalerts.controller.MedicalRecordCRUDController;
import com.safetynet.safetynetalerts.dao.MedicalRecordDAO;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@WebMvcTest(MedicalRecordCRUDController.class)
public class MedicalRecordCRUDControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordDAO medicalRecordDAO;

	@Test
	public void showAllMedicalRecords() throws Exception {
		// ARRANGE
		List<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
		when(medicalRecordDAO.findAll()).thenReturn(listMedicalRecord);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/medicalRecord")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordDAO, times(1)).findAll();
	}

	@Test
	public void showMedicalRecordById() throws Exception {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord();
		String firstName = "AAAA";
		medicalRecord.setFirstName(firstName);
		when(medicalRecordDAO.findById(any(String.class))).thenReturn(medicalRecord);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/medicalRecord/Someone")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordDAO, times(1)).findById(any(String.class));
	}

	@Disabled
	@Test
	public void addMedicalRecord() throws Exception {
		// ARRANGE
		List<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
		MedicalRecord medicalRecord = new MedicalRecord();
		when(medicalRecordDAO.save(medicalRecord)).thenReturn(listMedicalRecord);

		// ACT
		MvcResult mvcResult = mockMvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"AAAA\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 201);
		verify(medicalRecordDAO, times(1)).save(medicalRecord);
	}

	@Test
	public void updateMedicalRecord() throws Exception {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord();
		String firstName = "AAAA";
		medicalRecord.setFirstName(firstName);
		when(medicalRecordDAO.update(any(String.class), (any(MedicalRecord.class)))).thenReturn(medicalRecord);

		// ACT
		MvcResult mvcResult = mockMvc.perform(put("/medicalRecord/Someone").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"AAAA\"}")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordDAO, times(1)).update(any(String.class), (any(MedicalRecord.class)));
	}

	@Test
	public void deleteMedicalRecord() throws Exception {
		// ARRANGE
		Mockito.doNothing().when(medicalRecordDAO).deleteById("firstNameAndlastName");

		// ACT
		MvcResult mvcResult = mockMvc.perform(delete("/medicalRecord/Someone")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordDAO, times(1)).deleteById(any(String.class));
	}
}
