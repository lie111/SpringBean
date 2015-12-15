package com.test.app.services.impl;

public class WorkWithFile {
	
	public String getExtension(String fullPath){
	
		return fullPath.substring(fullPath.lastIndexOf(".") + 1);		
	}
	
	
}
