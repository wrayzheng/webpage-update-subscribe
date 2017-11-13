package com.codebelief.app.rwDatabase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.Content;

public class GetURL {
	

	public void getURL() throws Exception {
		MySQLDatabaseConnection.initialDatabaseDeploy();

	}
	
	public static Map<String, LinkedList<Integer>> getAllUrl() {		
		IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
		Map<Integer,String> tempUrlMap = urlDAO.getAllUrl();
		
		Map<String, LinkedList<Integer>> urlMap = new HashMap<String, LinkedList<Integer>>();
		
		for(Map.Entry<Integer,String> entry : tempUrlMap.entrySet()) {
			String url = entry.getValue();
			if(urlMap.containsKey(url)) {
				LinkedList<Integer> tempList = urlMap.get(url);
				tempList.add(entry.getKey());
			}
			else {
				LinkedList<Integer> intList = new LinkedList<Integer>();
				intList.add(entry.getKey());
				urlMap.put(url, intList);
			}
		}
		return urlMap; 
	}
	
}
