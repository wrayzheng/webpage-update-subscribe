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
	
	private int urlID;
	private String userName;	//no more than 16 characters
	private String title;
	private String url;
	private boolean enabled;
	private boolean realTimePush;
	
	public Url(){}
	
	public Url(int UrlID){
		this.urlID = UrlID;
	}
	
	public Url(int UrlID, String UserName, String title, String Url, boolean enabled, boolean realTimePush){
		this.urlID = UrlID;
		this.userName = UserName;
		this.title = title;
		this.url = Url;
		this.enabled = enabled;
		this.realTimePush = realTimePush;
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