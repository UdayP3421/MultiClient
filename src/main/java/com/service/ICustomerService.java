package com.service;

import java.util.List;
import java.util.Optional;

import com.entity.Address;
import com.entity.Customer;

public interface ICustomerService {
	
	Customer addCustomer(Customer customer);
	
	Customer updateCustomer(Customer tenant);

	Customer deleteCustomer(Long customerId);

	Customer viewCustomer(Long customerId);	

	List<Customer> viewAllCustomers();

	boolean validateCustomer(String userName, String password);
	
	List<Object[]> viewOrders(Long customerId);
	
	Customer updateAddress(Long customerId, Address address);

	List<Customer> getAllCustomer();
	
	Customer findByUsername(String username);

	//Optional<Customer> findByUsernameAndPassword(String username, String password);

}