package com.service;

import java.util.List;

import com.entity.Orders;


public interface IOrderService {
	
	Orders addOrder(Orders order);
	
	Orders updateOrder(Orders order);
	
	void deleteOrder(Long orderId);
	
	Orders viewOrder(Long orderId);
	
	List<Orders> viewAllOrders();
}

