package com.securitytools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamValidator {
	
	//username can only be the combination of number and letters
	public  boolean checkUserName(String userName) {
		String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(userName);
		return m.matches();
	}
	
	//test whether a string is a valid number (integer or float)
	public boolean isNumber(String str) {
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isInt || isDouble;
	}


}
