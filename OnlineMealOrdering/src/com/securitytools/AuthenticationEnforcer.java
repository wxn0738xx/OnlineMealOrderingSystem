package com.securitytools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import com.domain.Customer;
import com.domain.Manager;
import java.io.IOException;

public class AuthenticationEnforcer {
	
	public static Manager authenticateManager(String username, String password){
		Manager manager = Manager.login(username, jdkSHA1(password));
		if(manager.getManagerId().equals("-1")) {
			return null;
		}else {
			return manager;
		}
		
		
	}
	
	public static Customer authenticateCustomer(String username, String password){
		Customer customer = Customer.login(username, jdkSHA1(password));
		if(customer.getCid().equals("-1")) {
			return null;
		}else {
			return customer;
		}
	}
	
	public static String jdkSHA1(String pwd){
        MessageDigest digest;
        String result = "";
        try {
            digest = MessageDigest.getInstance("SHA");
            digest.update(pwd.getBytes());
            result = Hex.encodeHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return result;
    }

}
