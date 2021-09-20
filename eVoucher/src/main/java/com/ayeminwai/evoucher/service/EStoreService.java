package com.ayeminwai.evoucher.service;

import java.util.HashMap;

import com.ayeminwai.evoucher.beans.req.CheckoutReq;
import com.ayeminwai.evoucher.beans.req.EVoucherDetailsListReq;
import com.ayeminwai.evoucher.beans.req.EVoucherListReq;
import com.ayeminwai.evoucher.beans.req.PaymentMethodsReq;
import com.ayeminwai.evoucher.beans.req.PaymentReq;
import com.ayeminwai.evoucher.beans.req.PurchaseHistoryReq;

@SuppressWarnings("rawtypes")
public interface EStoreService {

	public HashMap eVouchers(EVoucherListReq request);

	public HashMap eVoucherDetails(EVoucherDetailsListReq request);

	public HashMap<String, String> checkout(CheckoutReq request);

	public HashMap<String, String> paymentMethods(PaymentMethodsReq request);

	public HashMap<String, String> payment(PaymentReq request);

	public HashMap<String, String> paymentMethods(PurchaseHistoryReq request);

	
}
