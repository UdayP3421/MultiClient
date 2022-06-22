package com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



@Entity

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long bookingOrderId;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_fk")
	private Customer customer;

	private String orderDate;
	
	@NotBlank(message = "Transaction mode can't be blank.")
	private String transactionMode;	
	private int quantity;
	private double totalCost;
	
	
	
	
	
	
	@OneToMany(targetEntity = Services.class, cascade = CascadeType.ALL) 
	@JoinColumn(name = "service_fk", referencedColumnName = "bookingOrderId")
	private List<Services> service;
	
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}









	public List<Services> getService() {
		return service;
	}

	public void setService(List<Services> service) {
		this.service = service;
	}



	public Long getBookingOrderId() {
		return bookingOrderId;
	}

	public void setBookingOrderId(Long bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}
	
	
	
}
