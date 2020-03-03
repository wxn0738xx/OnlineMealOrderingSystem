package com.securitytools;

public class InterceptingValidator {
	private ParamValidator pValidator;
	private SQLValidator sqlValidator;
	
	public InterceptingValidator() {
		this.pValidator = new ParamValidator();
		this.sqlValidator = new SQLValidator();
	}
	
	//used when user register
	public  boolean validateUsername(String username) {
		return this.pValidator.checkUserName(username);
	}
	
	//used when enter set a price for a dish
	public boolean validatePrice(String price) {
		return this.pValidator.isNumber(price);
	}
	
	public boolean validateSQLJection(String str) {
		return this.sqlValidator.isValid(str);
	}
	
	
}
