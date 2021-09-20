package com.ayeminwai.pc.beans.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromoCodeValidateRes extends CoreRes {

	private String phoneNo;
	private String expireDate;
	private String status;
	
}
