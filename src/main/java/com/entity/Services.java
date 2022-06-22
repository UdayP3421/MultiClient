package com.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Services {
    
	@Id
	@GeneratedValue
	private Long serviceId;
	private String serviceName;
	private String serviceType;
	public double servicePrice;
	private String serviceDescription;
	
	
	
	
	
	
	


	@JsonBackReference
	@ManyToOne(targetEntity = Merchant.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "merchant_fk")
	private Merchant merchant;





	public Merchant getMerchant() {
		return merchant;
	}





	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}





	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Services(Long serviceId, String serviceName, String serviceType, double servicePrice,
			String serviceDescription) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.servicePrice = servicePrice;
		this.serviceDescription = serviceDescription;
	}





	public Long getServiceId() {
		return serviceId;
	}





	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}





	public String getServiceName() {
		return serviceName;
	}





	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}





	public String getServiceType() {
		return serviceType;
	}





	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}





	public double getServicePrice() {
		return servicePrice;
	}





	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}





	public String getServiceDescription() {
		return serviceDescription;
	}





	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}





	@Override
	public String toString() {
		return "Services [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceType=" + serviceType
				+ ", servicePrice=" + servicePrice + ", serviceDescription=" + serviceDescription + "]";
	}



	
	



















	

	
	
	
}
