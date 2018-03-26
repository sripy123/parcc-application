package com.nj.parcc.exception;
/**
 * Enum class for Error Messages
 * @author Sriram
 *
 */
public enum ErrorMessages {
	PARCC_000("PARCC_000","PARCC_000","Retrieved the results Successfully."),
	PARCC_001("PARCC_001","PARCC_001","Result created Successfully."),
	PARCC_002("PARCC_002","PARCC_002","Result updated Successfully."),
	PARCC_003("PARCC_003","PARCC_003","Delete All not supported."),
	PARCC_004("PARCC_004","PARCC_004","Deleted Successfully."),
	PARCC_005("PARCC_005","PARCC_005","Invalid Student Mark, it shd. be in the range of 0 and 100."),
	PARCC_006("PARCC_006","PARCC_006","Student name cannot be blank."),
	PARCC_007("PARCC_007","PARCC_007","Student school cannot be blank."),
	PARCC_008("PARCC_008","PARCC_008","Student suject name cannot be blank."),
	PARCC_500("PARCC_500","PARCC_500","Internal Server Error.");
	
	private final String matchEntry;
	private final String code;
	private final String defaultMessage;

	private ErrorMessages(String matchEntry, String code, String defaultMessage) {
		this.matchEntry = matchEntry;
		this.code = code;
		this.defaultMessage = defaultMessage;
	}
	
	public String getMatchEntry() {
		return matchEntry;
	}

	public String getCode() {
		return code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}
}