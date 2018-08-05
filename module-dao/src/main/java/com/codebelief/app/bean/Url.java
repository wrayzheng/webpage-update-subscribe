package com.codebelief.app.bean;

/**
 * 
 * @ClassName: Url
 * @Description: Define Url Type
 * @author ����
 * @date 2017-10-13
 *
 */
public class Url {
	
	private int urlID;
	private String userName;	//no more than 16 characters
	private String title;
	private String url;
	private boolean enabled;
	private boolean realTimePush;
	
	public Url(){}
	
	public Url(int urlID){
		this.urlID = urlID;
	}
	
	public Url(String userName, String title, String url, boolean enabled, boolean realTimePush){
		this.userName = userName;
		this.title = title;
		this.url = url;
		this.enabled = enabled;
		this.realTimePush = realTimePush;
	}
	
	public Url(int urlID, String userName, String title, String url, boolean enabled, boolean realTimePush) {
		this(userName, title, url, enabled, realTimePush);
		this.urlID = urlID;
	}
	
	/**
	 * 
	 * @return UrlID
	 */
	public int getUrlID() {
		return urlID;
	}
	/**
	 * 
	 * @param urlID
	 */
	public void setUrlID(int urlID) {
		this.urlID = urlID;
	}
	/**
	 * 
	 * @return UserName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 
	 * @return title
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 * 
	 * @return Url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 
	 * @return Enable
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * 
	 * @param enable
	 */
	public void setEnabled(boolean enable) {
		this.enabled = enable;
	}
	/**
	 * 
	 * @return RealTimePush
	 */
	public boolean isRealTimePush() {
		return realTimePush;
	}
	/**
	 * 
	 * @param realTimePush
	 */
	public void setRealTimePush(boolean realTimePush) {
		this.realTimePush = realTimePush;
	}
	
}