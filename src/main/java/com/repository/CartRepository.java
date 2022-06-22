package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CartP;


public interface CartRepository extends JpaRepository<CartP, Long>{

	

}
