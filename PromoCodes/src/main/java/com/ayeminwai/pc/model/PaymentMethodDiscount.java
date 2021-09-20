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
@Table(name = "payment_method_discount")
@Getter
@Setter
@ToString
public class PaymentMethodDiscount {

	@Id
	@Column(name = "pmd_id")
	private Long pmdId;
	
	@Column(name = "pmd_percent")
	private Integer pmdPercent;
	
	@Column(name = "pmd_percent_desc")
	private String pmdPrecentDesc;
	
	@Column(name = "pmd_status")
	private String pmdStatus;
	
    @OneToMany(mappedBy = "paymentMethodDiscount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EVoucher> evoucher;
    
    @OneToMany(mappedBy = "paymentMethodDiscount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Payment> payment;
    
}
