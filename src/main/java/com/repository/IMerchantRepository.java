package com.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Merchant;


@Repository
public interface IMerchantRepository extends JpaRepository<Merchant, Long>{
	Merchant findByMerchantUsername(String merchantUsername);	
	Optional<Merchant> findByMerchantUsernameAndMerchantpassword(String merchantUsername, String merchantpassword);	

}
