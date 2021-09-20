package com.ayeminwai.pc.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.ayeminwai.pc.constant.APICodes;

@SuppressWarnings("serial")
public class PromoCodeException extends ResponseStatusException {

	APICodes errorCode;
	Exception e;
	Map<String, String> errors;

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public APICodes getErrorCode() {
		return errorCode;
	}

	public PromoCodeException(HttpStatus status, String reason, APICodes errorCode, Map<String, String> errors) {
		super(status, reason);

		this.errorCode = errorCode;
		this.errors = errors;

	}

	public PromoCodeException(HttpStatus status, String reason, APICodes errorCode) {
		super(status, reason);
		this.errorCode = errorCode;
	}

	public PromoCodeException(HttpStatus status, String reason, APICodes errorCode, Exception e) {
		super(status, reason);
		this.errorCode = errorCode;
		this.e = e;
	}

	void addValidationErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Exception getE() {
		return e;
	}

}
