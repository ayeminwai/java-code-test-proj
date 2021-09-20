package com.ayeminwai.evoucher.beans.req;

import javax.validation.constraints.Pattern;

import com.ayeminwai.evoucher.constant.IRegEx;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseHistoryReq extends CoreReq {

	@Pattern(regexp = IRegEx.TRANX_STATUS, message = "{regex.tranxstatus}" )
	private String evoucherStatus; 
}
