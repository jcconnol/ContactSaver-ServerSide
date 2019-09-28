package com.cont.commands.queries;

import java.util.List;
import java.util.stream.Collectors;

import com.cont.contacts.ResultCommandInterface;
import com.cont.models.api.Contact;
import com.cont.models.repositories.ContactRepository;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactsQuery implements ResultCommandInterface<List<Contact>> {
	@Override
	public List<Contact> execute() {
		return this.contactRepository.
			all().
			stream().
			map(mp -> (new Contact(mp))).
			collect(Collectors.toList());
	}

	//Properties
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
