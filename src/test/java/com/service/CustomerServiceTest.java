package com.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.entity.Customer;
import com.entity.Services;

import com.repository.ICustomerRepository;
import com.serviceimpl.CustomerServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@InjectMocks
	private CustomerServiceImpl customerservice;
	
	@Mock
	private ICustomerRepository customerrepo;
	
	@Test
	public void addCustomer()
	{
		Customer customer = new Customer();
		customer.setCustomerName("uday");
		when(customerservice.addCustomer(customer)).thenReturn(customer);
		assertEquals(customer, customerservice.addCustomer(customer));
	}
	@Test
	
	void testGetCustomerById() throws Throwable {
	Customer c = new Customer();
	
	
	c.setCustomerName("uday12");
	c.setCustomerEmail("uday@gmail.com");
	
	c.setPassword("Uday@123");
		 
		 
		Optional<Customer> c2=Optional.of(c);
		
		Mockito.when(customerrepo.findById((long) 1)).thenReturn(c2);
		
		assertThat(customerservice.viewCustomer((long) 1)).isEqualTo(c);
	}
	
	/*
	 * @Test void testUpdateCustomer() throws Throwable {
	 * 
	 * CustomerReg c = new CustomerReg();
	 * 
	 * 
	 * c.setCustomerName("uday12"); c.setCustomerEmail("uday@gmail.com");
	 * c.setCustomerPhone("9898753673"); c.setPassword("Uday@123");
	 * 
	 * 
	 * Optional<CustomerReg> c2=Optional.of(c);
	 * Mockito.when(customerrepo.findById(1)).thenReturn(c2);
	 * 
	 * Mockito.when(customerrepo.save(c)).thenReturn(c);
	 * 
	 * c.setCustomerName("uday123"); c.setCustomerEmail("uday23@gmail.com");
	 * c.setCustomerPhone("9898753673"); c.setPassword("Uday@123");
	 * 
	 * assertThat(customerservice.updateCustomer(c)).isEqualTo(c); }
	 */


	
	
}
