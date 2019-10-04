package com.cont.dataaccess.repository;

public enum DatabaseTable {
	NONE(""),
	USER("users"),
	CONTACT("contact");
	
	public String getLabel() {
		return label;
	}
	
	private final String label;
	
	private DatabaseTable(String label) {
		this.label = label;
	}
}
