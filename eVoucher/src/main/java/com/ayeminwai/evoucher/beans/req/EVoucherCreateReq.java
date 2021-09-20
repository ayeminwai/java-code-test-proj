package com.ayeminwai.evoucher.beans.req;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EVoucherCreateReq extends CoreReq {

	@NotNull
	String title;
	
	@NotNull
	String description;

	@NotNull
	String expireDate;
	
	@NotNull
	String image;
	
	@NotNull
	String amount;
	
	@NotNull
	String paymentMethod;
	
	@NotNull
	String paymentMethodDiscount;
	
	@NotNull
	String quantity;
	
	@NotNull
	String buyType; // O and G
	
	@NotNull
	String name;
	
	@NotNull
	String phoneNo;
	
	String giftPerUserLimit;
	
	@NotNull
	String maxEVoucherLimit;
	
	
	
}
