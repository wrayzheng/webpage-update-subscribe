/*
 * @(#)module-scraper --- CompareUrls.java 
 */
package com.codebelief.app.compare;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codebelief.app.bean.Content;
import com.codebelief.app.bean.Url;
import com.codebelief.app.dao.ContentDao;
import com.codebelief.app.dao.UrlDao;

/**
 * @author ����
 * @version 1st   on 2017��11��12��
 */
@Component
public class ContentHandler {
	private static UrlDao urlDao;
	private static ContentDao contentDao;
	
	public static void updateProcess(int UrlID, LinkedList<SingleUpdateRecord> updateRecords){
		try {
			CompareContent(UrlID, updateRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		testRemoveSomeRecords("Wray", 5);
	}
	
	/**
	 * ɾ��������ҳ���ݣ������ã�
	 * ����ĳ���û����ĵ����� Url������Ӧ Content �е� Html �ֶε���� N �������Ƴ�
	 * @author Wray
	 */
	public static void testRemoveSomeRecords(String userName, int numToRemove) throws Exception {
		
		List<Url> urls = urlDao.doFindAll(userName);
		
		for(Url url : urls) {
			Content content = contentDao.doFindAllByUrlID(url.getUrlID());
			if(content == null) continue;
			
			String html = content.getHtml();
			
			String[] recordList = html.split("\n\n");
			String newHtml = "";
	
			for(int i = 0; i < recordList.length - numToRemove; i++) {
				newHtml += recordList[i] + "\n\n";
			}
			
			content.setHtml(newHtml);
			content.setDelta("");
			contentDao.doUpdate(content);
		}
	}
	
	public static void UpdateRecord(Content content){
		try {
			contentDao.doUpdate(content);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void InsertRecord(Content content){
		try {
			contentDao.doInsert(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void CompareContent(int UrlID, LinkedList<SingleUpdateRecord> updateRecords) throws Exception{
		Content content = contentDao.doFindAllByUrlID(UrlID);
		
		String newDelta = "";
		String newHtml = "";
		if(content != null){
			String oldUrlLinksAndTitles = content.getHtml();
			String[] oldUrlLinkAndTitles = oldUrlLinksAndTitles.split("\n\n");
			for(int i = 0; i < updateRecords.size(); i++){
				boolean flag = false;
				for(String oldSingleUpdateRecord : oldUrlLinkAndTitles)
					if(updateRecords.get(i).toString().equals(oldSingleUpdateRecord)){
						flag = true;
			
								break;
					}
				if(!flag){
					newDelta += updateRecords.get(i) + "\n\n";
				}
				newHtml += updateRecords.get(i) + "\n\n";
			}
			content.setHtml(newHtml);
			content.setDelta(newDelta + content.getDelta());
			contentDao.doUpdate(content);
		}
		// ��ʼ��¼
		else{
			for(int i = 0; i < updateRecords.size(); i++)
				newDelta += updateRecords.get(i) + "\n\n";
			newHtml = newDelta;
			content = new Content();
			content.setUrlID(UrlID);
			content.setHtml(newHtml);
			content.setDelta(newDelta);
			contentDao.doInsert(content);
		}
	}
	
	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		ContentHandler.urlDao = urlDao;
	}
	
	@Autowired
	public void setContentDao(ContentDao contentDao) {
		ContentHandler.contentDao = contentDao;
	}
}
