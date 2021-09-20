package com.ayeminwai.evoucher.beans.req;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentReq extends CoreReq {

	@NotNull
	String evoucherId;

}
