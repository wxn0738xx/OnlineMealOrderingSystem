package com.securitytools;

import java.util.regex.Pattern;

public class SQLValidator {
	
    private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                                + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
 
    private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
 
    //used to prevent sql injection
	public boolean isValid(String str)
	    {
	        if (sqlPattern.matcher(str).find())
	        {
	            System.out.println("cannot pass the validation��str=" + str);
	            return false;
	        }
	        return true;
	    }  
	

}
