/*
 * @(#)SeeUTomorrow --- Content.java 
 */
package com.codebelief.app.VO;

/**
 * 
 * @ClassName: Content
 * @Description: Define Content Type
 * @author 何涛
 * @date 2017年10月13日
 *
 */
public class Content {
	
	private int ContentID;
	private int UrlID;
	private String Title;	//the alias of a Url Content
	private String Html;	//the Html code when the Content has Changed
	private String Delta;	//the Difference between two interfacing Html codes
	
	public Content(int ContentID){
		this.ContentID=ContentID;
	}
	
	public Content(int ContentID,int UrlID,String Title,String Html,String Delta){
		this.ContentID=ContentID;
		this.UrlID=UrlID;
		this.Title=Title;
		this.Html=Html;
		this.Delta=Delta;
	}
	
	/**
	 * 
	 * @return ContentID
	 */
	public int getContentID() {
		return ContentID;
	}
	/**
	 * 
	 * @param contentID
	 */
	public void setContentID(int contentID) {
		ContentID = contentID;
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
	 * @return Title
	 */
	public String getTitle() {
		return Title;
	}
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		Title = title;
	}
	/**
	 * 
	 * @return Html
	 */
	public String getHtml() {
		return Html;
	}
	/**
	 * 
	 * @param html
	 */
	public void setHtml(String html) {
		Html = html;
	}
	/**
	 * 
	 * @return Delta
	 */
	public String getDelta() {
		return Delta;
	}
	/**
	 * 
	 * @param delta
	 */
	public void setDelta(String delta) {
		Delta = delta;
	}
	
}
