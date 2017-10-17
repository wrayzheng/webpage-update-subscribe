/*
 * @(#)SeeUTomorrow --- Url.java 
 */
package com.codebelief.app.VO;

/**
 * 
 * @ClassName: Url
 * @Description: Define Url Type
 * @author 何涛
 * @date 2017年10月13日
 *
 */
public class Url {
	
	private int UrlID;
	private String UserName;	//no more than 16 characters
	private String Url;
	private boolean Enable;
	private boolean RealTimePush;
	
	public Url(int UrlID){
		this.UrlID=UrlID;
	}
	
	public Url(int UrlID,String UserName,String Url){
		this.UrlID=UrlID;
		this.UserName=UserName;
		this.Url=Url;
		this.Enable=true;
		this.RealTimePush=true;
	}
	
	/**
	 * 
	 * @return UrlID
	 */
	public int getUrlID() {
		return UrlID;
	}
	/**
	 * 
	 * @param urlID
	 */
	public void setUrlID(int urlID) {
		UrlID = urlID;
	}
	/**
	 * 
	 * @return UserName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * 
	 * @return Url
	 */
	public String getUrl() {
		return Url;
	}
	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		Url = url;
	}
	/**
	 * 
	 * @return Enable
	 */
	public boolean isEnable() {
		return Enable;
	}
	/**
	 * 
	 * @param enable
	 */
	public void setEnable(boolean enable) {
		Enable = enable;
	}
	/**
	 * 
	 * @return RealTimePush
	 */
	public boolean isRealTimePush() {
		return RealTimePush;
	}
	/**
	 * 
	 * @param realTimePush
	 */
	public void setRealTimePush(boolean realTimePush) {
		RealTimePush = realTimePush;
	}
	
}