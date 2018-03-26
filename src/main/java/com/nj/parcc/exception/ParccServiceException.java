package com.nj.parcc.exception;
/**
 * Exception class for ParccService Application
 * @author Sriram
 *
 */
public class ParccServiceException  extends Exception {

	/**
	 * Marked intentionally as Private so that no body can initiate this exception class
	 * without any Exception message.
	 */
	@SuppressWarnings("unused")
	private ParccServiceException() {
		
	}

	public ParccServiceException(String message) {
        super(message);
    }

}
