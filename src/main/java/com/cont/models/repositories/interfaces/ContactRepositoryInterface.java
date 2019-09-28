package com.cont.models.repositories.interfaces;

import com.cont.dataaccess.repository.BaseRepositoryInterface;
import com.cont.models.entities.ContactEntity;

public interface ContactRepositoryInterface extends BaseRepositoryInterface<ContactEntity> {
	ContactEntity byName(String name);
}
