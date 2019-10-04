package com.John.models.repositories.interfaces;

import com.John.dataaccess.repository.BaseRepositoryInterface;
import com.John.models.entities.ContactEntity;

public interface ContactRepositoryInterface extends BaseRepositoryInterface<ContactEntity> {
	ContactEntity byLookupCode(String lookupCode);
}
