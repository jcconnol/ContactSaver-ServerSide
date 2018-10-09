package edu.uark.commands.contacts;

import java.util.List;
import java.util.stream.Collectors;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Contact;
import edu.uark.models.repositories.ContactRepository;
import edu.uark.models.repositories.interfaces.ContactRepositoryInterface;

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
