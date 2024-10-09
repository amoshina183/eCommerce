package com.example.comm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;

import com.example.comm.entity.Product;
import com.example.comm.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	public Product getProductById(Long id){
		
		Optional<Product> p = productRepository.findById(id);
		if(p.isPresent()) {
			return p.get();
		}else {
			throw new RuntimeException("Product not found"+id);
		}

		
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product saveProduct(Product product){
		return productRepository.save(product);
	}
	
	public Product updateProduct(Long id, Product product){
		return productRepository.save(product);
	}
	
	
	public void deleteProduct(Long id){
		productRepository.deleteById(id);
	}
	

}
