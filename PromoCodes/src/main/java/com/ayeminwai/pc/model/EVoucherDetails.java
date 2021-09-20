package com.ayeminwai.pc.model;

import java.io.Serializable;

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
@Table(name = "evoucher_details")
@Getter
@Setter
@ToString
public class EVoucherDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evoucher_details_id")
	private Long evoucherDetailsId;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evoucher_id", nullable = false)
	private EVoucher evoucher;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bt_id", nullable = false)
	private BuyType buyType;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_no")
	private Long phoneNo;
	
	@Column(name = "max_limit")
	private Long maxLimit;
	
	@Column(name = "gift_per_user_limit")
	private Long giftPerUserLimit;
	
	@Column(name = "evoucher_details_status")
	private String evoucherDetailsStatus;
	
	@OneToOne(mappedBy = "evoucherDetail")
	private Payment payments;

}
