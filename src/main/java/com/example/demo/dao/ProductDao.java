package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductDao {
	
	public boolean saveProduct(Product product) ;
	
	public Product getProductById(String productId);
	
	public List<Product> getAllProduct();
	
	public boolean deleteProductById(String productId);
	
	public boolean updateProduct(Product product);
	
	public int uploadProductList(List<Product> list);
	

}
