package br.com.api.products.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.products.service.ProductService;
import br.com.api.products.service.exceptions.ProductNotFoundException;
import br.com.api.products.service.exceptions.ProductValidationException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
// TODO: To add Handle Exception https://www.baeldung.com/exception-handling-for-rest-with-spring
public class ProductResource {

	private ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductResponse>> findAll() {

		List<ProductResponse> products = productService.findAll();

		return ResponseEntity.ok(products);
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable Long id) throws ProductNotFoundException {

		ProductResponse productResponse = productService.findById(id);

		return ResponseEntity.ok(productResponse);
	}

	@PostMapping(value = "/products")
	public ResponseEntity<ProductResponse> insert(@RequestBody ProductRequest productRequest)
			throws ProductValidationException {

		ProductResponse productResponse = productService.insert(productRequest);

		return ResponseEntity.ok(productResponse);
	}

	@PutMapping(value = "/products")
	public ResponseEntity<ProductResponse> updateById(@RequestBody ProductRequest productRequest, @PathVariable Long id)
			throws ProductNotFoundException, ProductValidationException {

		ProductResponse product = this.productService.update(new ProductRequest(id, productRequest));

		return ResponseEntity.ok(product);
	}

	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<ProductResponse> deleteById(@PathVariable Long id) {
		this.productService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
