package com.service;

import java.util.Optional;

import com.entity.Merchant;



public interface IMerchantService {
	

	
	

	Merchant addMerchant(Merchant admin);

	boolean validateMerchant(String username, String password);

	Optional<Merchant> viewByMerchantUserName(String username, String password);

	Merchant findByMerchantUsername(String adminUsername);

}
