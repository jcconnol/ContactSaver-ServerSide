package com.John.commands.contacts;

import java.util.UUID;

import com.John.commands.VoidCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.models.entities.ContactEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		ContactEntity contactEntity = this.contactRepository.get(this.contactId);
		if (contactEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Contact");
		}
		
		contactEntity.delete();
	}

	//Properties
	private UUID contactId;
	public UUID getContactId() {
		return this.contactId;
	}
	public ContactDeleteCommand setContactId(UUID contactId) {
		this.contactId = contactId;
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
