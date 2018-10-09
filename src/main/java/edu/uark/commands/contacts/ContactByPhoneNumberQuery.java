package edu.uark.commands.contacts;

import org.apache.commons.lang3.StringUtils;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.controllers.exceptions.UnprocessableEntityException;
import edu.uark.models.api.Contact;
import edu.uark.models.entities.ContactEntity;
import edu.uark.models.repositories.ContactRepository;
import edu.uark.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactByPhoneNumberQuery implements ResultCommandInterface<Contact> {
	@Override
	public Contact execute() {
		if (StringUtils.isBlank(this.phoneNumber)) {
			throw new UnprocessableEntityException("lookupCode");
		}
		
		ContactEntity contactEntity = this.contactRepository.byPhoneNumber(this.phoneNumber);
		if (contactEntity != null) {
			return new Contact(contactEntity);
		} else {
			throw new NotFoundException("phoneNumber");
		}
	}

	//Properties
	private String phoneNumber;
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public ContactByPhoneNumberQuery setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactByPhoneNumberQuery setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactByPhoneNumberQuery() {
		this.contactRepository = new ContactRepository();
	}
}
