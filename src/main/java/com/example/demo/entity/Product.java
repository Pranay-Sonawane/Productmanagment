package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	private String productId;
	
	@NotNull(message = "Product Name Is Required")
	private String productName;
	
	@NotNull(message = "Product Type Is Required")
	private String productType;
	
	@Min(1)
	private double productPrice;
	
	@Min(1)
	private int productQty;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String productId, String productName, String productType, double productPrice, int productQty) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", productPrice=" + productPrice + ", productQty=" + productQty + "]";
	}
	
	
	
	

}
