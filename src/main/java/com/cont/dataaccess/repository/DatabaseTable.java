package com.cont.dataaccess.repository;

public enum DatabaseTable {
	NONE(""),
	CONTACT("contact"), 
	USER("users");
	
	public String getLabel() {
		return label;
	}
	
	private final String label;
	
	private DatabaseTable(String label) {
		this.label = label;
	}
}
