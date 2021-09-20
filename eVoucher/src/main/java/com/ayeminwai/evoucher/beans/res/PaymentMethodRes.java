package com.ayeminwai.evoucher.beans.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentMethodRes {

	private Long paymentMethodId;
	private String paymentMethodCode;
	private String paymentMethodDesc;
	private String paymentMethodStatus;
	
	public PaymentMethodRes(Long paymentMethodId, String paymentMethodCode, String paymentMethodDesc,
			String paymentMethodStatus) {
		super();
		this.paymentMethodId = paymentMethodId;
		this.paymentMethodCode = paymentMethodCode;
		this.paymentMethodDesc = paymentMethodDesc;
		this.paymentMethodStatus = paymentMethodStatus;
	}
	
}
