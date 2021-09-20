package com.ayeminwai.pc.service;

import java.util.HashMap;

import com.ayeminwai.pc.beans.req.PromoCodeGenReq;
import com.ayeminwai.pc.beans.req.PromoCodeValidateReq;

@SuppressWarnings("rawtypes")
public interface PromoCodeService {

	public HashMap generate(PromoCodeGenReq request);

	public HashMap<String, String> validatePromoCode(PromoCodeValidateReq request);

}
