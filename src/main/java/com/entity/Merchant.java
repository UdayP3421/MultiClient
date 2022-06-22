package com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;



@Entity
@Table(name="merchant")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Merchant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long merchantId;
	
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, message = "Username must contain 3 characters.")
	private String merchantUsername;
	
	@NotBlank(message = "Password is mandatory")
	/*
	 * @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$",
	 * message="Password must contain a lowercase character, " +
	 * "a uppercase character and a digit, minimum length must bers")e 6 charact
	 */
	private String merchantpassword;
	
	
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private List<Services> service;

	public List<Services> getService() {
		return service;
	}

	public void setService(List<Services> service) {
		this.service = service;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantUsername() {
		return merchantUsername;
	}

	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
	}

	public String getMerchantpassword() {
		return merchantpassword;
	}

	public void setMerchantpassword(String merchantpassword) {
		this.merchantpassword = merchantpassword;
	}


	
	
	
}
