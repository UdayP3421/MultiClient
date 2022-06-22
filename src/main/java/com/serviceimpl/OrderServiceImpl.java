package com.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.CustomerNotFoundException;
import com.advice.OrderNotFoundException;
import com.advice.PlantNotFoundException;
import com.advice.PlanterNotFoundException;
import com.advice.SeedNotFoundException;
import com.entity.Customer;
import com.entity.Orders;
import com.entity.Services;

import com.repository.ICustomerRepository;
import com.repository.IOrderRepository;
import com.repository.ServiceRepository;

import com.service.IOrderService;


@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private IOrderRepository iorderrepository;
	
	
	
	@Autowired
	private ServiceRepository plantRepo;
	
	
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Override
	public Orders addOrder(Orders Myorder) {

			final Orders order = Myorder;
			Myorder.setOrderDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
		
			//....................................Updating stock...................................................
			/*
			 * IntStream.range(0, seed.size()).forEachOrdered(i -> { if
			 * (seed.get(i).getSeedsStock() >= order.getSeedQuantity())
			 * seed.get(i).setSeedsStock(seed.get(i).getSeedsStock() -
			 * order.getSeedQuantity()); //add else throw new
			 * RuntimeException("Product Out of Stock"); });
			 */

			//.................................calculating seed cost................................................
		
			
			
			//......................................Plant...........................................................
			Double serviceCost = 0.0;
			List<Services> service = new ArrayList<Services>();
			int plantqty = 0;
			
			if (order.getService() != null) {
				for (Services i : order.getService()) {
					service.add(plantRepo.findById(i.getServiceId()).orElseThrow(
							() -> new PlantNotFoundException("Plant by id" + i.getServiceId() + " was not found")));
					
				}
				Myorder.setService(service);

			//.................................Upadting Stock.......................................................
			/*
			 * IntStream.range(0, plant.size()).forEachOrdered(i -> { if
			 * (plant.get(i).getPlantsStock() >= order.getPlantQuantity())
			 * plant.get(i).setPlantsStock(plant.get(i).getPlantsStock() -
			 * order.getPlantQuantity()); //add else throw new
			 * RuntimeException("Product Out of Stock"); });
			 */

			//..............................calculating Plant cost.................................................
				serviceCost = IntStream.range(0, order.getService().size())
				  .mapToDouble(i -> order.getService().get(i).getServicePrice()).sum();  //add
			}
								
			//.........................................Planter.....................................................
			

			//.....................................Updating Stock...................................................
			/*
			 * IntStream.range(0, planter.size()).forEachOrdered(i -> { if
			 * (planter.get(i).getPlanterStock() >= order.getPlanterQuantity())
			 * planter.get(i).setPlanterStock(planter.get(i).getPlanterStock() -
			 * order.getPlanterQuantity()); //add else throw new
			 * RuntimeException("Product Out of Stock"); });
			 */
			//.................................calculating Planter cost.............................................
		

			Myorder.setTotalCost(serviceCost);			
			Myorder.setQuantity(plantqty);
			
			//...........................................Customer..................................................
			Customer customer = customerRepo.findById(Myorder.getCustomer().getCustomerId()).orElseThrow(
					            () -> new CustomerNotFoundException("Customer Not Found"));
			Myorder.setCustomer(customer);
			
			
			Myorder =  iorderrepository.save(Myorder);
			
			plantRepo.saveAll(service);
			
			customerRepo.save(customer);			
			return Myorder;
	}
	
	
	@Override
	public Orders updateOrder(Orders order) {
		return iorderrepository.save(order);
	}	
	
	@Override
	public List<Orders> viewAllOrders() {		
		return iorderrepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) {
		iorderrepository.deleteById(orderId);		
	}

	@Override
	public Orders viewOrder(Long orderId) {
		Optional<Orders> order = iorderrepository.findById(orderId);
		if (!order.isPresent()) {
			throw new OrderNotFoundException("Order Id not found");
		}
		return order.get();
	}

}