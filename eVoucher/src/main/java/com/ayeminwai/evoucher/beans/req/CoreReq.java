package com.ayeminwai.evoucher.beans.req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ayeminwai.evoucher.constant.IRegEx;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoreReq {

	@NotEmpty(message = "{not.empty}")
	@Size(min=14, max = 14, message = "{exact.size}")
	@Pattern(regexp = IRegEx.REQ_DATE_TIME, message = "{regex.datetime}")
	String reqDateTime;
}
