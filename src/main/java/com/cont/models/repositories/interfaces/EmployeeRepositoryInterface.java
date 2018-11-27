package com.cont.models.repositories.interfaces;

import com.cont.dataaccess.repository.BaseRepositoryInterface;
import com.cont.models.entities.EmployeeEntity;
import com.cont.models.enums.EmployeeClassification;

public interface EmployeeRepositoryInterface extends BaseRepositoryInterface<EmployeeEntity> {
	boolean employeeIdExists(String employeeId);
	EmployeeEntity byEmployeeId(String employeeId);
	int activeCountByClassification(EmployeeClassification employeeClassification);
}
