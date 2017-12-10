/*
 * @(#)module-scraper --- CompareUrls.java 
 */
package com.codebelief.app.compare;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;
<<<<<<< HEAD
/**
 * @author 何涛
 * @version 1st   on 2017年11月12日
 */
=======


public class ContentHandler {
	private static IContentDAO contentDAO = null;
	
	public static void updateProcess(int UrlID, LinkedList<SingleUpdateRecord> updateRecords){
		try {
			CompareContent(UrlID, updateRecords);
			//testRemoveSomeRecords(UrlID, 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testRemoveSomeRecords(int UrlID, int numToRemove) throws Exception {
		contentDAO = ContentDAOFactory.getContentDAOInstance();
		Content content = contentDAO.doFindAllByUrlID(UrlID);
		String html = content.getHtml();
		
		String[] recordList = html.split("\n\n");
		String newHtml = "";

		for(int i = 0; i < recordList.length - numToRemove; i++) {
			newHtml += recordList[i] + "\n\n";
		}
		
		content.setHtml(newHtml);
		content.setDelta("");
		UpdateRecord(content);
	}
	
	public static void UpdateRecord(Content content){
		try {
			contentDAO.doUpdate(content);
			
			contentDAO.free();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void InsertRecord(Content content){
		try {
			contentDAO.doInsert(content.getUrlID(),content.getHtml(),content.getDelta());
			contentDAO.free();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void CompareContent(int UrlID, LinkedList<SingleUpdateRecord> updateRecords) throws Exception{
		contentDAO = ContentDAOFactory.getContentDAOInstance();
		Content content = contentDAO.doFindAllByUrlID(UrlID);
		

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
			UpdateRecord(content);
		}
		// 初始记录
		else{
			for(int i = 0; i < updateRecords.size(); i++)
				newDelta += updateRecords.get(i) + "\n\n";
			newHtml = newDelta;
			content = new Content();
			content.setUrlID(UrlID);
			content.setHtml(newHtml);
			// 设置初始Delta，修改第一次添加可改变系统在初始订阅网站是否推送
			content.setDelta(" ");
			InsertRecord(content);
		}
	}
	
	
}
