package com.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Customer;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByUsername(String username);	
	
	Optional<Customer> findByUsernameAndPassword(String username, String password);	
	
	@Query("SELECT customerName,customerEmail,orders FROM Customer WHERE customerId = :customerId")
	List<Object[]> findOrders(@Param("customerId") Long customerId);
	
}

