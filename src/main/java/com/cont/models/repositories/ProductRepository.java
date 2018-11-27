package com.cont.models.repositories;

import java.sql.SQLException;

import com.cont.dataaccess.repository.BaseRepository;
import com.cont.dataaccess.repository.DatabaseTable;
import com.cont.dataaccess.repository.helpers.PostgreFunctionType;
import com.cont.dataaccess.repository.helpers.SQLComparisonType;
import com.cont.dataaccess.repository.helpers.where.WhereClause;
import com.cont.dataaccess.repository.helpers.where.WhereContainer;
import com.cont.models.entities.ProductEntity;
import com.cont.models.entities.fieldnames.ProductFieldNames;
import com.cont.models.repositories.interfaces.ProductRepositoryInterface;

public class ProductRepository extends BaseRepository<ProductEntity> implements ProductRepositoryInterface {
	@Override
	public ProductEntity byLookupCode(String lookupCode) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					postgreFunction(PostgreFunctionType.LOWER).
					table(this.primaryTable).
					fieldName(ProductFieldNames.LOOKUP_CODE).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, lookupCode.toLowerCase());
				} catch (SQLException e) {}

				return ps;
			}
		);
	}
	
	@Override
	public ProductEntity createOne() {
		return new ProductEntity();
	}
	
	public ProductRepository() {
		super(DatabaseTable.PRODUCT);
	}
}
