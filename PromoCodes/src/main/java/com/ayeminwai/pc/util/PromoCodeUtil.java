package com.ayeminwai.pc.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class PromoCodeUtil {

	public BigDecimal roundAmount(BigDecimal amount) {
		amount = amount.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return amount;
	}

	public BigDecimal getBigAmount(Double amount) {
		BigDecimal bigAmt = new BigDecimal(amount);
		bigAmt = bigAmt.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return bigAmt;
	}

	public BigDecimal getBigAmount(String amount) {
		BigDecimal bigAmt = new BigDecimal(amount);
		bigAmt = bigAmt.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		return bigAmt;
	}

	public BigDecimal getSumAmount(Double amount, Double amount2) {

		BigDecimal bigAmt1 = new BigDecimal(amount);
		bigAmt1 = bigAmt1.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal bigAmt2 = new BigDecimal(amount2);
		bigAmt2 = bigAmt2.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal bigSum = bigAmt1.add(bigAmt2);
		bigSum = bigSum.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		return bigSum;
	}

	public BigDecimal getSubstractAmount(Double amount, Double amount2) {

		BigDecimal bigAmt1 = new BigDecimal(amount);
		bigAmt1 = bigAmt1.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal bigAmt2 = new BigDecimal(amount2);
		bigAmt2 = bigAmt2.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		BigDecimal bigSum = bigAmt1.subtract(bigAmt2);
		bigSum = bigSum.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		return bigSum;
	}
	
}
