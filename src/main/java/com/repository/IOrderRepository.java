package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Orders;


@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long>{
	public Optional<Orders> findById(Long orderId);
}