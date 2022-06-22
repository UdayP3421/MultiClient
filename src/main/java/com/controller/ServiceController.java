package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.entity.Services;
import com.repository.ServiceRepository;
import com.service.SService;
import com.serviceimpl.ServServiceImpl;

@RestController
@RequestMapping("/api/service")
@CrossOrigin
public class ServiceController {

	@Autowired
	private SService sserviceservice;

	
	
	
	@PostMapping("/addservice")
	public ResponseEntity<Services> saveService(@RequestBody Services service)
	{
		return new ResponseEntity<Services>(sserviceservice.saveService(service), HttpStatus.CREATED);
	}
	
	//get all plants
	@GetMapping("/getservices")
	public List<Services> getAllServices()
	{
		return sserviceservice.getAllServices();
		
	}
	
	@GetMapping("/getserviceby/{serviceId}")
	public ResponseEntity<Services> getServiceById(@PathVariable Long serviceId) throws Throwable
	{
		Services p = sserviceservice.getServiceById(serviceId);
		return new ResponseEntity<Services>(p, HttpStatus.OK);
	}
	
	
	
	//get plant by name
	
	@GetMapping("/getservice/{serviceName}")
	public ResponseEntity<Services> getServiceByName(@PathVariable String serviceName)
   {
	Services p = sserviceservice.getServiceByName(serviceName);
	return new ResponseEntity<Services>(p, HttpStatus.OK);
	}
	
	//get plant by type
	
	@GetMapping("/getservice/{serviceType}")
	public ResponseEntity<Services> getServiceByType(@PathVariable String serviceType)
	{
		Services p =  sserviceservice.getServiceByType(serviceType);
		return new ResponseEntity<Services>(p, HttpStatus.OK);
	}
	
	@PutMapping(path="/updateservice")
	public ResponseEntity<Services> updateService(@RequestBody Services e) throws Throwable
     {
	 Services e1 = sserviceservice.updateService(e) ;
	 ResponseEntity re = new ResponseEntity<Services>(e1,HttpStatus.OK);
	 return re; 
	}
	
	@DeleteMapping(path="/deleteserviceById/{serviceId}")
	public ResponseEntity<String> deleteServiceById(@PathVariable Long serviceId)
	{
		sserviceservice.deleteServiceById(serviceId);
		ResponseEntity re = new ResponseEntity<String>("Deleted",HttpStatus.OK);
		return re;
	}

}