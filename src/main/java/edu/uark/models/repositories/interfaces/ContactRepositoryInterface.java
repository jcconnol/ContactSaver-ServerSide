package edu.uark.models.repositories.interfaces;

import edu.uark.dataaccess.repository.BaseRepositoryInterface;
import edu.uark.models.entities.ContactEntity;

public interface ContactRepositoryInterface extends BaseRepositoryInterface<ContactEntity> {
	ContactEntity byPhoneNumber(String phoneNumber);
}
