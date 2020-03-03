package com.securitytools;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

public class AuthorizationProvider {
	
	private ArrayList<String> permissionCollection;
	
	public  boolean authorize(String role,String action) {
		if("manager".equals(role)) {
			//add the permission url or button or link name to permissionCollection
			if (permissionCollection.contains(action)) {
				return true;
			}
			else {
				return false;
			}
			
		}
		
		
		if("cusomter".equals(role)) {
			//add the permission url or button or link names to permissionCollection
			if (permissionCollection.contains(action)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
//		return this.permissionCollection;
	}

}
