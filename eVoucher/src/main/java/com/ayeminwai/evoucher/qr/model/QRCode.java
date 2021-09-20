package com.ayeminwai.evoucher.qr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QRCode {

	private String evoucherId;
	private String evoucherDtlId;
	private String phoneNo;
	private String promoCode;
}
