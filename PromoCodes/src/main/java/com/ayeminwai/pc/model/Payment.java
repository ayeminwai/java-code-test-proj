package com.ayeminwai.pc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "purchase_history")
@Getter
@Setter
@ToString
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "purchase_id")
	private Long purchaseId;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evoucher_id", nullable = false)
	private EVoucher evoucher;
	
    @OneToOne
    @JoinColumn(name = "evoucher_details_id", referencedColumnName = "evoucher_details_id", columnDefinition = "LONGVARBINARY")
	private EVoucherDetails evoucherDetail;

	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "discount_amt")
	private BigDecimal discountAmount;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false)
	private PaymentMethod paymentMethod;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_discount_id", nullable = false)
	private PaymentMethodDiscount paymentMethodDiscount;
	
	@Column(name = "qrcode_image")
	private String qrcodeImage;
	
	@Column(name = "promo_code")
	private String promoCode;
	
	@Column(name = "phone_no")
	private Long phoneNo;
	
	@Column(name = "expire_date")
	private Date expireDate;
	
	@Column(name = "tranx_date")
	private Date tranxDate;
	
	@Column(name = "tranx_status")
	private String tranxStatus;
}
