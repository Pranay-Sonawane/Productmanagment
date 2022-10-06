package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductAlredyExistException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.ProductServiceImpl;

@RestController
public class ProductController {
	
	@Autowired
	private ProductServiceImpl service;
	
	
	@PostMapping(value = "/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product){
		boolean isAdded = service.saveProduct(product);
		
		if(isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		}else {
			throw new ProductAlredyExistException("Product Alredy Exist" );
		}
	}
	
	@GetMapping(value = "/getProductById")
	public ResponseEntity<Product> getProductById(@RequestParam String productId){
		Product product = service.getProductById(productId);
		
		if(product != null) {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}else {
	
		throw new ProductNotFoundException("Product not found for Id : " + productId);
		
		
		}
	}
	
	
	@GetMapping(value = "/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> allProduct = service.getAllProduct();
		if(allProduct != null) {
			
			return new ResponseEntity<List<Product>> (allProduct,HttpStatus.OK);
		}else {
			throw new ProductNotFoundException("Product not found for Id");
		}
		
	}
	
	
	@DeleteMapping(value = "/deleteProductById")
	public ResponseEntity<Boolean> deleteProductById(@RequestParam String productId){
		boolean isDelete = service.deleteProductById(productId);
		if(isDelete) {
			return new ResponseEntity<Boolean> (isDelete,HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product not found for Id " + productId);
		}
	}
	
	@PutMapping(value = "/updateProduct")
	public ResponseEntity<Boolean> updateProduct(@Valid @RequestBody Product product){
		boolean isUpdated = service.updateProduct(product);
		if(isUpdated) {
			return new ResponseEntity<Boolean> (isUpdated,HttpStatus.OK);
		}
		else {
			throw new ProductNotFoundException("Product not found for Update");
			
		}
	}
	 
	
	@GetMapping(value = "/sortProductById_Asc")
	public ResponseEntity<List<Product>> sortProductById_Asc(@RequestParam String sortBy){
		List<Product> list = service.sortProductById_Asc(sortBy);
		if(list == null) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}else {
	
		return new ResponseEntity<List<Product>> (list,HttpStatus.OK);
		
		
		}
	}
	
	
	@GetMapping(value = "/sortProductByName_Des")
	public ResponseEntity<List<Product>> sortProductByName_Des(@RequestParam String sortBy){
		List<Product> list = service.sortProductByName_Des(sortBy);
		if(list == null) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}else {
	
		return new ResponseEntity<List<Product>> (list,HttpStatus.OK);
		
		
		}
	}
	
	@GetMapping(value = "/getMaxPriceProduct")
	public ResponseEntity<Product> getMaxPriceProduct() {
		
		Product product =  service.getMaxPriceProduct();

		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}else {
	
		return new ResponseEntity<Product> (product,HttpStatus.OK);
	}
}
	
	
	@GetMapping(value = "/countSumOfProductPrice")
	public ResponseEntity<Double> countSumOfProductPrice() {
		
		double sum =  service.countSumOfProductPrice();
		if(sum > 0)
		return new ResponseEntity<Double> (sum,HttpStatus.OK);
		else {
			return new ResponseEntity<Double> (sum,HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	@GetMapping(value = "/getTotalCountOfProducts")
	public ResponseEntity<Integer> getTotalCountOfProducts() {
		
		int numberOfProduct =  service.getTotalCountOfProducts();
		if(numberOfProduct>0)
		return new ResponseEntity<Integer> (numberOfProduct,HttpStatus.OK);
		else {
			return new ResponseEntity<Integer> (HttpStatus.NO_CONTENT);
		}
		
}
	
	
	@PostMapping(value = "/uploadSheet")
	public ResponseEntity<Integer> uploadSheet(@RequestParam CommonsMultipartFile file,HttpSession httpSession){
		
		int count = service.uploadSheet(file, httpSession);
		
		return new ResponseEntity<Integer> (count,HttpStatus.OK);
		
	}
}
