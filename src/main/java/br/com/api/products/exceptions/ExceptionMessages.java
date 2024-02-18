package br.com.api.products.exceptions;

public enum ExceptionMessages {

    PRODUCT_NOT_FOUND("PROD_V_00001", "Product not found"),
    PRODUCT_NAME_CANT_BE_EMPTY("PROD_V_00002", "Product Name can not be empty"),
    PRODUCT_BRAND_CANT_BE_EMPTY("PROD_V_00003", "Product Brand can not be empty"),
    PRODUCT_ID_INVALID("PROD_V_00004", "Product ID is invalid"),
    ;

    private String code;
    private String message;

    ExceptionMessages(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("Validation {} - {}", this.code, this.message);
    }
}