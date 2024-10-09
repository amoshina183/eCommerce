package com.example.comm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.comm.entity.Product;
import com.example.comm.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
//@RequestMapping("api/v1")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		return new ResponseEntity<Product>(productService.getProductById(id),HttpStatus.OK);
		
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Product>> getAllProduct(){
		return new ResponseEntity<List<Product>>(productService.getAllProduct(),HttpStatus.OK);
	}
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.saveProduct(product),HttpStatus.CREATED);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Product> UpdateProductById(@RequestParam Long id, @RequestBody Product product){
		product.setPid(id);
		return new ResponseEntity<Product>(productService.updateProduct(id, product),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<HttpStatus> deleteProductById(@RequestParam Long id){
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getCSRFToken")
	public CsrfToken getCSRFToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
		
	}

}
