package com.codebelief.app.bean;

import java.sql.Time;
/**
 * 
 * @ClassName: User
 * @Description: Define User Type
 * @author ����
 * @date 2017-10-13
 *
 */
public class User {
	private String userName;	//no more than 16 characters
	private String password;	//no more than 64 characters
	private String email;		//no more than 64 characters
	private Time pushTime;		//java.sql.Time
	
	public User(){}
	
	public User(String userName){
		this.userName = userName;
	}
	
	public User(String userName, String password, String email, Time pushTime){
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.pushTime = pushTime;
	}
	
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	 * 
	 * @return userName
	 */
	public String getUserName(){
		return this.userName;
	}
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	/**
	 * 
	 * @return password
	 */
	public String getPassword(){
		return this.password;
	}
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}
	/**
	 * 
	 * @return email
	 */
	public String getEmail(){
		return email;
	}
	/**
	 * 
	 * @param pushTime
	 */
	public void setPushTime(Time pushTime){
		this.pushTime = pushTime;
	}
	/**
	 * 
	 * @return pushTime
	 */
	public Time getPushTime(){
		return pushTime;
	}
	
}