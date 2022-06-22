package com.serviceimpl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.advice.CustomerNotFoundException;
import com.advice.ResourceNotFoundException;
import com.entity.Address;
import com.entity.Customer;
import com.entity.Services;
import com.repository.ICustomerRepository;
import com.service.ICustomerService;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;





@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private ICustomerRepository response;
	
	@Override
	public List<Customer> viewAllCustomers() {
		if(response.findAll() == null)
			throw new CustomerNotFoundException("No Customer found");
		return response.findAll();
	}
	
	@Override
	public Customer addCustomer(Customer customer) {
		return response.save(customer);
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		return response.save(customer);
	}
	
	@Override
	public Customer deleteCustomer(Long customerId) {
		Optional<Customer> obj = response.findById(customerId);
		if(obj.get() == null)
			throw new CustomerNotFoundException("Customer not created");
		response.deleteById(customerId);
		return obj.get();
	}	
	
	
	  @Override public boolean validateCustomer(String username, String password) {
	  Optional<Customer> customer = response.findByUsernameAndPassword(username,
	  password); if(customer.get() == null) return false; else return true;
	 
	}

	/*
	 * @Override public Optional<Customer> findByUsernameAndPassword(String
	 * username,String password) { Optional<Customer> customer =
	 * response.findByUsernameAndPassword(username,password); return customer; }
	 */
	 
	
	@Override
	public Customer viewCustomer(Long validId) {
		Optional<Customer> customer = response.findById(validId);
		if(customer.get() == null)
			throw new CustomerNotFoundException("Customer not created");
		return customer.get();
	}
	
	public Optional<Customer> viewByUserName(String username, String password) {
		Optional<Customer> customer = response.findByUsernameAndPassword(username, password);
		if(customer.get() == null)
			throw new CustomerNotFoundException("Customer not created");
		return customer;		
	}

	@Override
	public List<Object[]> viewOrders(Long customerId) {
		List<Object[]> obj = response.findOrders(customerId);
		return obj;
	}

	@Override
	public Customer updateAddress(Long customerId, Address address) {
		Customer customer = response.findById(customerId).get();
		if(customer == null)
			throw new CustomerNotFoundException("Customer not created");
		customer.setAddress(address);
		return response.save(customer);
	}
	@Override
	public List<Customer> getAllCustomer() {
		
		return response.findAll();
	}

	@Override
	public Customer findByUsername(String username) {
	
		Customer cus = response.findByUsername(username);
		
		return cus;
	}

}