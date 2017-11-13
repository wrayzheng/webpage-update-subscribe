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
				for(String oldSingleUpdateRecord : oldUrlLinkAndTitles){
					if(!newSingleUpdateRecords.get(i).equals(oldSingleUpdateRecord)){
						newDelta += newSingleUpdateRecords.get(i) + "\n\n";
						break;
					}
				}
				newHtml += newSingleUpdateRecords.get(i) + "\n\n";
			}
			content.setHtml(newHtml);
			content.setDelta(newDelta);
			UpdateRecord(content);
		}
		else{
			for(int i = 0; i < newSingleUpdateRecords.size(); i++)
				newDelta += newSingleUpdateRecords.get(i) + "\n\n";
			newHtml = newDelta;
			content = new Content();
			content.setUrlID(UrlID);
			content.setHtml(newHtml);
			content.setDelta(newDelta);
			InsertRecord(content);
		}
	}
	
	
}
