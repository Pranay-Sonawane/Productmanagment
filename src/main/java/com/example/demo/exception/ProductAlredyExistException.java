package com.example.demo.exception;

public class ProductAlredyExistException extends RuntimeException {
	
	public ProductAlredyExistException(String msg) {
		super(msg);
	}

}
