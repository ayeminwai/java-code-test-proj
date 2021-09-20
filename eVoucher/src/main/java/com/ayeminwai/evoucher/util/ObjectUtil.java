package com.ayeminwai.evoucher.util;

import com.ayeminwai.evoucher.beans.req.EVoucherCreateReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			System.out.println(mapper.writeValueAsString(new EVoucherCreateReq()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
