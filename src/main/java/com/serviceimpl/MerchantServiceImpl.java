package com.serviceimpl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.AdminFoundException;
import com.advice.AdminNotFoundException;
import com.entity.Merchant;
import com.repository.IMerchantRepository;
import com.service.IMerchantService;



@Service
public class MerchantServiceImpl implements IMerchantService{
	
	@Autowired
	private IMerchantRepository adminrepo;

	@Override
	public Merchant addMerchant(Merchant admin) {
		Merchant obj = adminrepo.findByMerchantUsername(admin.getMerchantUsername());
		if(obj != null)
			throw new AdminFoundException("Admin already created");
		return adminrepo.save(admin);
	}

	@Override
	public boolean validateMerchant(String username, String password) {
		Optional<Merchant> admin = adminrepo.findByMerchantUsernameAndMerchantpassword(username, password);
		if(admin.get() == null)
			throw new AdminNotFoundException("Admin not created");
		else
			return true;
	}

	@Override
	public Optional<Merchant> viewByMerchantUserName(String username, String password) {
		Optional<Merchant> admin = adminrepo.findByMerchantUsernameAndMerchantpassword(username, password);
		if(admin.get() == null)
			throw new AdminNotFoundException("Admin not created");
		return admin;		
	}

	public Object findByUsernameAndPassword(String adminUsername, String adminpassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Merchant findByMerchantUsername(String adminUsername) {
		
		Merchant admin = adminrepo.findByMerchantUsername(adminUsername);
		return admin;
	}

}