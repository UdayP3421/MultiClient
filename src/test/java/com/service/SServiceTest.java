package com.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.entity.Services;
import com.repository.ServiceRepository;
import com.serviceimpl.ServServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SServiceTest {

	@InjectMocks
	private ServServiceImpl plantservice;
	
	@Mock
	private ServiceRepository plantrepo;
	
	@Test
	public void savePlant()
	{
		Services plant = new Services();
		plant.setServiceId((long) 2);
		when(plantservice.saveService(plant)).thenReturn(plant);
		assertEquals(plant, plantservice.saveService(plant));
	}
	
	@Test
	public void getPlant()
	{
		Services p= new Services();
		p.setServiceId((long) 1);
		p.setServiceName("Apple");
		p.setServiceType("Shrub");
		p.setServiceDescription("Tall");
		p.setServicePrice(20.0);
		
		
		List<Services> plantlist = new ArrayList<>();
		plantlist.add(p);
		
		Mockito.when(plantrepo.findAll()).thenReturn(plantlist);
		assertThat(plantservice.getAllServices()).isEqualTo(plantlist);
		
	}
	
	@Test
	
	void testGetPlantById() throws Throwable {
		Services p= new Services();
		p.setServiceId((long) 1);
		p.setServiceName("Apple");
		p.setServiceType("Shrub");
		p.setServiceDescription("Tall");
		p.setServicePrice(20.0);
		
		 
		 
		Optional<Services> c2=Optional.of(p);
		
		Mockito.when(plantrepo.findById((long) 1)).thenReturn(c2);
		
		assertThat(plantservice.getServiceById((long) 1)).isEqualTo(p);
	}
	
	@Test
	void testUpdatePlant() throws Throwable {
		Services p= new Services();
		p.setServiceId((long) 1);
		p.setServiceName("Apple");
		p.setServiceType("Shrub");
		p.setServiceDescription("Tall");
		p.setServicePrice(20.0);
		
		 
		 
		Optional<Services> c2=Optional.of(p);
		
		Mockito.when(plantrepo.findById((long) 1)).thenReturn(c2);
		
		Mockito.when(plantrepo.save(p)).thenReturn(p);
		p.setServiceName("Lemon");
		p.setServiceType("Shrub");
		p.setServiceDescription("Small");
		p.setServicePrice(20.0);
	
		
		assertThat(plantservice.updateService(p)).isEqualTo(p);
	}
	
}
