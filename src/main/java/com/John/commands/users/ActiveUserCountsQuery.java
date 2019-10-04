package com.John.commands.users;

import com.John.commands.ResultCommandInterface;
import com.John.models.api.ActiveUserCounts;
import com.John.models.enums.UserClassification;
import com.John.models.repositories.UserRepository;
import com.John.models.repositories.interfaces.UserRepositoryInterface;

public class ActiveUserCountsQuery implements ResultCommandInterface<ActiveUserCounts> {
	@Override
	public ActiveUserCounts execute() {
		return ((UserClassification.map(this.classification) == UserClassification.NOT_DEFINED)
			? this.queryAllActiveCounts()
			: this.queryActiveCountsByClassification());
	}
	
	private ActiveUserCounts queryActiveCountsByClassification() {
		ActiveUserCounts activeUserCounts = new ActiveUserCounts();
		UserClassification mappedUserClassification = UserClassification.map(this.classification);
		int activeCount = this.userRepository.activeCountByClassification(mappedUserClassification);
		
		if (mappedUserClassification == UserClassification.CASHIER) {
			activeUserCounts.setActiveCashierCount(activeCount);
		} else if (mappedUserClassification == UserClassification.SHIFT_MANAGER) {
			activeUserCounts.setActiveShiftManagerCount(activeCount);
		} else if (mappedUserClassification == UserClassification.GENERAL_MANAGER) {
			activeUserCounts.setActiveGeneralManagerCount(activeCount);
		}
		
		return activeUserCounts;
	}
	
	private ActiveUserCounts queryAllActiveCounts() {
		//Generally it would be better to run a single query, probably using a GROUP BY clause, to count all records by classification.
		return (new ActiveUserCounts()).
			setActiveCashierCount(
				this.userRepository.activeCountByClassification(UserClassification.CASHIER)
			).
			setActiveShiftManagerCount(
				this.userRepository.activeCountByClassification(UserClassification.SHIFT_MANAGER)
			).
			setActiveGeneralManagerCount(
				this.userRepository.activeCountByClassification(UserClassification.GENERAL_MANAGER)
			);
	}
	
	private int classification;
	public int getUserClassification() {
		return this.classification;
	}
	public ActiveUserCountsQuery setUserClassification(int classification) {
		this.classification = classification;
		return this;
	}
	
	private UserRepositoryInterface userRepository;
	public UserRepositoryInterface getProductRepository() {
		return this.userRepository;
	}
	public ActiveUserCountsQuery setProductRepository(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
		return this;
	}
	
	public ActiveUserCountsQuery() {
		this.userRepository = new UserRepository();
	}
}
