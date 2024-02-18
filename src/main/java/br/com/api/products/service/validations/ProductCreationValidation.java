package br.com.api.products.service.validations;

import br.com.api.products.resource.ProductRequest;
import br.com.api.products.service.exceptions.ProductValidationException;

public class ProductCreationValidation {

    public void validate(ProductRequest productRequest) throws ProductValidationException {

        if (productRequest.getName() == null || productRequest.getName().isEmpty()) {
            throw new ProductValidationException(ValidationMessages.PRODUCT_NAME_CANT_BE_EMPTY.toString());
        }

        if (productRequest.getBrand() == null || productRequest.getBrand().isEmpty()) {
            throw new ProductValidationException(ValidationMessages.PRODUCT_BRAND_CANT_BE_EMPTY.toString());
        }
    }
}
