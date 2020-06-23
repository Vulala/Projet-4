package com.safetynet.safetynetalerts.service.object;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.safetynet.safetynetalerts.model.Firestation;

@JsonFilter("EndPointsFilters")
public class FirestationCRUDObject {

	private List<Firestation> listFirestations;
	private Firestation firestation;

	public FirestationCRUDObject(List<Firestation> listFirestation) {
		super();
		this.listFirestations = listFirestation;
	}

	public FirestationCRUDObject(Firestation firestation) {
		super();
		this.firestation = firestation;
	}

	public List<Firestation> getListFirestation() {
		return listFirestations;
	}

	public Firestation getFirestation() {
		return firestation;
	}

}
