package br.com.api.products.resource;

public class ProductRequest {
	
    private Long id;
    private String name;
	private String brand;
    
    public ProductRequest() {
    }

    public ProductRequest(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public ProductRequest(Long id, String name, String brand) {
        this(name, brand);
        this.id = id;
    }

    // This method is to support the update api 
    public ProductRequest(Long id, ProductRequest productRequest) {
        this(id, productRequest.getName(), productRequest.getBrand());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

}
