package com.safetynet.safetynetalerts.model;

public class AdultsAndChildren {

	private long adults;
	private long children;

	public AdultsAndChildren(long adults, long children) {
		this.adults = adults;
		this.children = children;
	}

	public long getAdults() {
		return adults;
	}

	public long getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "AdultsAndChildren [adults=" + adults + ", children=" + children + "]";
	}

}
