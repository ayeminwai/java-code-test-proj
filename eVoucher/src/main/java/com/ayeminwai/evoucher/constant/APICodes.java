package com.ayeminwai.evoucher.constant;

public enum APICodes {

	/********** Common Codes 101 - 199 *********/
	SUCCESS						("000","00","Successfully Processed")
	,PROCESSED_WITH_CONDITION	("001","05","Successfully Processed with Condition")

	,INTERNAL_SERVER_ERROR		("101","96","Internal Server Error")
	,APPL_ERR					("102","96","Application Error")
	,VALIDATION_ERR				("103","05","Validation Error")
	,API_CALL_ERR				("104","05","API Calling Error")
	,REQ_TIME_NOT				("105","05","Req Time is not available on Request")
	,TIME_VALIDATION			("106","05","Time Interval Validation Error")
	,TOKEN_ERROR				("109","05","OAuth Token Error")
	
	/********** Specific Codes for e-voucher 201 - 299 *********/
	,INVALID_EVOUCHER				("201","05","Invalid EVoucher")
	,MOBILENO_NOT_PRESENT			("202","05","Mobile No. is not available on Request")
	,TRANX_NOT_FOUND				("203","05","Transaction NOT Found")
	,EVOUCHER_NOT_ACTIVE			("204","05","EVoucher is not active")

	/********** Specific Codes for e-store 301 - 399 *********/
	,INVALID_QR_CODE				("301","05","Invalid QR Codes")
	,INVALID_PROMO_CODE				("302","05","Invalid Promo Codes")
	

	/********** Specific Codes for promo-codes 401 - 499 *********/
	,PROMO_CODE_DUPLICATE			("401","05","Duplicate Promo Codes")
	,PROMO_CODE_GEN_ERROR			("402","05","Promo Codes Generation Error"),
	;

	/**** No need to change below ****/

	private final String value;
	private final String responseCode;
	private final String reasonPhrase;

	APICodes(String value, String responseCode, String reasonPhrase) {
		this.value = value;
		this.responseCode = responseCode;
		this.reasonPhrase = reasonPhrase;
	}

	public String code() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	public String getResponseCode() {
		return responseCode;
	}

}
