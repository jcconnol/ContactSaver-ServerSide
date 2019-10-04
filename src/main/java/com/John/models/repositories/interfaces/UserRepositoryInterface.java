package com.John.models.repositories.interfaces;

import com.John.dataaccess.repository.BaseRepositoryInterface;
import com.John.models.entities.UserEntity;

public interface UserRepositoryInterface extends BaseRepositoryInterface<UserEntity> {
	boolean userIdExists(String userId);
	UserEntity byUserId(String userId);
}
