package com.example.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String,Object> hamdleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		
		HashMap<String,Object> hashmap = new HashMap<>();
		hashmap.put("Time", new Date());
		
//		ex.getBindingResult().getFieldErrors().forEach(error ->{
//			hashmap.put(error.getField(), error.getDefaultMessage());});
		
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			hashmap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return hashmap;
		
	}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> ProductNotFoundEx(ProductNotFoundException ex){
		
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.OK);
		
	}

	
	@ExceptionHandler(ProductAlredyExistException.class)
	public ResponseEntity<String> ProductAlredyEx(ProductAlredyExistException ex){
		
		return new ResponseEntity<String> (ex.getMessage(),HttpStatus.OK);
		
	}
}
