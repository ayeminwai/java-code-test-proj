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

import com.ayeminwai.evoucher.beans.req.ChangeStatusReq;
import com.ayeminwai.evoucher.beans.req.EVoucherCreateReq;
import com.ayeminwai.evoucher.beans.req.EVoucherUpdateReq;
import com.ayeminwai.evoucher.config.GlobalConfig;
import com.ayeminwai.evoucher.constant.ISystem.IRquestMapping;
import com.ayeminwai.evoucher.service.EVoucherService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@SuppressWarnings({"unchecked", "rawtypes"})
@RequestMapping(value = GlobalConfig.EVOUCHER_END_POINT + GlobalConfig.API_VERSION, headers = IRquestMapping.HEADER)
public class EVoucherController {

	@Autowired
	EVoucherService eVoucherService;

	@PostMapping(value = "createEVoucher")
	public ResponseEntity<HashMap> createEVoucher(@Valid @RequestBody  EVoucherCreateReq request) {
		log.info("In createEVoucher method");
		
		HashMap<String, String> eVoucherCreateRes = null;
		
		eVoucherCreateRes = eVoucherService.createEVoucher(request);
		
		return new ResponseEntity<HashMap>(eVoucherCreateRes, HttpStatus.OK);
	}
	

	@PostMapping(value = "updateEVoucher")
	public ResponseEntity<HashMap> updateEVoucher(@Valid @RequestBody  EVoucherUpdateReq request) {
		log.info("In updateEVoucher method");
		
		HashMap<String, String> eVoucherUpdateRes = null;
		
		eVoucherUpdateRes = eVoucherService.updateEVoucher(request);
		
		return new ResponseEntity<HashMap>(eVoucherUpdateRes, HttpStatus.OK);
	}
	
	@PostMapping(value = "setInActive")
	public ResponseEntity<HashMap> setInActive(@Valid @RequestBody  ChangeStatusReq request) {
		log.info("In setInActive method");
		
		HashMap<String, String> eVoucherUpdateRes = null;
		
		eVoucherUpdateRes = eVoucherService.setInActive(request);
		
		return new ResponseEntity<HashMap>(eVoucherUpdateRes, HttpStatus.OK);
	}
}
