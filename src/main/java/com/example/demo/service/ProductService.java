package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.demo.entity.Product;

public interface ProductService {
	
	public boolean saveProduct(Product product) ;
	
	public Product getProductById(String productId);
	
	public List<Product> getAllProduct();
	
	public boolean deleteProductById(String productId);
	
	public boolean updateProduct(Product product);
	
	
	public List<Product> sortProductById_Asc(String sortBy);
	
	
	public List<Product> sortProductByName_Des(String sortBy);
	
	public Product getMaxPriceProduct();
	
	public double countSumOfProductPrice();
	
	public int getTotalCountOfProducts(); 
	
	public int uploadSheet(CommonsMultipartFile file, HttpSession httpSession);

}
