package com.ayeminwai.pc.qr.model;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QRCode2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String evoucherDtlId;
	private String phoneNo;
	private String promoCode;
}
