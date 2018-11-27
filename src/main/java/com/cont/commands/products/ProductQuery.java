package com.cont.commands.products;

import java.util.UUID;

import com.cont.commands.ResultCommandInterface;
import com.cont.controllers.exceptions.NotFoundException;
import com.cont.models.api.Product;
import com.cont.models.entities.ProductEntity;
import com.cont.models.repositories.ProductRepository;
import com.cont.models.repositories.interfaces.ProductRepositoryInterface;

public class ProductQuery implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		ProductEntity productEntity = this.productRepository.get(this.productId);
		if (productEntity != null) {
			return new Product(productEntity);
		} else {
			throw new NotFoundException("Product");
		}
	}

	//Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public ProductQuery setProductId(UUID productId) {
		this.productId = productId;
		return this;
	}
	
	private ProductRepositoryInterface productRepository;
	public ProductRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ProductQuery setProductRepository(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ProductQuery() {
		this.productRepository = new ProductRepository();
	}
}
