package com.cont.commands.products;

import java.util.UUID;

import com.cont.commands.VoidCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.entities.ProductEntity;
import com.cont.models.repositories.ProductRepository;
import com.cont.models.repositories.interfaces.ProductRepositoryInterface;

public class ProductDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
		ProductEntity productEntity = this.productRepository.get(this.productId);
		if (productEntity == null) { //No record with the associated record ID exists in the database.
			throw new NotFoundException("Product");
		}
		
		productEntity.delete();
	}

	//Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ProductDeleteCommand setProductId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private ProductRepositoryInterface productRepository;
	public ProductRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ProductDeleteCommand setProductRepository(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ProductDeleteCommand() {
		this.productRepository = new ProductRepository();
	}
}
