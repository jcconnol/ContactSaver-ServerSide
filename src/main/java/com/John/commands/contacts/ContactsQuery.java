package com.John.commands.contacts;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.John.commands.ResultCommandInterface;
import com.John.controllers.exceptions.NotFoundException;
import com.John.models.api.Contact;
import com.John.models.api.User;
import com.John.models.entities.ContactEntity;
import com.John.models.entities.UserEntity;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactsQuery implements ResultCommandInterface<List<Contact>> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> execute() {
		return (List<Contact>) this.contactRepository.
			all().
			stream().
			map(mp -> (new Contact(mp))).
			collect(Collectors.groupingBy(Contact::getId,
					                      Collectors.toList()
					                      )
					);
	}
	
	//Properties
	private UUID contactId;
	public UUID getContactId() {
		return contactId;
	}
	public ContactsQuery setContactId(UUID contactId) {
		this.contactId = contactId;
		return this;
	}
	
	private ContactRepositoryInterface contactRepository;
	public ContactRepositoryInterface getContactRepository() {
		return this.contactRepository;
	}
	public ContactsQuery setContactRepository(ContactRepositoryInterface contactRepository) {
		this.contactRepository = contactRepository;
		return this;
	}
	
	public ContactsQuery() {
		this.contactRepository = new ContactRepository();
	}
}
