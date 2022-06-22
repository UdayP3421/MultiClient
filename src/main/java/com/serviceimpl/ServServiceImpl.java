package com.serviceimpl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.advice.ResourceNotFoundException;

import com.entity.Services;
import com.repository.ServiceRepository;
import com.service.SService;



@Service
public class ServServiceImpl implements SService {

	@Autowired
	private ServiceRepository servicerepository;
	




	@Override
	public Services saveService(Services service) {
		
		return servicerepository.save(service);
	}



	@Override
	public List<Services> getAllServices() {
		
		return servicerepository.findAll();
	}

	@Override
	public Services getServiceById(Long serviceId) throws Throwable
	{
		Supplier s1   = ()->new ResourceNotFoundException("Coder Does not Exist in the database");
		Services c = servicerepository.findById(serviceId).orElseThrow(s1);
		return c;
	}


	@Override
	public Services getServiceByName(String serviceName) {
		
		Services plant =servicerepository.findByserviceName(serviceName);
		
		return plant;
	}



	@Override
	public Services getServiceByType(String serviceType) {
		
		Services plant = servicerepository.findByserviceType(serviceType);
		return plant;
	}

	@Override
	public Services updateService(Services c) throws Throwable
	{
		Long id =c.getServiceId();
		Supplier s1=()-> new ResourceNotFoundException("Plant Not Found");
		Services c1 =servicerepository.findById(id).orElseThrow(s1);
		c1.setServiceName(c.getServiceName());
		c1.setServiceType(c.getServiceType());
		c1.setServicePrice(c.getServicePrice());
		c1.setServiceDescription(c.getServiceDescription());
	
		servicerepository.save(c1);
		return c1;
	}

	@Override
	public String deleteServiceById(Long cid)
	{
		servicerepository.deleteById(cid);
		return "Deleted";
	}

}
