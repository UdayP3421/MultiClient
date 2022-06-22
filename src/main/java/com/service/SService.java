package com.service;

import java.util.List;

import com.entity.Services;

public interface SService {



	Services saveService(Services service);

	List<Services> getAllServices();

	Services getServiceById(Long serviceId) throws Throwable;

	Services getServiceByName(String serviceName);

	Services getServiceByType(String serviceType);

	Services updateService(Services c) throws Throwable;

	String deleteServiceById(Long cid);
	
	
	
	
}
