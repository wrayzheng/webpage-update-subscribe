package com.codebelief.app.test;

import com.codebelief.app.DAO.*;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.DatabaseConnection.DatabaseConnectionFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;
import com.codebelief.app.DAOFactory.*;

import java.sql.Time;
/**
 * @author 何涛
 * @version 1st   on 2017年10月14日
 */
public class DAOTest {
	public static void main(String[] args) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		//MySQLDatabaseConnection mysql = DatabaseConnectionFactory.getMySQLDatabaseConnection();
		//User user=new User("何涛","hetao","15705657659@163.com",Time.valueOf("20:00:00"));
		IUserDAO userDAO=UserDAOFactory.getUserDAOInstance();
		//userDAO.doInsert(user);
		//userDAO.doUpdatePushTime("何涛", Time.valueOf("20:00:00"));
		//userDAO.doUpdatePasswordAndEmail("何涛", "hetao", "15705657659@163.com");
		/*User newUser=userDAO.doFindAll("何涛");
		System.out.println("UserName:"+newUser.getUserName());
		System.out.println("Password:"+newUser.getPassword());
		System.out.println("Email:"+newUser.getEmail());
		System.out.println("PushTime:"+newUser.getPushTime());*/
		/*String password=userDAO.doFindPassword("何涛");
		System.out.println("Passowrd:"+password);
		String Email=userDAO.doFindEmail("何涛");
		System.out.println("Email:"+Email);
		Time PushTime=userDAO.doFindPushTime("何涛");
		System.out.println("PushTime:"+PushTime);
		userDAO.free();*/
		
		//Url url=new Url(10010,"何涛","question","www.Github.com");
		//IUrlDAO urlDAO=UrlDAOFactory.getUrlDAOInstance();
		//urlDAO.doDelete(10010);
		//urlDAO.doInsert(url);
		/*Url newUrl=urlDAO.doFindAll(10010);
		System.out.println(newUrl.getUrl());
		System.out.println(newUrl.getUserName());
		System.out.println(newUrl.getUrlID());
		if(newUrl.isEnable())	System.out.println("true");
		if(newUrl.isRealTimePush())	System.out.println("true");*/
		/*System.out.println(urlDAO.doFindUrl(10010));
		System.out.println(urlDAO.doFindUserName(10010));
		System.out.println(""+urlDAO.doFindEnable(10010));
		System.out.println(""+urlDAO.doFindRealTimePush(10010));*/
		//urlDAO.doUpdate(10010, "www.stackOverflow.com", false, false);
		//urlDAO.doDelete(10010);
		//urlDAO.free();
		
		//Content content = new Content(1001,10010,"<html><head></head></html>","<head></head>");
		//IContentDAO contentDAO=ContentDAOFactory.getContentDAOInstance();
		//contentDAO.doInsert(content);
		/*Content newContent=contentDAO.doFindAll(1001);
		System.out.println(newContent.getUrlID());
		System.out.println(newContent.getTitle());
		System.out.println(newContent.getHtml());
		System.out.println(newContent.getDelta());*/
		/*System.out.println(contentDAO.doFindUrlID(1001));
		System.out.println(contentDAO.doFindTitle(1001));
		System.out.println(contentDAO.doFindHtml(1001));
		System.out.println(contentDAO.doFindDelta(1001));*/
		//contentDAO.doUpdateHtmlAndDelta(1001, "<html><body></body></html>", "<body></body>");
		//contentDAO.doDelete(1001);
		//contentDAO.Free();
		//MySQLDatabaseConnection dbc = DatabaseConnectionFactory.getMySQLDatabaseConnection();
		//MySQLDatabaseConnection.initialDatabaseDeploy();
		//MySQLDatabaseConnection mysql = DatabaseConnectionFactory.getMySQLDatabaseConnection();
		//System.out.println(System.getProperties().getProperty("file.separator"));
		System.out.println("Success!");
	}
}
