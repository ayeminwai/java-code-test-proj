package com.ayeminwai.evoucher.beans.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EVoucherDetailsRes {

	String evoucherId;
	String evoucherDetailId;
	String name;
	String phoneNo;
	String maxVoucherLimit;
	String giftPerUserLimit;
	String status;
	
	public EVoucherDetailsRes(String evoucherId, String evoucherDetailId, String name, String phoneNo,
			String maxVoucherLimit, String giftPerUserLimit, String status) {
		super();
		this.evoucherId = evoucherId;
		this.evoucherDetailId = evoucherDetailId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.maxVoucherLimit = maxVoucherLimit;
		this.giftPerUserLimit = giftPerUserLimit;
		this.status = status;
	}
	
}
