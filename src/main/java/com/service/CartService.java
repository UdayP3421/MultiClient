package com.service;

import java.util.List;

import com.entity.CartP;



public interface CartService {

	CartP addProduct(CartP cart);

	String deleteProductById(Long cid);

	List<CartP> getCart();

	CartP getCartById(Long cartId) throws Throwable;

	String deleteCartById(Long cartId);

	//CartP updateCart(CartP c) throws Throwable;

	

	//String getBillById(int cartId);



}
