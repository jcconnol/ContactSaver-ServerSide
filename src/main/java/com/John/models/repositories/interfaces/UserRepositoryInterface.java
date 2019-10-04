package com.John.models.repositories.interfaces;

import com.John.dataaccess.repository.BaseRepositoryInterface;
import com.John.models.entities.UserEntity;
import com.John.models.enums.UserClassification;

public interface UserRepositoryInterface extends BaseRepositoryInterface<UserEntity> {
	boolean userIdExists(String userId);
	UserEntity byUserId(String userId);
	int activeCountByClassification(UserClassification userClassification);
}
