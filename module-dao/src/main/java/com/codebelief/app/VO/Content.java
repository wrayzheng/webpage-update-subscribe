package com.codebelief.app.VO;

/**
 * 
 * @ClassName: Content
 * @Description: Define Content Type
 * @author ����
 * @date 2017��10��13��
 *
 */
public class Content {
	
	private int contentID;
	private int urlID;
	private String html;	//the Html code after the Content has Changed
	private String delta;	//the difference between two interfacing Html codes
	
	public Content(){}
	
	public Content(int ContentID){
		this.contentID = ContentID;
	}
	
	public Content(int ContentID, int UrlID, String Html, String Delta){
		this.contentID = ContentID;
		this.urlID = UrlID;
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
