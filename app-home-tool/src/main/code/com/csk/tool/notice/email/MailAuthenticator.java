package com.csk.tool.notice.email;

import javax.mail.Authenticator; 
import javax.mail.PasswordAuthentication; 

public class MailAuthenticator extends Authenticator{
	/** 
	  * 用户名 
	  */
	 private String username; 
	 /** 
	  * 密码 
	  */
	 private String password; 
	  
	 /** 
	  * 创建一个新的实例 MailAuthenticator. 
	  * 
	  * @param username 
	  * @param password 
	  */
	 public MailAuthenticator(String username, String password) { 
	  this.username = username; 
	  this.password = password; 
	 } 
	  
	 public String getPassword() { 
	  return password; 
	 } 
	   
	 protected PasswordAuthentication getPasswordAuthentication() { 
	  return new PasswordAuthentication(username, password); 
	 } 
	  
	 public String getUsername() { 
	  return username; 
	 } 
	  
	 public void setPassword(String password) { 
	  this.password = password; 
	 } 
	  
	 public void setUsername(String username) { 
	  this.username = username; 
	 } 
	  
}
