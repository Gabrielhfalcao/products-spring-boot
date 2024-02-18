package br.com.api.products.service.validations;

enum ValidationMessages {

    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_NAME_CANT_BE_EMPTY("Product Name can not be empty"),
    PRODUCT_BRAND_CANT_BE_EMPTY("Product Brand can not be empty")
    ;

    private String message;

    ValidationMessages(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return this.message;
    }
}