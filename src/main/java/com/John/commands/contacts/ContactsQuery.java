package com.John.commands.contacts;

import java.util.List;
import java.util.stream.Collectors;

import com.John.commands.ResultCommandInterface;
import com.John.models.api.Contact;
import com.John.models.repositories.ContactRepository;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactsQuery implements ResultCommandInterface<List<Contact>> {
	@Override
	public List<Contact> execute() {
		return this.productRepository.
			all().
			stream().
			map(mp -> (new Contact(mp))).
			collect(Collectors.toList());
	}

	//Properties
	private ContactRepositoryInterface productRepository;
	public ContactRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ContactsQuery setProductRepository(ContactRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ContactsQuery() {
		this.productRepository = new ContactRepository();
	}
}
