package com.cont.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cont.commands.queries.ContactByNameQuery;
import com.cont.commands.queries.ContactCreateCommand;
import com.cont.commands.queries.ContactDeleteCommand;
import com.cont.commands.queries.ContactQuery;
import com.cont.commands.queries.ContactUpdateCommand;
import com.cont.commands.queries.ContactsQuery;
import com.cont.models.api.Contact;

@RestController
@RequestMapping(value = "/api/contact")
public class ContactRestController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Contact> getContacts() {
		return (new ContactsQuery()).execute();
	}

	@RequestMapping(value = "/{contactId}", method = RequestMethod.GET)
	public Contact getContact(@PathVariable UUID contactId) {
		return (new ContactQuery()).
			setContactId(contactId).
			execute();
	}

	@RequestMapping(value = "/byName/{contactName}", method = RequestMethod.GET)
	public Contact getContactByName(@PathVariable String contactName) {
		return (new ContactByNameQuery()).
			setName(contactName).
			execute();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Contact createProduct(@RequestBody Contact contact) {
		return (new ContactCreateCommand()).
			setApiContact(contact).
			execute();
	}
	
	@RequestMapping(value = "/{contactName}", method = RequestMethod.PUT)
	public Contact updateProduct(@PathVariable UUID contactName, @RequestBody Contact contact) {
		return (new ContactUpdateCommand()).
			setContactName(contactName).
			setApiContact(contact).
			execute();
	}
	
	@RequestMapping(value = "/{contactName}", method = RequestMethod.DELETE)
	public void deleteContact(@PathVariable UUID contactName) {
		(new ContactDeleteCommand()).
			setContactName(contactName).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (ContactRestController)";
	}
}
