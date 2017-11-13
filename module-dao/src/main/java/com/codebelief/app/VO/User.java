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
	private String userName;	//no more than 16 characters
	private String password;	//no more than 64 characters
	private String email;		//no more than 64 characters
	private Time pushTime;		//java.sql.Time
	
	public User(String UserName){
		this.userName = UserName;
	}
	
	public User(String UserName,String Password,String Email,Time PushTime){
		this.userName = UserName;
		this.password = Password;
		this.email = Email;
		this.pushTime = PushTime;
	}
	
	/**
	 * 
	 * @param UserName
	 */
	public void setUserName(String UserName){
		this.userName = UserName;
	}
	/**
	 * 
	 * @return UserName
	 */
	public String getUserName(){
		return this.userName;
	}
	/**
	 * 
	 * @param Password
	 */
	public void setPassword(String Password){
		this.password = Password;
	}
	/**
	 * 
	 * @return Password
	 */
	public String getPassword(){
		return this.password;
	}
	/**
	 * 
	 * @param Email
	 */
	public void setEmail(String Email){
		this.email = Email;
	}
	/**
	 * 
	 * @return Email
	 */
	public String getEmail(){
		return email;
	}
	/**
	 * 
	 * @param PushTime
	 */
	public void setPushTime(Time PushTime){
		this.pushTime = PushTime;
	}
	/**
	 * 
	 * @return PushTime
	 */
	public Time getPushTime(){
		return pushTime;
	}
	
}

