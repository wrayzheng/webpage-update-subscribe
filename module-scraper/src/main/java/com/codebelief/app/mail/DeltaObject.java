package com.codebelief.app.mail;

import java.util.LinkedList;

import com.codebelief.app.compare.SingleUpdateRecord;

/**
 * @author: Wray Zheng
 * @date: 2017-11-13
 * @description: 存储单条订阅的更新内容
 */
public class DeltaObject {
	private String title;
	private String url;
	private LinkedList<SingleUpdateRecord> updateList;
	
	public DeltaObject(String title, String url, LinkedList<SingleUpdateRecord> updateList) {
		this.title = title;
		this.url = url;
		this.updateList = updateList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LinkedList<SingleUpdateRecord> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(LinkedList<SingleUpdateRecord> updateList) {
		this.updateList = updateList;
	}
	
}