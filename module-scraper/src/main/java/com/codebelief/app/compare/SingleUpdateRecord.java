/*
 * @(#)module-scraper --- SingleUpdateRecord.java 
 */
package com.codebelief.app.compare;

/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class SingleUpdateRecord {
	private String url;
	private String title;
	
	public SingleUpdateRecord(){}
	
	public SingleUpdateRecord(String url, String title){
		this.url = url;
		this.title = title;
	}
	
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	@Override
	public String toString(){
		return title + "\n" + url;
	}
	
}
