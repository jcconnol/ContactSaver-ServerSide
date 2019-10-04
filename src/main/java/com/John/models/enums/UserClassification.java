package com.John.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserClassification {
	NOT_DEFINED(0),
	GENERAL_MANAGER(1),
	SHIFT_MANAGER(2),
	CASHIER(3);
	
	public int getValue() {
		return value;
	}

	public static UserClassification map(int key) {
		if (valueMap == null) {
			valueMap = new HashMap<Integer, UserClassification>();

			for (UserClassification status : UserClassification.values()) {
				valueMap.put(status.getValue(), status);
			}
		}
		
		return (valueMap.containsKey(key) ? valueMap.get(key) : UserClassification.NOT_DEFINED);
	}
	
	private int value;

	private static Map<Integer, UserClassification> valueMap = null;

	private UserClassification(int value) {
		this.value = value;
	}
}
