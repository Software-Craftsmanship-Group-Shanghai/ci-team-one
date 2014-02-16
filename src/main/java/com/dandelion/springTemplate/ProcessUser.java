package com.dandelion.springTemplate;

public interface ProcessUser {
	
	public void registerUser(String username,String password);
	
	public void activateUser(String username);
	
	public boolean validateUserIsEffictive(String username,String password) ;

}
