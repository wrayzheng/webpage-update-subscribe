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
	
	private int contentID;
	private int urlID;
	//private String title;	//the alias of a Url Content
	private String html;	//the Html code when the Content has Changed
	private String delta;	//the Difference between two interfacing Html codes
	
	public Content(int ContentID){
		this.contentID = ContentID;
	}
	
	public Content(int ContentID, int UrlID, String Html, String Delta){
		this.contentID = ContentID;
		this.urlID = UrlID;
		//this.title=Title;
		this.html = Html;
		this.delta = Delta;
	}
	
	/**
	 * 
	 * @return ContentID
	 */
	public int getContentID() {
		return contentID;
	}
	/**
	 * 
	 * @param contentID
	 */
	public void setContentID(int contentID) {
		this.contentID = contentID;
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
	 * @return Title
	 */
	/*public String getTitle() {
		return title;
	}*/
	/**
	 * 
	 * @param title
	 */
	/*public void setTitle(String title) {
		this.title = title;
	}*/
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
	
}
