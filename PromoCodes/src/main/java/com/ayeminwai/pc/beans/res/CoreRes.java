package com.ayeminwai.pc.beans.res;

import com.ayeminwai.pc.constant.APICodes;
import com.ayeminwai.pc.util.DateUtilJ8;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CoreRes {

	String responseCode;
	
	String responseText;
	
	String resDateTime;
	
	public void getCoreSuccessResponse() {
		this.responseCode = APICodes.SUCCESS.code();
		this.responseText = APICodes.SUCCESS.getReasonPhrase();
		this.resDateTime = DateUtilJ8.getServerTime();
	}
	
	public void getCoreErrorResponse() {
		this.responseCode = APICodes.INTERNAL_SERVER_ERROR.code();
		this.responseText = APICodes.INTERNAL_SERVER_ERROR.getReasonPhrase();
		this.resDateTime = DateUtilJ8.getServerTime();
	}

	public CoreRes(String responseCode, String responseText) {
		this.responseCode = responseCode;
		this.responseText = responseText;
		this.resDateTime = DateUtilJ8.getServerTime();
	}

	public CoreRes() { }
	
}
