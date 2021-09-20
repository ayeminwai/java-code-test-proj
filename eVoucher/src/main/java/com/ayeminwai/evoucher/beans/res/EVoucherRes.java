package com.ayeminwai.evoucher.beans.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EVoucherRes {

	String evoucherId;
	String title;
	String description;
	String expireDate;
	String image;
	String amount;
	String paymentMethod;
	String paymentMethodDiscount;
	String quantity;
	String buyType;
	
	public EVoucherRes(String evoucherId, String title, String description, String expireDate, String image,
			String amount, String paymentMethod, String paymentMethodDiscount, String quantity, String buyType) {
		super();
		this.evoucherId = evoucherId;
		this.title = title;
		this.description = description;
		this.expireDate = expireDate;
		this.image = image;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentMethodDiscount = paymentMethodDiscount;
		this.quantity = quantity;
		this.buyType = buyType;
	}
	
	
	
}
