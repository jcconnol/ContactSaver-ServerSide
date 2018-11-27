package com.cont.commands.products;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.controllers.exceptions.UnprocessableEntityException;
import com.cont.models.api.Product;
import com.cont.models.entities.ProductEntity;
import com.cont.models.repositories.ProductRepository;
import com.cont.models.repositories.interfaces.ProductRepositoryInterface;

public class ProductUpdateCommand implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		//Validations
		if (StringUtils.isBlank(this.apiProduct.getLookupCode())) {
			throw new UnprocessableEntityException("lookupcode");
		}

		ProductEntity productEntity = this.productRepository.get(this.productId);
		if (productEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Product");
		}
		
		this.apiProduct = productEntity.synchronize(this.apiProduct); //Synchronize any incoming changes for UPDATE to the database.
		
		productEntity.save(); //Write, via an UPDATE, any changes to the database.
		
		return this.apiProduct;
	}

	//Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ProductUpdateCommand setProductId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private Product apiProduct;
	public Product getApiProduct() {
		return this.apiProduct;
	}
	public ProductUpdateCommand setApiProduct(Product apiProduct) {
		this.apiProduct = apiProduct;
		return this;
	}
	
	private ProductRepositoryInterface productRepository;
	public ProductRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ProductUpdateCommand setProductRepository(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ProductUpdateCommand() {
		this.productRepository = new ProductRepository();
	}
}
