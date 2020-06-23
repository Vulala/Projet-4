package com.safetynet.safetynetalerts.service.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.safetynet.safetynetalerts.model.MedicalRecord;

@JsonFilter("EndPointsFilters")
public class MedicalRecordCRUDObject {

	private List<MedicalRecord> listMedicalRecords;
	private MedicalRecord medicalRecord;

	public MedicalRecordCRUDObject(List<MedicalRecord> listMedicalRecords) {
		super();
		this.listMedicalRecords = listMedicalRecords;
	}

	public MedicalRecordCRUDObject(MedicalRecord medicalRecord) {
		super();
		this.medicalRecord = medicalRecord;
	}

	public List<MedicalRecord> getListMedicalRecords() {
		return listMedicalRecords;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

}
