package com.codebelief.app.rwDatabase;

import java.util.Map;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.Content;

public class GetURL {
	

	public void etURL() throws Exception {
		MySQLDatabaseConnection.initialDatabaseDeploy();

	}
	
	public static Map<Integer,String> getAllUrl() {		
		IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
		Map<Integer,String> urlMap = urlDAO.getAllUrl();
		
		return urlMap;
	}
	
}
