package com.John.models.api;

public class ActiveUserCounts {
	private int activeCashierCount;
	public int getActiveCashierCount() {
		return this.activeCashierCount;
	}
	public ActiveUserCounts setActiveCashierCount(int activeCashierCount) {
		this.activeCashierCount = activeCashierCount;
		return this;
	}

	private int activeShiftManagerCount;
	public int getActiveShiftManagerCount() {
		return this.activeShiftManagerCount;
	}
	public ActiveUserCounts setActiveShiftManagerCount(int activeShiftManagerCount) {
		this.activeShiftManagerCount = activeShiftManagerCount;
		return this;
	}

	private int activeGeneralManagerCount;
	public int getActiveGeneralManagerCount() {
		return this.activeGeneralManagerCount;
	}
	public ActiveUserCounts setActiveGeneralManagerCount(int activeGeneralManagerCount) {
		this.activeGeneralManagerCount = activeGeneralManagerCount;
		return this;
	}
	
	public ActiveUserCounts() {
		this.activeCashierCount = 0;
		this.activeShiftManagerCount = 0;
		this.activeGeneralManagerCount = 0;
	}
}
