package com.codebelief.app.test;

import com.codebelief.app.DAO.*;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.DatabaseConnection.DatabaseConnectionFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;
import com.codebelief.app.DAOFactory.*;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
/**
 * @author 何涛
 * @version 1st   on 2017年10月14日
 */
public class DAOTest {
	public static void main(String[] args) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		/*UrlModifyAction newModify = new UrlModifyAction();
		newModify.setUrlID(0);
		newModify.setUrl("www.google.com");
		newModify.setUrlTitle("google");
		newModify.updateUrl();
		newModify.updateUrlTitle();
		newModify.disable();
		newModify.setIntegratedPush();*/
		IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
		Content content = new Content(0, 1,"cc","cc");
		contentDAO.doUpdate(content);
		
		System.out.println("Success!");
	}
}
