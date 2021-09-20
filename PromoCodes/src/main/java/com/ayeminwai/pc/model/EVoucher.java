package com.ayeminwai.pc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "evoucher")
@Getter
@Setter
@ToString
public class EVoucher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evoucher_id")
	private Long evoucherId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "expire_date")
	private Date expireDate;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	//@Column(name = "payment_method_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod paymentMethod;
	
	//@Column(name = "payment_discount_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_discount_id", nullable = false)
	private PaymentMethodDiscount paymentMethodDiscount;
	
	@Column(name = "quantity")
	private Long quantity;
	
	//@Column(name = "buy_type_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buy_type_id", nullable = false)
	private BuyType buyType;
    
    @Column(name = "evoucher_status")
    private String evoucherStatus;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
    
    @OneToMany(mappedBy = "evoucher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EVoucherDetails> evoucherDetails;
    
    @OneToMany(mappedBy = "evoucher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Payment> payments;

}
