package br.com.api.products.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.api.products.exceptions.ProductException;
import br.com.api.products.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
// TODO: To add Handle Exception
// https://www.baeldung.com/exception-handling-for-rest-with-spring
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
	public ResponseEntity<ProductResponse> findById(@PathVariable Long id) throws ProductException {

		ProductResponse productResponse = productService.findById(id);

		return ResponseEntity.ok(productResponse);
	}

	@PostMapping(value = "/products", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProductResponse> insert(@RequestBody ProductRequest productRequest) {

		ProductResponse productResponse = productService.insert(productRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
	}

	@PutMapping(value = "/products/{id}")
	public ResponseEntity<ProductResponse> updateById(@RequestBody ProductRequest productRequest, @PathVariable Long id)
			throws ProductException {

		ProductResponse product = this.productService.update(new ProductRequest(id, productRequest));

		return ResponseEntity.ok(product);
	}

	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		this.productService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
