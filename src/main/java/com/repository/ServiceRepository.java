package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Services;


@Repository
public interface ServiceRepository extends JpaRepository<Services, Long>{

	Services findByserviceName(String serviceName);
	
	Services findByserviceType(String serviceType);

}
