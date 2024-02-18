package br.com.api.products.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.products.model.Product;
import br.com.api.products.repository.ProductRepository;
import br.com.api.products.resource.ProductRequest;
import br.com.api.products.resource.ProductResponse;
import br.com.api.products.service.exceptions.ProductNotFoundException;
import br.com.api.products.service.exceptions.ProductValidationException;
import br.com.api.products.service.validations.ProductCreationValidation;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductResponse> findAll() {

		return productRepository.findAll().stream()
				.map(p -> {
					return new ProductResponse(p.getName(), p.getBrand());
				})
				.collect(Collectors.toList());
	}

	public ProductResponse findById(long id) throws ProductNotFoundException {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException());

		return new ProductResponse(product.getName(), product.getBrand());
	}

	public ProductResponse insert(ProductRequest productRequest) throws ProductValidationException {

		// TODO: It is required to avoid the of the "new". Need to be injected intead of created (new)
		new ProductCreationValidation().validate(productRequest);	

		Product productPersisted = productRepository
				.save(new Product(productRequest.getName(), productRequest.getBrand()));

		return new ProductResponse(productPersisted.getName(), productPersisted.getBrand());
	}

	public ProductResponse update(ProductRequest productRequest)
			throws ProductNotFoundException, ProductValidationException {

		if (productRequest.getId() == null || productRequest.getId() <= 0) {
			throw new ProductValidationException("Product ID invalid");
		}

		Product product = productRepository.findById(productRequest.getId().longValue())
				.orElseThrow(ProductNotFoundException::new);

		product.setName(productRequest.getName());
		product.setBrand(productRequest.getBrand());

		this.productRepository.save(product);

		return new ProductResponse(product.getId(), product.getName(), product.getBrand());
	}

	public void deleteById(long id) {

		productRepository.findById(id)
				.orElseThrow(ProductNotFoundException::new);

		// TODO: Perhaps, we can deactivate the product instead of delete using an extra
		// column (active) in the table product.
		// product.setActive(false);
		// productRepository.save(product);

		productRepository.deleteById(id);
	}
}
