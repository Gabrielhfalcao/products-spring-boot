package br.com.api.products.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.api.products.model.Product;
import br.com.api.products.repository.ProductRepository;
import br.com.api.products.resource.ProductRequest;
import br.com.api.products.resource.ProductResponse;
import br.com.api.products.service.exceptions.ProductException;
import br.com.api.products.service.exceptions.ProductValidationException;
import br.com.api.products.service.validations.ProductCreationValidation;
import br.com.api.products.service.validations.ValidationMessages;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductResponse> findAll() {

		return productRepository.findAll().stream()
				.map(p -> {
					return new ProductResponse(p.getId(), p.getName(), p.getBrand());
				})
				.collect(Collectors.toList());
	}

	public ProductResponse findById(long id) throws ProductException {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductException(ValidationMessages.PRODUCT_NOT_FOUND.code(),
						ValidationMessages.PRODUCT_NOT_FOUND.message()));

		return new ProductResponse(product.getId(), product.getName(), product.getBrand());
	}

	public ProductResponse insert(ProductRequest productRequest) throws ProductValidationException {

		// TODO: Replace use the of "new" for dependencu inversion.
		new ProductCreationValidation().validate(productRequest);

		Product productPersisted = productRepository
				.save(new Product(productRequest.getName(), productRequest.getBrand()));

		return new ProductResponse(productPersisted.getId(), productPersisted.getName(), productPersisted.getBrand());
	}

	public ProductResponse update(ProductRequest productRequest)
			throws ProductException, ProductValidationException {

		if (productRequest.getId() == null || productRequest.getId() <= 0) {
			throw new ProductValidationException("Product ID invalid");
		}

		Product product = productRepository.findById(productRequest.getId().longValue())
				.orElseThrow(() -> new ProductException(ValidationMessages.PRODUCT_NOT_FOUND.code(),
						ValidationMessages.PRODUCT_NOT_FOUND.message()));

		// TODO: No update if the values are null or empty
		product.setName(productRequest.getName());
		product.setBrand(productRequest.getBrand());

		this.productRepository.save(product);

		return new ProductResponse(product.getId(), product.getName(), product.getBrand());
	}

	public void deleteById(long id) {

		productRepository.findById(id)
				.orElseThrow(() -> new ProductException(ValidationMessages.PRODUCT_NOT_FOUND.code(),
						ValidationMessages.PRODUCT_NOT_FOUND.message()));

		// TODO: Perhaps, we can deactivate the product instead of delete using an extra
		// column (active) in the table product.
		// product.setActive(false);
		// productRepository.save(product);

		productRepository.deleteById(id);
	}
}
