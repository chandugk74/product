package com.punith.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/main")
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public List<Product> retriveAllProduct() {
		return productService.retriveAllProduct();
	}

	@GetMapping("/products/{id}")
	public Optional<Product> retriveOneProduct(@PathVariable Integer id) {
		return productService.retriveOneProduct(id);
	}

	@PostMapping("/products")
	public Product insertProduct(@RequestBody Product product) {
		return productService.insertProduct(product);
	}

	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable Integer id,@RequestBody Product product) {
		return productService.updateProduct(id,product);
	}
	
	@PutMapping("/products")
	public Product updateProductWithId(@RequestBody Product product) {
		return productService.updateProductWithId(product);
	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		 productService.deleteProduct(id);
	}
}
