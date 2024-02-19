package br.com.api.products.resource;

public class ProductResponse {
    
    private long id;
    private String name;
    private String brand;

    public ProductResponse() {
    }

    public ProductResponse(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public ProductResponse(long id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    
}
