package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		
		List<Customer> allCustomers = customerService.getAllCustomers();
		
		return ResponseEntity.ok(allCustomers);
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customer) {
		
		customerService.addCustomer(customer);
		
		return ResponseEntity.ok("Customer Details added!!!");
	}
	
	@PostMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomer(int customerId, 
			@RequestParam("attr") String key, @RequestParam("value") String value) {
		
		customerService.updateCustomerData(customerId, key, value);
		
		return ResponseEntity.ok("Details updated!!!");
	}

	@PostMapping("/deleteCustomer")
	public BodyBuilder deleteCustomer(@RequestParam("customerId") int customerId) {
		
		try {
			customerService.deleteCustomer(customerId);
		} catch (CustomerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build().badRequest();
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build().ok();
	}
}
