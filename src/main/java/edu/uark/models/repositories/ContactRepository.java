package edu.uark.models.repositories;

import java.sql.SQLException;

import edu.uark.dataaccess.repository.BaseRepository;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.dataaccess.repository.helpers.PostgreFunctionType;
import edu.uark.dataaccess.repository.helpers.SQLComparisonType;
import edu.uark.dataaccess.repository.helpers.where.WhereClause;
import edu.uark.dataaccess.repository.helpers.where.WhereContainer;
import edu.uark.models.entities.ContactEntity;
import edu.uark.models.entities.fieldnames.ContactFieldNames;
import edu.uark.models.repositories.interfaces.ContactRepositoryInterface;

public class ContactRepository extends BaseRepository<ContactEntity> implements ContactRepositoryInterface {
	@Override
	public ContactEntity byPhoneNumber(String phoneNumber) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					postgreFunction(PostgreFunctionType.LOWER).
					table(this.primaryTable).
					fieldName(ContactFieldNames.PHONE_NUMBER).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, phoneNumber.toLowerCase());
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
