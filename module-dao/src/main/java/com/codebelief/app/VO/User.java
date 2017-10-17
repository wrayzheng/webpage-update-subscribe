/*
 * @(#)SeeUTomorrow --- User.java 
 */
package com.codebelief.app.VO;

import java.sql.Time;
/**
 * 
 * @ClassName: User
 * @Description: Define User Type
 * @author 何涛
 * @date 2017年10月13日
 *
 */
public class User {
	private String UserName;	//no more than 16 characters
	private String Password;	//no more than 64 characters
	private String Email;		//no more than 64 characters
	private Time PushTime;		//java.sql.Time
	
	public User(String UserName){
		this.UserName=UserName;
	}
	
	public User(String UserName,String Password,String Email,Time PushTime){
		this.UserName=UserName;
		this.Password=Password;
		this.Email=Email;
		this.PushTime=PushTime;
	}
	
	/**
	 * 
	 * @param UserName
	 */
	public void setUserName(String UserName){
		this.UserName=UserName;
	}
	/**
	 * 
	 * @return UserName
	 */
	public String getUserName(){
		return this.UserName;
	}
	/**
	 * 
	 * @param Password
	 */
	public void setPassword(String Password){
		this.Password=Password;
	}
	/**
	 * 
	 * @return Password
	 */
	public String getPassword(){
		return this.Password;
	}
	/**
	 * 
	 * @param Email
	 */
	public void setEmail(String Email){
		this.Email=Email;
	}
	/**
	 * 
	 * @return Email
	 */
	public String getEmail(){
		return Email;
	}
	/**
	 * 
	 * @param PushTime
	 */
	public void setPushTime(Time PushTime){
		this.PushTime=PushTime;
	}
	/**
	 * 
	 * @return PushTime
	 */
	public Time getPushTime(){
		return PushTime;
	}
	
}

