package com.ayeminwai.pc.constant;

public interface ISystem {

	public interface IRquestMapping {
		public final String HEADER = "Accept=application/json";
	}

	public interface IQualifier {

		public final String EVOUCHER_VALIDATION = "eVoucherValidationImpl";

	}

	public interface IKey {

		public interface IResponse {

			public final String DESC = "description";
			public final String RESPONSE = "response";
			public final String ERROR = "errors";

		}

	}

	public interface IStatus {

		public final String ACTIVE = "A";
		public final String BUY = "B";
		public final String CHECKOUT = "C";
		public final String INACTIVE = "I";

	}
}
