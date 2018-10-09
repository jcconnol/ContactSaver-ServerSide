package edu.uark.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.commands.contacts.ContactByPhoneNumberQuery;
import edu.uark.commands.contacts.ContactCreateCommand;
import edu.uark.commands.contacts.ContactDeleteCommand;
import edu.uark.commands.contacts.ContactQuery;
import edu.uark.commands.contacts.ContactUpdateCommand;
import edu.uark.commands.contacts.ContactsQuery;
import edu.uark.models.api.Contact;

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

	@RequestMapping(value = "/byLookupCode/{contactLookupCode}", method = RequestMethod.GET)
	public Contact getContactByLookupCode(@PathVariable String contactLookupCode) {
		return (new ContactByPhoneNumberQuery()).
			setPhoneNumber(contactLookupCode).
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
