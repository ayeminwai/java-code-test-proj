package com.ayeminwai.evoucher.beans.res;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseHistoryRes extends CoreRes {

	private List<Purchase> purchases;
	
}
