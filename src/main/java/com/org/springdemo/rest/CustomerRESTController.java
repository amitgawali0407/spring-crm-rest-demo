package com.org.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.springdemo.entity.Customer;
import com.org.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRESTController {

	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/customers")
	private List<Customer> getallCustomers(){
		
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	private Customer getCustomer(@PathVariable int customerId){
		
		Customer customer = customerService.getCustomer(customerId);
		
		if(null == customer) {
			throw new CustomerNotFoundException("Customer with id: " + customerId + " was not found");
		}
		
		return customer;
	}
	
	@PostMapping("/customers")
	private Customer addCustomer(@RequestBody Customer customer){
		
		
		customer.setId(0);
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	@PutMapping("/customers")
	private Customer updateCustomer(@RequestBody Customer customer){
		
		
		//customer.setId(0);
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	
	@DeleteMapping("/customers/{customerId}")
	private String deleteCustomer(@PathVariable int customerId){
		
		Customer customer = customerService.getCustomer(customerId);
		
		if(null == customer) {
			throw new CustomerNotFoundException("Customer with id: " + customerId + " was not found");
		}
		
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer with id: " + customerId;
	}
	
}
