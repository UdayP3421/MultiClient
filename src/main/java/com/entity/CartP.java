package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

public class CartP  {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long cartId;
	
	
    
	


    
	 @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
		@JoinColumn(name="customerId")
	private Customer customerreg;

	
public CartP() {
	super();
	// TODO Auto-generated constructor stub
}




public Customer getCustomerreg() {
	return customerreg;
}


public void setCustomerreg(Customer customerreg) {
	this.customerreg = customerreg;
}


public CartP( Customer customerreg) {
	super();
	
	this.customerreg = customerreg;
}


public String total() {
	// TODO Auto-generated method stub
	return null;
}














	


	
	
	}
	 
	 

