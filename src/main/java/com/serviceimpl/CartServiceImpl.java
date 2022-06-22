package com.serviceimpl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.advice.ResourceNotFoundException;
import com.entity.CartP;


import com.entity.Services;


import com.repository.CartRepository;

import com.repository.ServiceRepository;
import com.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartrepo;

	private ServiceRepository plantrepo;



	public CartServiceImpl(CartRepository cartrepo) {
		super();
		this.cartrepo = cartrepo;
	}

	@Override
	public CartP addProduct(CartP cartp) {
		return cartrepo.save(cartp);
	}

	@Override
	public String deleteProductById(Long cid) {
		cartrepo.deleteById(cid);
		return "Deleted";
	}

	@Override
	public List<CartP> getCart() {
   return cartrepo.findAll();
	}

	@Override
	public CartP getCartById(Long cartId) throws Throwable {
		Supplier s1 = () -> new ResourceNotFoundException("Cart Not Found");
		return cartrepo.findById(cartId).orElseThrow(s1);

	}

	@Override
	public String deleteCartById(Long cartId) {
		cartrepo.deleteById(cartId);
		return "Deleted";
	}


	/*
	 * @Override public CartP updateCart(CartP c) throws Throwable { int id =
	 * c.getCartId(); Supplier s1 = () -> new
	 * ResourceNotFoundException("Plant Not Found"); CartP c1 =
	 * cartrepo.findById(id).orElseThrow(s1); c1.setP(c.getP()); c1.setS(c.getS());
	 * c1.setPlanter(c.getPlanter()); c1.setGardendecor(c.getGardendecor());
	 * c1.setFertilizer(c.getFertilizer()); cartrepo.save(c1); return c1; }
	 */

	

	
	/*
	 * @Override public String getBillById(int cartId) {
	 * 
	 * CartP p=cartrepo.getById(cartId);
	 * 
	 * return "bill is " + p.total();
	 * 
	 * }
	 */
	 
}
