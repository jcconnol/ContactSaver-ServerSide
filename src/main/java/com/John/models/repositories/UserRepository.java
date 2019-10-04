package com.John.models.repositories;

import java.sql.SQLException;

import com.John.dataaccess.repository.BaseRepository;
import com.John.dataaccess.repository.DatabaseTable;
import com.John.dataaccess.repository.helpers.SQLComparisonType;
import com.John.dataaccess.repository.helpers.SQLConditionalType;
import com.John.dataaccess.repository.helpers.where.WhereClause;
import com.John.dataaccess.repository.helpers.where.WhereContainer;
import com.John.models.entities.UserEntity;
import com.John.models.entities.fieldnames.UserFieldNames;
import com.John.models.repositories.interfaces.UserRepositoryInterface;

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
