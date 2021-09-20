package com.ayeminwai.evoucher.beans.req;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutReq extends CoreReq {
	@NotNull
	String evoucherId;
}
