package com.ayeminwai.evoucher.beans.res;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EVoucherListRes extends CoreRes {

	private List<EVoucherRes> evouchers;
	
}
