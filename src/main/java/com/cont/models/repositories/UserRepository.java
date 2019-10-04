package com.cont.models.repositories;

import java.sql.SQLException;

import com.cont.dataaccess.repository.BaseRepository;
import com.cont.dataaccess.repository.DatabaseTable;
import com.cont.dataaccess.repository.helpers.SQLComparisonType;
import com.cont.dataaccess.repository.helpers.SQLConditionalType;
import com.cont.dataaccess.repository.helpers.where.WhereClause;
import com.cont.dataaccess.repository.helpers.where.WhereContainer;
import com.cont.models.entities.UserEntity;
import com.cont.models.entities.fieldnames.UserFieldNames;
import com.cont.models.repositories.interfaces.UserRepositoryInterface;

public class UserRepository extends BaseRepository<UserEntity> implements UserRepositoryInterface {
	@Override
	public boolean userIdExists(String userId) {
		return this.existsWhere(
			new WhereContainer(
				(new WhereClause()).
					table(this.primaryTable).
					fieldName(UserFieldNames.USER_ID).
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
	public UserEntity byUserId(String userId) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					table(this.primaryTable).
					fieldName(UserFieldNames.USER_ID).
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
	public UserEntity createOne() {
		return new UserEntity();
	}
	
	public UserRepository() {
		super(DatabaseTable.USER);
	}
}
