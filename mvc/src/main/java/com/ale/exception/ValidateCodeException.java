
package com.ale.exception;


/**
 * The type Validate code exception.
 * @author alewu
 */
public class ValidateCodeException extends RuntimeException {
	private static final long serialVersionUID = -7285211528095468156L;

	/**
	 * Instantiates a new Validate code exception.
	 */
	public ValidateCodeException() {
	}

	/**
	 * Instantiates a new Validate code exception.
	 *
	 * @param msg the msg
	 */
	public ValidateCodeException(String msg) {
		super(msg);
	}
}
