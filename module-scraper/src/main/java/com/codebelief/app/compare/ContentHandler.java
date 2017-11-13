/*
 * @(#)module-scraper --- CompareUrls.java 
 */
package com.codebelief.app.compare;

import java.util.LinkedList;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;
/**
 * @author ����
 * @version 1st   on 2017��11��12��
 */
public class ContentHandler {
	private static IContentDAO contentDAO = null;
	
	public static void updateProcess(int UrlID, LinkedList<SingleUpdateRecord> updateRecords){
		System.out.println("content:");
		Content content = null;
		try {
			content = CompareContent(UrlID, updateRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UpdateRecode(content);
	}
	
	public static void UpdateRecode(Content content){
		try {
			contentDAO.doUpdate(content);
			
			contentDAO.free();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Content CompareContent(int UrlID, LinkedList<SingleUpdateRecord> newSingleUpdateRecords) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		contentDAO = ContentDAOFactory.getContentDAOInstance();
		Content content = contentDAO.doFindAllByUrlID(UrlID);
		System.out.println("content:"+content);
		String oldUrlLinksAndTitles = content.getHtml();
		System.out.println("oldUrlLinksAndTitles:"+oldUrlLinksAndTitles);
		String newDelta = "";
		String newHtml = "";
		if(oldUrlLinksAndTitles != null){
			String[] oldUrlLinkAndTitles = oldUrlLinksAndTitles.split("\n\n");
			for(int i = 0; i < newSingleUpdateRecords.size(); i++){
				for(String oldSingleUpdateRecord : oldUrlLinkAndTitles){
					if(!newSingleUpdateRecords.get(i).equals(oldSingleUpdateRecord)){
						newDelta += newSingleUpdateRecords.get(i) + "\n\n";
						break;
					}
				}
				newHtml += newSingleUpdateRecords.get(i) + "\n\n";
			}
		}
		else{
			for(int i = 0; i < newSingleUpdateRecords.size(); i++)
				newDelta += newSingleUpdateRecords.get(i) + "\n\n";
			newHtml = newDelta;
		}
		content.setHtml(newHtml);
		content.setDelta(newDelta);
		return content;
	}
	
	
}
