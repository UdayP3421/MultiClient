package com.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advice.OrderNotFoundException;
import com.entity.Address;

import com.entity.Customer;
import com.entity.Customerinfo;
import com.entity.Orders;

//import com.entity.ResponseToken;
import com.forms.Login;
//import com.jwtsecurity.helper.JwtUtils;
import com.repository.ICustomerRepository;
import com.serviceimpl.CustomerServiceImpl;
import com.serviceimpl.OrderServiceImpl;
import com.serviceimpl.ServServiceImpl;


@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

	private int validUser = 0;
	private Long validId = (long) 0;
	private String welcome = "Welcome \n........................\n Customer Id : ";
	@Autowired
	private CustomerServiceImpl service;

	@Autowired
	private OrderServiceImpl orderservice;

	@Autowired
	private ServServiceImpl sserviceservice;


	
	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	/*
	 * //use for jwt
	 * 
	 * @Autowired private AuthenticationManager authenticationManager;
	 */

		//@Autowired
		//private JwtUtils jwtutil;

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
		return ResponseEntity.ok(service.addCustomer(customer));
	}

	@PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrder(@RequestBody Orders order) {
		if (validUser == 1) {
			Customer customer = service.viewCustomer(validId);
			order.setCustomer(customer);
			return ResponseEntity.ok(orderservice.addOrder(order));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@DeleteMapping("/deletecustomer")
	public ResponseEntity<?> deleteCustomer() {
		if (validUser == 1) {
			return ResponseEntity.ok(service.deleteCustomer(validId));
		} else
			return ResponseEntity.ok("Not Logged In");

	}

	@GetMapping("/logincu/{username}/{password}")
	public ResponseEntity<?> validateUser(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		boolean value = service.validateCustomer(username, password);
		if (value == true) {
			validUser = 1;
			Customer customer = service.viewByUserName(username, password).get();
			validId = customer.getCustomerId();

			return ResponseEntity.ok(welcome + validId + "\n Name : " + customer.getCustomerName() + "\n Email : "
					+ customer.getCustomerEmail() + "\n.........................\n Address --- \n House no : "
					+ customer.getAddress().getHouseNo() + "\n Colony : " + customer.getAddress().getColony()
					+ "\n City : " + customer.getAddress().getCity() + "\n State : " + customer.getAddress().getState()
					+ "\n Pincode : " + customer.getAddress().getPincode());
		} else
			return ResponseEntity.ok("Invalid Credentials");
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<?> viewOrder(@PathVariable("id") Long orderId) throws OrderNotFoundException {
		if (validUser == 1) {
			Orders order = orderservice.viewOrder(orderId);
			return ResponseEntity.ok("..........................Order Details......................... \n "
					+ "Booking id : " + order.getBookingOrderId() + "\n" + "Order date : " + order.getOrderDate() + "\n"
					+ "Transaction mode : " + order.getTransactionMode() + "\n" + "Quantity ordered : "
					+ order.getQuantity() + "\n" + "Total Cost : " + order.getTotalCost());
		} else
			return ResponseEntity.ok("Not Logged In");

	}

	@GetMapping("/getcustomer")
	public ResponseEntity<?> viewCustomer() {
		if (validUser == 1) {
			Customer customer = service.viewCustomer(validId);
			return ResponseEntity.ok(customer);
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@GetMapping("/orders")
	public ResponseEntity<?> fetchOrders() {
		if (validUser == 1) {
			Customer customer = service.viewCustomer(validId);
			List<Orders> order = customer.getOrders();
			List<String> orderlist = new ArrayList<>();
			int i = 1;
			for (Orders obj : order) {
				orderlist.add("Serial : " + i + ", Booking id : " + obj.getBookingOrderId() + ", Order date : "
						+ obj.getOrderDate() + ", Transaction mode : " + obj.getTransactionMode()
						+ ", Quantity ordered : " + obj.getQuantity() + ", Total Cost : " + obj.getTotalCost());
				i++;
			}
			return ResponseEntity.ok(orderlist);
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@PatchMapping(path = "/updatecustomer")
	public ResponseEntity<?> updateCustomerById(@RequestBody Map<Object, Object> fields) {
		if (validUser == 1) {
			Customer customer = service.viewCustomer(validId);
			if (customer != null) {
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Customer.class, (String) key);
					field.setAccessible(true);
					ReflectionUtils.setField(field, customer, value);
				});
			}
			return ResponseEntity.ok(service.updateCustomer(customer));

		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@PutMapping("/address")
	public ResponseEntity<?> updateAddressById(@RequestBody Address address) {
		if (validUser == 1) {
			return ResponseEntity.ok(service.updateAddress(validId, address));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		if (validUser == 1) {
			validUser = 0;
			return ResponseEntity.ok("Logged out...");
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	/* .........................Plant................................. */
	@GetMapping("/service")
	public ResponseEntity<?> fetchAllOrders() {
		if (validUser == 1) {
			return ResponseEntity.ok(sserviceservice.getAllServices());
		} else
			return ResponseEntity.ok("Not Logged In");
	}




	@PostMapping("/logincustomer")
	public ResponseEntity<?> loginUser(@Valid @RequestBody Login login) {
	
		
		boolean value = service.validateCustomer(login.getUserName(), login.getPassword());
	
	  if (value == true) { validUser = 1; Customer customer =
	  service.viewByUserName(login.getUserName(), login.getPassword()).get(); 
	  validId = customer.getCustomerId();
	  
	  return ResponseEntity.ok(welcome + validId + "\n Name : " +
	  customer.getCustomerName() + "\n Email : " + customer.getCustomerEmail() +
	  "\n.........................\n Address --- \n House no : " +
	  customer.getAddress().getHouseNo() + "\n Colony : " +
	  customer.getAddress().getColony() + "\n City : " +
	  customer.getAddress().getCity() + "\n State : " +
	  customer.getAddress().getState() + "\n Pincode : " +
	  customer.getAddress().getPincode()); } else return
	  ResponseEntity.ok("Invalid Credentials");
	 
		
	
	}
	@GetMapping("/getallcustomer")
	public List<Customer> getAllCustomer()
	{
		return service.getAllCustomer();
		
	}
	
	
	
	  //JWT //this method is used for login it will return token and generate token
	 // and send token as a response
	  
	  @PostMapping("/login") public ResponseEntity<?>
	  authenticateUser(@Validated @RequestBody Login loginRequest) {
	  
	  Boolean validate
	  =iCustomerRepository.findByUsernameAndPassword(loginRequest.getUserName(),
	  loginRequest.getPassword())!=null? true :false;
	  
	  System.out.println(loginRequest); if(validate){ 
		  validUser = 1; Customer customer =
		  service.viewByUserName(loginRequest.getUserName(), loginRequest.getPassword()).get(); 
		  validId = customer.getCustomerId();
			/*
			 * Customerinfo customerinfo = new Customerinfo();
			 * customerinfo.setCustomerEmail(customer.getCustomerEmail());
			 * customerinfo.setCustomerName(customer.getCustomerName());
			 * customerinfo.setHouseNo(customer.getAddress().getHouseNo());
			 * customerinfo.setColony(customer.getAddress().getColony());
			 * customerinfo.setState(customer.getAddress().getState());
			 * customerinfo.setPincode(customer.getAddress().getPincode());
			 */
		  return ResponseEntity.ok("welcome :" + validId + "\n Name : " +
		  customer.getCustomerName() + "\n Email : " + customer.getCustomerEmail() +
		  "\n.........................\n Address --- \n House no : " +
		  customer.getAddress().getHouseNo() + "\n Colony : " +
		  customer.getAddress().getColony() + "\n City : " +
		  customer.getAddress().getCity() + "\n State : " +
		  customer.getAddress().getState() + "\n Pincode : " +
		  customer.getAddress().getPincode()); } else return
		  ResponseEntity.ok("Invalid Credentials");
		  }
	  
		@GetMapping("/getcustomer/{username}")
		public ResponseEntity<Customer> getByuserName(@PathVariable String username)
	   {
		Customer p = service.findByUsername(username);
		return new ResponseEntity<Customer>(p, HttpStatus.OK);
		}
		
	  
	  
	  
	  
	  }
	 



