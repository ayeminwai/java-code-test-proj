package com.ayeminwai.pc.beans.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoCodeValidateReq extends CoreReq {


	String qrCode;
	

	String promoCode;
}
