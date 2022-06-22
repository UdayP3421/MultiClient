package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CartP;

import com.entity.Services;

import com.service.CartService;


@RestController
@RequestMapping("/api/cart")
public class CartController {

	private CartService cartservice;

	CartP cartp;

	public CartController(CartService cartservice) {
		super();
		this.cartservice = cartservice;
	}

	@PostMapping("/addproduct")
	public ResponseEntity<CartP> addproduct(@RequestBody CartP cart) {
		return new ResponseEntity<CartP>(cartservice.addProduct(cart), HttpStatus.OK);
	}


	@GetMapping("/getCart")
	public List<CartP> getAllCart() {
		System.out.println("cartdat");
		List<CartP>list = cartservice.getCart();
		
	return list;

	}

	@GetMapping("/getCart/{cartId}")
	public ResponseEntity<CartP> getCartById(@PathVariable Long cartId) throws Throwable {

		return new ResponseEntity<CartP>(cartservice.getCartById(cartId), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteCartById/{cartId}")
	public ResponseEntity<String> deleteSeedById(@PathVariable Long cartId) {
		cartservice.deleteCartById(cartId);
		ResponseEntity re = new ResponseEntity<String>("Deleted", HttpStatus.OK);
		return re;
	}

	/*
	 * @PutMapping(path = "/updateCart") public ResponseEntity<CartP>
	 * updateCart(@RequestBody CartP e) throws Throwable { CartP e1 =
	 * cartservice.updateCart(e); ResponseEntity re = new ResponseEntity<CartP>(e1,
	 * HttpStatus.OK); return re; }
	 */

	/*
	 * @GetMapping("/bill/{cartId}") public ResponseEntity<String>
	 * getBillById(@PathVariable int cartId) { String b
	 * =cartservice.getBillById(cartId); ResponseEntity re = new
	 * ResponseEntity<String>(b, HttpStatus.OK); return re; }
	 */
	
	@DeleteMapping(path = "/deleteProductById/{eid}")
	public ResponseEntity<String> deleteProductById(@PathVariable Long eid) {
		cartservice.deleteProductById(eid);
		ResponseEntity re = new ResponseEntity<String>("Deleted", HttpStatus.OK);
		return re;
	}


}
