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
 * @author 何涛
 * @version 1st   on 2017年11月12日
 */
public class CompareContent {
	public static void CompareUrlLinks(int UrlID,
			LinkedList<String> urlLinks, 
			LinkedList<String> titles) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
		Content content = contentDAO.doFindAllByUrlID(UrlID);
		String oldUrlLinksAndTitles = content.getHtml();
		String newDelta = "";
		String newHtml = "";
		if(oldUrlLinksAndTitles != null){
			String[] oldUrlLinkAndTitles = oldUrlLinksAndTitles.split("\n\n");
			for(int i = 0; i < urlLinks.size(); i++){
				for(String oldUrlLinkAndTitle:oldUrlLinkAndTitles){
					String urlLink = oldUrlLinkAndTitle.split("\n")[0];
					if(urlLinks.get(i).equals(urlLink)){
						newDelta += urlLinks.get(i) + "\n" +titles.get(i) + "\n\n";
						break;
					}
				}
				newHtml += urlLinks.get(i) + "\n" + titles.get(i) + "\n\n";
			}
		}
		else{
			for(int i = 0; i < urlLinks.size(); i++)
				newDelta += urlLinks.get(i) + "\n" +titles.get(i) + "\n\n";
			newHtml = newDelta;
		}
		content.setHtml(newHtml);
		content.setDelta(newDelta);
		contentDAO.doUpdate(content);
		contentDAO.free();
	}
}
