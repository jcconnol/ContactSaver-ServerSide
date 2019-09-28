package com.cont.models.repositories.interfaces;

import com.cont.dataaccess.repository.BaseRepositoryInterface;
import com.cont.models.entities.UserEntity;

public interface UserRepositoryInterface extends BaseRepositoryInterface<UserEntity> {
	boolean userIdExists(String userId);
	UserEntity byUserId(String userId);
}
