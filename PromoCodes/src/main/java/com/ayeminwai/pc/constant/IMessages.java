package com.ayeminwai.pc.constant;

public interface IMessages {

	public interface IServer {
		public static final String INTERNAL_SERVER_ERROR =  "Server Error";
	}
	
	public interface IError {
		public static final String VALIDATION_FAILED =  "Fileds Validations Failed";
		public static final String INTERNAL_ERROR =  "Internal Error on Processing the Request";
		public static final String UNAUTHORIZED =  "Unauthorized Request";
		public static final String BAD_REQUEST =  "Some of the Validations Failed";
	}
}
