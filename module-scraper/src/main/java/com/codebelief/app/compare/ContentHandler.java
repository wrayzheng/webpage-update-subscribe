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


public class ContentHandler {
	private static IContentDAO contentDAO = null;
	
	public static void updateProcess(int UrlID, LinkedList<SingleUpdateRecord> updateRecords){
		try {
			CompareContent(UrlID, updateRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public static void CompareContent(int UrlID, LinkedList<SingleUpdateRecord> newSingleUpdateRecords) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		contentDAO = ContentDAOFactory.getContentDAOInstance();
		Content content = contentDAO.doFindAllByUrlID(UrlID);
		

		String newDelta = "";
		String newHtml = "";
		if(content != null){
			String oldUrlLinksAndTitles = content.getHtml();
			String[] oldUrlLinkAndTitles = oldUrlLinksAndTitles.split("\n\n");
			
			
			for(int i = 0; i < newSingleUpdateRecords.size(); i++){
				int flag = 0;
				for(String oldUrl: oldUrlLinkAndTitles) {
			
					String newUrl = newSingleUpdateRecords.get(i).toString();
					if(newUrl.equals(oldUrl))
					{  
						flag = 1;
						break;
					}
				}
				if(flag == 0) {
					newDelta += newSingleUpdateRecords.get(i) + "\n\n";
				}
				newHtml += newSingleUpdateRecords.get(i) + "\n\n";
			}
			content.setHtml(newHtml);
			content.setDelta(newDelta + content.getDelta());
			UpdateRecord(content);
		}
		// 初始记录
		else{
			for(int i = 0; i < newSingleUpdateRecords.size(); i++)
				newDelta += newSingleUpdateRecords.get(i) + "\n\n";
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
