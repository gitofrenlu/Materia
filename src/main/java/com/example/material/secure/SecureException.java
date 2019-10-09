

package com.example.material.secure;


import com.example.material.tools.IResultCode;
import com.example.material.tools.ResultCode;

public class SecureException extends RuntimeException {
	private static final long serialVersionUID = 2359767895161832954L;
	private final IResultCode resultCode;

	public SecureException(String message) {
		super(message);
		this.resultCode = ResultCode.UN_AUTHORIZED;
	}

	public SecureException(IResultCode resultCode) {
		super(resultCode.getMessage());
		this.resultCode = resultCode;
	}

	public SecureException(IResultCode resultCode, Throwable cause) {
		super(cause);
		this.resultCode = resultCode;
	}

	public Throwable fillInStackTrace() {
		return this;
	}

	public IResultCode getResultCode() {
		return this.resultCode;
	}
}
