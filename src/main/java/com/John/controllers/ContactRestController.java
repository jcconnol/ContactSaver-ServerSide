package com.John.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.John.commands.contacts.ContactByLookupCodeQuery;
import com.John.commands.contacts.ContactCreateCommand;
import com.John.commands.contacts.ContactDeleteCommand;
import com.John.commands.contacts.ContactQuery;
import com.John.commands.contacts.ContactUpdateCommand;
import com.John.commands.contacts.ContactsQuery;
import com.John.models.api.Contact;

@RestController
@RequestMapping(value = "/api/product")
public class ContactRestController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Contact> getProducts() {
		return (new ContactsQuery()).execute();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Contact getProduct(@PathVariable UUID productId) {
		return (new ContactQuery()).
			setProductId(productId).
			execute();
	}

	@RequestMapping(value = "/byLookupCode/{productLookupCode}", method = RequestMethod.GET)
	public Contact getProductByLookupCode(@PathVariable String productLookupCode) {
		return (new ContactByLookupCodeQuery()).
			setLookupCode(productLookupCode).
			execute();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Contact createProduct(@RequestBody Contact contact) {
		return (new ContactCreateCommand()).
			setApiProduct(contact).
			execute();
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public Contact updateProduct(@PathVariable UUID productId, @RequestBody Contact contact) {
		return (new ContactUpdateCommand()).
			setProductId(productId).
			setApiProduct(contact).
			execute();
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable UUID productId) {
		(new ContactDeleteCommand()).
			setProductId(productId).
			execute();
	}

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Successful test. (ProductRestController)";
	}
}
