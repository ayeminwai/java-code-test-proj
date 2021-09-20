package com.ayeminwai.pc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payment_method")
@Getter
@Setter
@ToString
public class PaymentMethod {

	@Id
	@Column(name = "pm_id")
	private Long pmId;
	
	@Column(name = "pm_code")
	private String pmCode;
	
	@Column(name = "pm_desc")
	private String pmDesc;
	
	@Column(name = "pm_status")
	private String pmStatus;
	
    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EVoucher> evoucher;
    
    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Payment> payment;
    
}
