package edu.uark.commands.contacts;

import java.util.UUID;

import edu.uark.commands.VoidCommandInterface;
import edu.uark.controllers.exceptions.NotFoundException;
import edu.uark.models.entities.ContactEntity;
import edu.uark.models.repositories.ContactRepository;
import edu.uark.models.repositories.interfaces.ContactRepositoryInterface;

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
