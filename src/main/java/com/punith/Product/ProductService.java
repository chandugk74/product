package com.punith.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductUtility productUtility;

	public List<Product> retriveAllProduct() {
		List<Product> list =  productRepository.findAll();
		// return list.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).collect(Collectors.toList());
		return list.stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
	}

	public Optional<Product> retriveOneProduct(Integer id) {
		return productRepository.findById(id);
	}

	public Product insertProduct(Product product) {
		//product.setPrice(productUtility.calculateGST(product.getPrice()));	
		float onlygst= productUtility.calculateGST(product.getPrice());
		float onlydiscount = productUtility.calculateDiscount(product.getPrice());
		product.setDiscount(onlydiscount);
		product.setGst(onlygst);
		float totalPrice = onlygst+product.getPrice()-onlydiscount;
		product.setTotalPrice(totalPrice);
		return productRepository.save(product);
	}

	public Product updateProduct(Integer id,Product product) {
		if (!productRepository.findById(id).isPresent())
		{
			throw new RuntimeException();
		}
		product.setId(id);
		float originalpricewithgst= productUtility.calculateGST(product.getPrice());
		product.setGst(originalpricewithgst);
		float originalpricewithdiscount = productUtility.calculateDiscount(product.getPrice());
		product.setDiscount(originalpricewithdiscount);
		float totalPrice = product.getPrice()+originalpricewithgst-originalpricewithdiscount;
		product.setTotalPrice(totalPrice);
		return productRepository.save(product);
	}
	
	public Product updateProductWithId(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
