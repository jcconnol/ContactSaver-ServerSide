package com.cont.models.repositories.interfaces;

import com.cont.dataaccess.repository.BaseRepositoryInterface;
import com.cont.models.entities.ProductEntity;

public interface ProductRepositoryInterface extends BaseRepositoryInterface<ProductEntity> {
	ProductEntity byLookupCode(String lookupCode);
}
