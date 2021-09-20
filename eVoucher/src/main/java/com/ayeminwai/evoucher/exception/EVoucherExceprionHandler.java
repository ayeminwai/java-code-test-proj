package com.ayeminwai.evoucher.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ayeminwai.evoucher.beans.res.CoreErrorRes;
import com.ayeminwai.evoucher.constant.APICodes;
import com.ayeminwai.evoucher.constant.IMessages;
import com.ayeminwai.evoucher.constant.ISystem;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
@Log4j2
public class EVoucherExceprionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		HashMap errorResponse = getErrorMap(ex);

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EVoucherException.class)
	public final ResponseEntity<Object> handleCacisException(EVoucherException cacisEx, WebRequest request) {

		HashMap errorResponse = getErrorMap(cacisEx);

		return new ResponseEntity<>(errorResponse, cacisEx.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		EVoucherException cacisEx = new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.VALIDATION_FAILED, APICodes.VALIDATION_ERR, errors);

		HashMap errorResponse = getErrorMap(cacisEx);

		return new ResponseEntity<>(errorResponse, cacisEx.getStatus());

	}

	private HashMap getErrorMap(Object ex) {

		HashMap result= new HashMap();
		String errorDetail = "";

		CoreErrorRes objCoreErrorRes = new CoreErrorRes();
		objCoreErrorRes.getCoreErrorResponse();

		if(ex instanceof EVoucherException){

			objCoreErrorRes = new CoreErrorRes();
			objCoreErrorRes.getCoreErrorResponse((EVoucherException)ex);

			if(((EVoucherException) ex).getE() != null) {
				
				errorDetail = ((EVoucherException) ex).getE().getStackTrace()[0] + "-" + ((EVoucherException) ex).getE().getMessage();
				log.warn("errorDetail :: " + errorDetail);
				log.warn("errorDetail Length :: " + errorDetail.length());
				
				if(errorDetail.length() > 200) {
					errorDetail = errorDetail.substring(0, 200);
				}
			}

		}

		//APILog apiLog = StaticClass.getThreadLocalApi().get();
		
		//if(apiLog==null) {
		//	apiLog = new APILog();
		//}

		//apiLog.setApiCode(objCoreAPIErrorRes.getResponseCode());
		
		//if(!"".equals(errorDetail)) {
		//	apiLog.setErrorDetail(errorDetail);
		//}

		result.put(ISystem.IKey.IResponse.RESPONSE,objCoreErrorRes);

		return result;

	}

}
