package com.cont.commands.products;

import org.apache.commons.lang3.StringUtils;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.Product;
import com.cont.models.entities.ProductEntity;
import com.cont.models.repositories.ProductRepository;
import com.cont.models.repositories.interfaces.ProductRepositoryInterface;

public class ProductByLookupCodeQuery implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		if (StringUtils.isBlank(this.lookupCode)) {
			throw new UnprocessableEntityException("lookupcode");
		}
		
		ProductEntity productEntity = this.productRepository.byLookupCode(this.lookupCode);
		if (productEntity != null) {
			return new Product(productEntity);
		} else {
			throw new NotFoundException("Product");
		}
	}

	//Properties
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ProductByLookupCodeQuery setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private ProductRepositoryInterface productRepository;
	public ProductRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ProductByLookupCodeQuery setProductRepository(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ProductByLookupCodeQuery() {
		this.productRepository = new ProductRepository();
	}
}
