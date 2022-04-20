package com.example.demo.Exceptions;

public class CustomerNotFoundException extends Exception{

	@Override
	public String getMessage() {
		return "customer not found!!" ;
	}
}
