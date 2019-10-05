package com.John.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.John.commands.contacts.ContactByNameQuery;
import com.John.commands.contacts.ContactCreateCommand;
import com.John.commands.contacts.ContactDeleteCommand;
import com.John.commands.contacts.ContactQuery;
import com.John.commands.contacts.ContactUpdateCommand;
import com.John.commands.contacts.ContactsQuery;
import com.John.models.api.Contact;

@RestController
@RequestMapping(value = "/api/contact")
public class ContactRestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Contact> getContacts(){//@PathVariable String ownerId) {
		return (new ContactsQuery()).
			//setOwnerId(ownerId).
			execute();
	}

	@RequestMapping(value = "/byName/{contactName}", method = RequestMethod.GET)
	public Contact getContactByName(@PathVariable String contactName) {
		return (new ContactByNameQuery()).
			setName(contactName).
			execute();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Contact createContact(@RequestBody Contact contact) {
		return (new ContactCreateCommand()).
			setApiContact(contact).
			execute();
	}
	
	@RequestMapping(value = "/{contactId}", method = RequestMethod.PUT)
	public Contact updateContact(@PathVariable UUID contactId, @RequestBody Contact contact) {
		return (new ContactUpdateCommand()).
			setContactId(contactId).
			setApiContact(contact).
			execute();
	}
	
	@RequestMapping(value = "/{contactId}", method = RequestMethod.DELETE)
	public void deleteContact(@PathVariable UUID contactId) {
		(new ContactDeleteCommand()).
			setContactId(contactId).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (ContactRestController)";
	}
}
