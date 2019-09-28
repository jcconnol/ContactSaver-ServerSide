package com.cont.commands.queries;

import java.util.UUID;

import com.cont.contacts.VoidCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.entities.ContactEntity;
import com.cont.models.repositories.ContactRepository;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		ContactEntity contactEntity = this.contactRepository.get(this.contactName);
		if (contactEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Contact");
		}
		
		contactEntity.delete();
	}

	//Properties
	private String contactName;
	public String getContactName() {
		return this.contactName;
	}
	public ContactDeleteCommand setContactName(String contactName) {
		this.contactName = contactName;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactDeleteCommand setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactDeleteCommand() {
		this.contactRepository = new ContactRepository();
	}
}
