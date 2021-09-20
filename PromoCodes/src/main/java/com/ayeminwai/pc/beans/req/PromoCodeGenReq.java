package com.ayeminwai.pc.beans.req;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoCodeGenReq extends CoreReq {
	@NotNull
	String evoucherId;
}
