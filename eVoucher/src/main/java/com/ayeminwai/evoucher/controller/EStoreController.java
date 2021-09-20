package com.ayeminwai.evoucher.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayeminwai.evoucher.beans.req.CheckoutReq;
import com.ayeminwai.evoucher.beans.req.EVoucherDetailsListReq;
import com.ayeminwai.evoucher.beans.req.EVoucherListReq;
import com.ayeminwai.evoucher.beans.req.PaymentMethodsReq;
import com.ayeminwai.evoucher.beans.req.PaymentReq;
import com.ayeminwai.evoucher.beans.req.PurchaseHistoryReq;
import com.ayeminwai.evoucher.config.GlobalConfig;
import com.ayeminwai.evoucher.constant.ISystem.IRquestMapping;
import com.ayeminwai.evoucher.service.EStoreService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@SuppressWarnings({"rawtypes","unchecked"})
@RequestMapping(value = GlobalConfig.ESTORE_END_POINT + GlobalConfig.API_VERSION, headers = IRquestMapping.HEADER)
public class EStoreController {
	
	@Autowired
	EStoreService eStroeService;

	@PostMapping(value = "eVouchers")
	public ResponseEntity<HashMap> eVouchers(@Valid @RequestBody  EVoucherListReq request) {
		log.info("In eVouchers method");
		
		HashMap<String, String> eVouchersRes = null;
		
		eVouchersRes = eStroeService.eVouchers(request);
		
		return new ResponseEntity<HashMap>(eVouchersRes, HttpStatus.OK);
	}
	
	@PostMapping(value = "eVoucherDetails")
	public ResponseEntity<HashMap> eVoucherDetails(@Valid @RequestBody  EVoucherDetailsListReq request) {
		log.info("In eVoucherDetails method");
		
		HashMap<String, String> eVoucherDetailsRes = null;
		
		eVoucherDetailsRes = eStroeService.eVoucherDetails(request);
		
		return new ResponseEntity<HashMap>(eVoucherDetailsRes, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "checkout")
	public ResponseEntity<HashMap> checkout(@Valid @RequestBody CheckoutReq request) {
		log.info("In checkout method");
		
		HashMap<String, String> checkoutRes = null;
		
		checkoutRes = eStroeService.checkout(request);
		
		return new ResponseEntity<HashMap>(checkoutRes, HttpStatus.OK);
	}
	
	@PostMapping(value = "paymentMethods")
	public ResponseEntity<HashMap> paymentMethods(@Valid @RequestBody PaymentMethodsReq request) {
		log.info("In paymentMethods method");
		
		HashMap<String, String> paymentMethodsRes = null;
		
		paymentMethodsRes = eStroeService.paymentMethods(request);
		
		return new ResponseEntity<HashMap>(paymentMethodsRes, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "payment")
	public ResponseEntity<HashMap> payment(@Valid @RequestBody PaymentReq request) {
		log.info("In payment method");
		
		HashMap<String, String> paymentRes = null;
		
		paymentRes = eStroeService.payment(request);
		
		return new ResponseEntity<HashMap>(paymentRes, HttpStatus.OK);
	}
	
	@PostMapping(value = "verifyPromoCode")
	public ResponseEntity<HashMap> verifyPromoCode(@Valid @RequestBody PaymentReq request) {
		log.info("In verifyPromoCode method");
		
		HashMap<String, String> verifyPromoCodeRes = null;
		
		//verifyPromoCodeRes = eStroeService.paymentMethods(request);
		
		return new ResponseEntity<HashMap>(verifyPromoCodeRes, HttpStatus.OK);
	}
	
	@PostMapping(value = "purchaseHistory")
	public ResponseEntity<HashMap> purchaseHistory(@Valid @RequestBody PurchaseHistoryReq request) {
		log.info("In purchaseHistory method");
		
		HashMap<String, String> purchaseHistoryRes = null;
		
		purchaseHistoryRes = eStroeService.paymentMethods(request);
		
		return new ResponseEntity<HashMap>(purchaseHistoryRes, HttpStatus.OK);
	}
}
