package com.John.models.repositories;

import java.sql.SQLException;
import java.util.UUID;

import com.John.dataaccess.repository.BaseRepository;
import com.John.dataaccess.repository.DatabaseTable;
import com.John.dataaccess.repository.helpers.PostgreFunctionType;
import com.John.dataaccess.repository.helpers.SQLComparisonType;
import com.John.dataaccess.repository.helpers.where.WhereClause;
import com.John.dataaccess.repository.helpers.where.WhereContainer;
import com.John.models.entities.ContactEntity;
import com.John.models.entities.fieldnames.ContactFieldNames;
import com.John.models.repositories.interfaces.ContactRepositoryInterface;

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
	
	public ContactEntity byUserId(UUID userId) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					postgreFunction(PostgreFunctionType.LOWER).
					table(this.primaryTable).
					fieldName(ContactFieldNames.ID).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, userId);
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