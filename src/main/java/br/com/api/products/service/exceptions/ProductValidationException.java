package br.com.api.products.service.exceptions;

public class ProductValidationException extends RuntimeException {
	
	private static final String PRODUCT_VALIDATION = "Product validation: {}";

	public ProductValidationException(String errorMessage) {
		super(String.format(PRODUCT_VALIDATION, errorMessage));
	}
}