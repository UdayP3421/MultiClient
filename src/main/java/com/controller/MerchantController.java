package com.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Merchant;
import com.entity.Customer;
import com.entity.Orders;
import com.entity.Services;

//import com.entity.ResponseToken;

import com.forms.MerchantLogin;
import com.forms.Login;
import com.repository.IMerchantRepository;
//import com.jwtsecurity.helper.JwtUtils;
//import com.repository.IAdminRepository;
import com.serviceimpl.MerchantServiceImpl;
import com.serviceimpl.CustomerServiceImpl;
import com.serviceimpl.ServServiceImpl;


@RestController
@RequestMapping("/merchant")
@CrossOrigin
public class MerchantController {
	private int validAdmin = 0; // flag variable
	private Long ValidId = (long) 0;
	@Autowired
	private CustomerServiceImpl customerservice;

	@Autowired
	private ServServiceImpl sserviceservice;

	

	

	@Autowired
	private MerchantServiceImpl merchantservice;

	/*
	 * //use for jwt
	 * 
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private JwtUtils jwtutil;
	 * 
	 */
	@Autowired
	private IMerchantRepository iadminrepo;

	/* ..........................For Merchant.................................. */

	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<?> loginAdmin(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		if (merchantservice.validateMerchant(username, password) == true) {
			validAdmin = 1;
			Merchant admin = merchantservice.viewByMerchantUserName(username, password).get();
			String welcome = "Welcome \n........................\n";
			return ResponseEntity
					.ok(welcome + "Id : " + admin.getMerchantId() + "\nUsername : " + admin.getMerchantUsername());
		} else
			return ResponseEntity.ok("Invalid credentials");
	}

	@PostMapping("/add")
	public Merchant addMerchant(@Validated @RequestBody Merchant admin) {
		return merchantservice.addMerchant(admin);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout() {
		if (validAdmin == 1) {
			validAdmin = 0;
			return ResponseEntity.ok("Logged out...");
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	/* ..........................For Customer............................. */

	@GetMapping("/customer")
	public ResponseEntity<?> viewAllCustomers() {
		if (validAdmin == 1) {
			return ResponseEntity.ok(customerservice.viewAllCustomers());
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@PatchMapping("/customer/{id}")
	public ResponseEntity<?> updateCustomerById(@PathVariable("id") Long customerId,
			@Validated @RequestBody Map<Object, Object> fields) {
		if (validAdmin == 1) {
			Customer customer = customerservice.viewCustomer(customerId);
			if (customer != null) {
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Customer.class, (String) key);
					field.setAccessible(true);
					ReflectionUtils.setField(field, customer, value);
				});
			}
			return ResponseEntity.ok(customerservice.updateCustomer(customer));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long customerId) {
		if (validAdmin == 1) {
			return ResponseEntity.ok(customerservice.deleteCustomer(customerId));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<?> fetchOrders(@PathVariable("id") Long customerId) {
		if (validAdmin == 1) {
			Customer customer = customerservice.viewCustomer(customerId);
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

	/* ..........................For Service................................ */

	@PostMapping("/service")
	public ResponseEntity<?> saveOrder(@RequestBody Services service) {
		if (validAdmin == 1) {
			return ResponseEntity.ok(sserviceservice.saveService(service));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@PatchMapping("/service/{id}")
	public ResponseEntity<?> updateServiceById(@PathVariable("id") Long serviceId,
			@Validated @RequestBody Map<Object, Object> fields) throws Throwable {
		if (validAdmin == 1) {
			Services plant = sserviceservice.getServiceById(serviceId);
			if (plant != null) {
				fields.forEach((key, value) -> {
					Field field = ReflectionUtils.findField(Services.class, (String) key);
					field.setAccessible(true);
					ReflectionUtils.setField(field, plant, value);
				});
			}
			return ResponseEntity.ok(sserviceservice.updateService(plant));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@DeleteMapping("/service/{id}")
	public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
		if (validAdmin == 1) {
			return ResponseEntity.ok(sserviceservice.deleteServiceById(id));
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	@GetMapping("/services")
	public ResponseEntity<?> fetchAllOrders() {
		if (validAdmin == 1) {
			return ResponseEntity.ok(sserviceservice.getAllServices());
		} else
			return ResponseEntity.ok("Not Logged In");
	}

	



	/*
	 * // JWT Methods // This method is used for Login and its sends token as a
	 * response
	 * 
	 * @PostMapping("/login") public ResponseEntity<?>
	 * authenticateUser(@Validated @RequestBody Admin loginRequest) {
	 * 
	 * Boolean validate =
	 * iadminrepo.findByAdminUsernameAndAdminpassword(loginRequest.getAdminUsername(
	 * ), loginRequest.getAdminpassword()) != null ? true : false;
	 * 
	 * System.out.println(loginRequest); if (validate) { Authentication
	 * authentication = authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(loginRequest.getAdminUsername(),
	 * loginRequest.getAdminpassword()));
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * String jwt = jwtutil.generateJwtToken(authentication);
	 * 
	 * return ResponseEntity.ok(new ResponseToken(jwt)); } else { return
	 * ResponseEntity.ok("User Not Found!!!"); }
	 * 
	 * }
	 */

	@PostMapping("/login") public ResponseEntity<?>
	  authenticateUser(@Validated @RequestBody MerchantLogin loginRequest) {
	  
	  Boolean validate
	  =iadminrepo.findByMerchantUsernameAndMerchantpassword(loginRequest.getAdminUsername(),
	  loginRequest.getAdminPassword())!=null? true :false;
	  
	  System.out.println(loginRequest); if(validate){ 
		  validAdmin = 1; Merchant customer =
		  merchantservice.viewByMerchantUserName(loginRequest.getAdminUsername(), loginRequest.getAdminPassword()).get(); 
		  ValidId = customer.getMerchantId();
		  
		  return ResponseEntity.ok( "welcome" + ValidId + "\n Name : " +
		  customer.getMerchantUsername() + "\n Password : " + customer.getMerchantpassword());
		 } else 
			 return ResponseEntity.ok("Invalid Credentials");
		  }
	
	@GetMapping("/getadmin/{adminUsername}")
	public ResponseEntity<Merchant> getByAdminuserName(@PathVariable String adminUsername)
   {
	Merchant a = merchantservice.findByMerchantUsername(adminUsername);
	return new ResponseEntity<Merchant>(a, HttpStatus.OK);
	}

}
