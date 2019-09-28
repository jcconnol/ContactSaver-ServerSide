package com.cont.models.repositories;

import java.sql.SQLException;
import java.util.Collection;

import com.cont.dataaccess.repository.BaseRepository;
import com.cont.dataaccess.repository.DatabaseTable;
import com.cont.dataaccess.repository.helpers.PostgreFunctionType;
import com.cont.dataaccess.repository.helpers.SQLComparisonType;
import com.cont.dataaccess.repository.helpers.where.WhereClause;
import com.cont.dataaccess.repository.helpers.where.WhereContainer;
import com.cont.models.entities.ContactEntity;
import com.cont.models.entities.fieldnames.ContactFieldNames;
import com.cont.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactRepository extends BaseRepository<ContactEntity> implements ContactRepositoryInterface {
	@Override
	public ContactEntity byName(String name) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					postgreFunction(PostgreFunctionType.LOWER).
					table(this.primaryTable).
					fieldName(ContactFieldNames.NAME).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, name.toLowerCase());
				} catch (SQLException e) {}

				return ps;
			}
		);
	}
	
	@Override
	public ContactEntity createOne() {
		return new ContactEntity();
	}
	
	public ContactRepository() {
		super(DatabaseTable.CONTACT);
	}
}
