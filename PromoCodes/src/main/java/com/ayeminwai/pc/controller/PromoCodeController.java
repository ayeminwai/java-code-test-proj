package com.ayeminwai.pc.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayeminwai.pc.beans.req.PromoCodeGenReq;
import com.ayeminwai.pc.beans.req.PromoCodeValidateReq;
import com.ayeminwai.pc.config.GlobalConfig;
import com.ayeminwai.pc.constant.ISystem.IRquestMapping;
import com.ayeminwai.pc.service.PromoCodeService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@SuppressWarnings({"unchecked", "rawtypes"})
@RequestMapping(value = GlobalConfig.PROMOCODE_END_POINT + GlobalConfig.API_VERSION, headers = IRquestMapping.HEADER)
public class PromoCodeController {

	@Autowired
	PromoCodeService promoCodeService;

	@PostMapping(value = "generate")
	public ResponseEntity<HashMap> generate(@Valid @RequestBody PromoCodeGenReq request) {
		log.info("In generate method");
		
		HashMap<String, String> generateRes = null;
		
		generateRes = promoCodeService.generate(request);
		
		return new ResponseEntity<HashMap>(generateRes, HttpStatus.OK);
	}
	
	@PostMapping(value = "validatePromoCode")
	public ResponseEntity<HashMap> validatePromoCode(@Valid @RequestBody PromoCodeValidateReq request) {
		log.info("In validatePromoCode method");
		
		HashMap<String, String> validatePromoCodeRes = null;
		
		validatePromoCodeRes = promoCodeService.validatePromoCode(request);
		
		return new ResponseEntity<HashMap>(validatePromoCodeRes, HttpStatus.OK);
	}
}
