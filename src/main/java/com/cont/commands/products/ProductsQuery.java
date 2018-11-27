package com.cont.commands.products;

import java.util.List;
import java.util.stream.Collectors;

import com.cont.commands.ResultCommandInterface;
import com.cont.models.api.Product;
import com.cont.models.repositories.ProductRepository;
import com.cont.models.repositories.interfaces.ProductRepositoryInterface;

public class ProductsQuery implements ResultCommandInterface<List<Product>> {
	@Override
	public List<Product> execute() {
		return this.productRepository.
			all().
			stream().
			map(mp -> (new Product(mp))).
			collect(Collectors.toList());
	}

	//Properties
	private ProductRepositoryInterface productRepository;
	public ProductRepositoryInterface getProductRepository() {
		return this.productRepository;
	}
	public ProductsQuery setProductRepository(ProductRepositoryInterface productRepository) {
		this.productRepository = productRepository;
		return this;
	}
	
	public ProductsQuery() {
		this.productRepository = new ProductRepository();
	}
}
