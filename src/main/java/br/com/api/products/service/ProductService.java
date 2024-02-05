package br.com.api.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.products.model.Product;
import br.com.api.products.model.ResponseModel;
import br.com.api.products.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository pr;

	@Autowired
	private ResponseModel rm;

	public List<Product> findAll() {
		List<Product> products = pr.findAll();
		return products;
	}

	public ResponseEntity<?> findById(Long id) {
		Optional<Product> product = pr.findById(id);
		if (product.isEmpty()) {
			rm.setMessage("the id not exists!");
			return new ResponseEntity<ResponseModel>(rm, HttpStatus.NO_CONTENT);
		} else {
			Product prod = product.get();
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
		}
	}

	public ResponseEntity<?> insert(Product product) {
		if (product.getName() == "") {
			rm.setMessage("the product name is mandatory");
			return new ResponseEntity<ResponseModel>(rm, HttpStatus.BAD_REQUEST);
		} else if (product.getBrand() == "") {
			rm.setMessage("the product brand name is mandatory");
			return new ResponseEntity<ResponseModel>(rm, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(pr.save(product), HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateById(Product product, Long id) {
		Optional<Product> prod = pr.findById(id);
		if (prod.isEmpty()) {
			rm.setMessage("the id not exists!");
			return new ResponseEntity<ResponseModel>(rm, HttpStatus.NO_CONTENT);
		} else {
			Product pd = pr.getReferenceById(id);
			pd.setName(product.getName());
			pd.setBrand(product.getBrand());
			return new ResponseEntity<Product>(pr.save(pd), HttpStatus.OK);
		}
	}

	public ResponseEntity<?> deleteById(Long id) {
		Optional<Product> product = pr.findById(id);
		if (product.isEmpty()) {
			rm.setMessage("the id not exists!");
			return new ResponseEntity<ResponseModel>(rm, HttpStatus.NO_CONTENT);
		} else {
			pr.deleteById(id);
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		}
	}
}
