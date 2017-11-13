package com.codebelief.app.test;

import com.codebelief.app.DAO.*;
import com.codebelief.app.DAOFactory.*;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.*;
import com.codebelief.app.DAOFactory.*;

import java.sql.Time;
import java.util.LinkedList;
/**
 * @author ����
 * @version 1st   on 2017��10��14��
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
		//IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
		//Content content = new Content(0, 1,"cc","cc");
		//contentDAO.doUpdate(content);
		IUrlDAO UrlDAO = UrlDAOFactory.getUrlDAOInstance();
		//LinkedList<Url> url = UrlDAO.doFindAll("abc");
		//System.out.println(url);
		Url url = new Url(3,"hetao","baidu","http://www.baidu.com",true,true);
		UrlDAO.doUpdateUrl(2,"baidu.com");
		//UrlDAO.doInsert("abc", "baidu", "www.baidu.com", true, true);
		UrlDAO.free();
		
		System.out.println("Success!");
	}
}
