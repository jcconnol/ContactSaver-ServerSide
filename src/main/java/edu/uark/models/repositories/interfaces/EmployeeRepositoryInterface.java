package edu.uark.models.repositories.interfaces;

import edu.uark.dataaccess.repository.BaseRepositoryInterface;
import edu.uark.models.entities.EmployeeEntity;
import edu.uark.models.enums.EmployeeClassification;

public interface EmployeeRepositoryInterface extends BaseRepositoryInterface<EmployeeEntity> {
	boolean employeeUsernameExists(String employeeUsername);
	EmployeeEntity byEmployeeUsername(String employeeUsername);
	int activeCountByClassification(EmployeeClassification employeeClassification);
}
