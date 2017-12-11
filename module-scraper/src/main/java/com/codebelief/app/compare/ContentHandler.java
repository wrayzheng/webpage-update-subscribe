/*
 * @(#)module-scraper --- CompareUrls.java 
 */
package com.codebelief.app.compare;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;


/**
 * @author 何涛
 * @version 1st   on 2017年11月12日
 */
public class ContentHandler {
	private static IContentDAO contentDAO = null;
	
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
	 * 删除部分网页内容（测试用）
	 * 对于某个用户订阅的所有 Url，将对应 Content 中的 Html 字段的最后 N 条内容移除
	 * @author Wray
	 */
	public static void testRemoveSomeRecords(String userName, int numToRemove) throws Exception {
		MySQLDatabaseConnection.initialDatabaseDeploy();
		IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
		contentDAO = ContentDAOFactory.getContentDAOInstance();
		
		LinkedList<Url> urls = urlDAO.doFindAll(userName);
		
		for(Url url : urls) {
			Content content = contentDAO.doFindAllByUrlID(url.getUrlID());
			if(content == null) continue;
			
			String html = content.getHtml();
			
			String[] recordList = html.split("\n\n");
			String newHtml = "";
	
			for(int i = 0; i < recordList.length - numToRemove; i++) {
				newHtml += recordList[i] + "\n\n";
			}
			
			content.setHtml(newHtml);
			content.setDelta("");
			contentDAO.doUpdate(content);
		}
		contentDAO.free();
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
			contentDAO.doUpdate(content);
			contentDAO.free();
		}
		// 初始记录
		else{
			for(int i = 0; i < updateRecords.size(); i++)
				newDelta += updateRecords.get(i) + "\n\n";
			newHtml = newDelta;
			content = new Content();
			content.setUrlID(UrlID);
			content.setHtml(newHtml);
			content.setDelta(newDelta);
			contentDAO.doInsert(content.getUrlID(),content.getHtml(),content.getDelta());
			contentDAO.free();
		}
	}
	
	
}
