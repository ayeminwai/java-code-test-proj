package com.ayeminwai.evoucher.service;

import java.util.HashMap;

import com.ayeminwai.evoucher.beans.req.ChangeStatusReq;
import com.ayeminwai.evoucher.beans.req.EVoucherCreateReq;
import com.ayeminwai.evoucher.beans.req.EVoucherUpdateReq;

@SuppressWarnings("rawtypes")
public interface EVoucherService {

	public HashMap createEVoucher(EVoucherCreateReq request);
	
	public HashMap updateEVoucher(EVoucherUpdateReq request);

	public HashMap<String, String> setInActive(ChangeStatusReq request);
	
}
