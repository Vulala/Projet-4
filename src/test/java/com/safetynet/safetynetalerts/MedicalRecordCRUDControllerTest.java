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

import com.safetynet.safetynetalerts.controller.MedicalRecordCRUDController;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.MedicalRecordCRUDService;
import com.safetynet.safetynetalerts.service.object.MedicalRecordCRUDObject;

@WebMvcTest(MedicalRecordCRUDController.class)
public class MedicalRecordCRUDControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordCRUDService medicalRecordCRUDService;

	@Test
	public void showAllMedicalRecords() throws Exception {
		// ARRANGE
		List<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
		MedicalRecordCRUDObject medicalRecordCRUDObject = new MedicalRecordCRUDObject(listMedicalRecord);
		when(medicalRecordCRUDService.findAll()).thenReturn(medicalRecordCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/medicalRecord")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordCRUDService, times(1)).findAll();
	}

	@Test
	public void showMedicalRecordById() throws Exception {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord();
		MedicalRecordCRUDObject medicalRecordCRUDObject = new MedicalRecordCRUDObject(medicalRecord);
		when(medicalRecordCRUDService.findById(any(String.class))).thenReturn(medicalRecordCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(get("/medicalRecord/Someone")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordCRUDService, times(1)).findById(any(String.class));
	}

	@Test
	public void addMedicalRecord() throws Exception {
		// ARRANGE
		List<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
		MedicalRecordCRUDObject medicalRecordCRUDObject = new MedicalRecordCRUDObject(listMedicalRecord);
		when(medicalRecordCRUDService.save(any(MedicalRecord.class))).thenReturn(medicalRecordCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(
				post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"AAAA\"}"))
				.andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 201);
		verify(medicalRecordCRUDService, times(1)).save(any(MedicalRecord.class));
	}

	@Test
	public void updateMedicalRecord() throws Exception {
		// ARRANGE
		MedicalRecord medicalRecord = new MedicalRecord();
		MedicalRecordCRUDObject medicalRecordCRUDObject = new MedicalRecordCRUDObject(medicalRecord);
		when(medicalRecordCRUDService.update(any(String.class), (any(MedicalRecord.class))))
				.thenReturn(medicalRecordCRUDObject);

		// ACT
		MvcResult mvcResult = mockMvc.perform(put("/medicalRecord/Someone").contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"AAAA\"}")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordCRUDService, times(1)).update(any(String.class), (any(MedicalRecord.class)));
	}

	@Test
	public void deleteMedicalRecord() throws Exception {
		// ARRANGE
		Mockito.doNothing().when(medicalRecordCRUDService).deleteById("firstNameAndlastName");

		// ACT
		MvcResult mvcResult = mockMvc.perform(delete("/medicalRecord/Someone")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(medicalRecordCRUDService, times(1)).deleteById(any(String.class));
	}
}
