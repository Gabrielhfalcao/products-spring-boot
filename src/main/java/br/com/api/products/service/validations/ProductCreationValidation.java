package br.com.api.products.service.validations;

import br.com.api.products.exceptions.ExceptionMessages;
import br.com.api.products.exceptions.ProductException;
import br.com.api.products.resource.ProductRequest;

public class ProductCreationValidation {

    public void validate(ProductRequest productRequest) throws ProductException {

        if (productRequest.getName() == null || productRequest.getName().isEmpty()) {
            throw new ProductException(ExceptionMessages.PRODUCT_NAME_CANT_BE_EMPTY.code(),
                    ExceptionMessages.PRODUCT_NAME_CANT_BE_EMPTY.message());
        }

        if (productRequest.getBrand() == null || productRequest.getBrand().isEmpty()) {
            throw new ProductException(ExceptionMessages.PRODUCT_BRAND_CANT_BE_EMPTY.code(),
                    ExceptionMessages.PRODUCT_BRAND_CANT_BE_EMPTY.message());
        }
    }
}
