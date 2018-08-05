package com.codebelief.app.bean;

/**
 * 
 * @ClassName: Content
 * @Description: Define Content Type
 * @author ����
 * @date 2017-10-13
 *
 */
public class Content {

	private int urlID;
	private String html;	//the Html code after the Content has Changed
	private String delta;	//the difference between two interfacing Html codes
	
	public Content(){}
	
	public Content(int UrlID){
		this.urlID = UrlID;
	}
	
	public Content(int UrlID, String Html, String Delta){
		this.urlID = UrlID;
		this.html = Html;
		this.delta = Delta;
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
	 * @return Html
	 */
	public String getHtml() {
		return html;
	}
	/**
	 * 
	 * @param html
	 */
	public void setHtml(String html) {
		this.html = html;
	}
	/**
	 * 
	 * @return Delta
	 */
	public String getDelta() {
		return delta;
	}
	/**
	 * 
	 * @param delta
	 */
	public void setDelta(String delta) {
		this.delta = delta;
	}

	@Override
	public String toString() {
		return "Content [urlID=" + urlID + ", html=" + html + ", delta=" + delta + "]";
	}
	
}
