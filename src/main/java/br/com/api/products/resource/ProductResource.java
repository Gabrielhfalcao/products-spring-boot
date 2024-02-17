package br.com.api.products.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.api.products.model.Product;
import br.com.api.products.service.ProductService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class ProductResource {
	
	@Autowired
	private ProductService ps;
	
	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> findAll(){
		List<Product> products = ps.findAll();
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return ps.findById(id);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> insert(@RequestBody Product product){
		return ps.insert(product);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateById(@RequestBody Product product, @PathVariable Long id){
		return ps.updateById(product, id);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return ps.deleteById(id);
	}
	
}
