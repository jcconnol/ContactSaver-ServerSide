package edu.uark.dataaccess.repository;

public enum DatabaseTable {
	NONE(""),
	CONTACT("contact"),
	EMPLOYEE("employee");
	
	public String getLabel() {
		return label;
	}
	
	private final String label;
	
	private DatabaseTable(String label) {
		this.label = label;
	}
}
