package br.com.api.products.service.exceptions;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException() {
		super("Product not found.");
	}
}