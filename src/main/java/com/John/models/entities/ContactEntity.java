package com.John.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.John.dataaccess.entities.BaseEntity;
import com.John.dataaccess.repository.DatabaseTable;
import com.John.models.api.Contact;
import com.John.models.entities.fieldnames.ContactFieldNames;

public class ContactEntity extends BaseEntity<ContactEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.lookupCode = rs.getString(ContactFieldNames.LOOKUP_CODE);
		this.count = rs.getInt(ContactFieldNames.COUNT);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ContactFieldNames.LOOKUP_CODE, this.lookupCode);
		record.put(ContactFieldNames.COUNT, this.count);
		
		return record;
	}

	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ContactEntity setLookupCode(String lookupCode) {
		if (!StringUtils.equals(this.lookupCode, lookupCode)) {
			this.lookupCode = lookupCode;
			this.propertyChanged(ContactFieldNames.LOOKUP_CODE);
		}
		
		return this;
	}

	private int count;
	public int getCount() {
		return this.count;
	}
	public ContactEntity setCount(int count) {
		if (this.count != count) {
			this.count = count;
			this.propertyChanged(ContactFieldNames.COUNT);
		}
		
		return this;
	}
	
	public Contact synchronize(Contact apiProduct) {
		this.setCount(apiProduct.getCount());
		this.setLookupCode(apiProduct.getLookupCode());
		
		apiProduct.setId(this.getId());
		apiProduct.setCreatedOn(this.getCreatedOn());
		
		return apiProduct;
	}
	
	public ContactEntity() {
		super(DatabaseTable.CONTACT);
		
		this.count = -1;
		this.lookupCode = StringUtils.EMPTY;
	}
	
	public ContactEntity(Contact apiProduct) {
		super(DatabaseTable.CONTACT);
		
		this.count = apiProduct.getCount();
		this.lookupCode = apiProduct.getLookupCode();
	}
}
