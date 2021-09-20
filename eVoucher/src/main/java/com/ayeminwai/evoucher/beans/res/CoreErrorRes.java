package com.ayeminwai.evoucher.beans.res;

import java.util.Map;

import com.ayeminwai.evoucher.exception.EVoucherException;
import com.ayeminwai.evoucher.util.DateUtilJ8;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CoreErrorRes extends CoreRes {

	Map<String, String> errors;

	public CoreErrorRes() {
	}
	
	public CoreErrorRes(String responseCode, String responseText) {
		super(responseCode, responseText);
	}

	public CoreErrorRes(Map<String, String> errors) {
		this.errors = errors; 
	}

	public void getCoreErrorResponse(EVoucherException eVoucherEX) {	
		
		this.responseCode = ""+eVoucherEX.getErrorCode().code();
		this.responseText = eVoucherEX.getErrorCode().getReasonPhrase();
		this.resDateTime = DateUtilJ8.getServerTime();

		if(eVoucherEX.getErrors() != null && !eVoucherEX.getErrors().isEmpty()) {
			this.errors = eVoucherEX.getErrors();
		}
		
	}


}
