package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.CustomerNotFoundException;
import com.example.demo.dto.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void addCustomer(CustomerDto customerData) {
		
		if(!validate(customerData)) {
			System.out.println("Customer data invalid!!!");
		}
		
		Customer customer = new Customer(customerData.getCustomerId(), 
								customerData.getCustomerName(), customerData.getEmail(),
								customerData.getMobile(), customerData.getAddress()
								);
		
		customerRepository.save(customer);
	}
	
	public void updateCustomerData(int customerId, String key, String value) {
		
		Customer customer = customerRepository.findById(customerId).get();
		
		if(key.equals("email")) {
			
			if(!validateEmail(value)) {
				return;
			}
			
			customer.setEmail(value);
			
		} else if(key.equals("mobile")) {
			
			if(!validateMobile(value)) {
				return;
			}
			customer.setMobile(value);
			
		} else if (key.equals("address")) {
			customer.setAddress(value);
		}
		
		customerRepository.save(customer);
	}
	
	private boolean validateMobile(String value) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean validateEmail(String value) {
		// TODO Auto-generated method stub
		return true;
	}

	public List<Customer> getAllCustomers() {
		
		Iterable<Customer> iter = customerRepository.findAll();
		
		List<Customer> customerList = new ArrayList<>(); 
 		
		Iterator<Customer> customers = iter.iterator();
		
		while(customers.hasNext()) {
			customerList.add(customers.next());
		}
		
		return customerList;
	}
	
	public void deleteCustomer(int customerId) throws CustomerNotFoundException {
		
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
		} else {
			throw new CustomerNotFoundException();
		}
	}
	
	public boolean validate(CustomerDto customer) {
		
		return true;
	}

}
