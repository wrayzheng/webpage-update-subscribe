/*
 * @(#)module-scraper --- PushUpdateMessageRealtime.java 
 */
package com.codebelief.app.pushupdate;

import java.util.Map;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.Content;
import com.codebelief.app.mail.SendMail;

import java.util.HashMap;

/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class PushUpdateMessageRealtime {
	public static void PushUpdateRealTime() throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		IUrlDAO UrlDAO = UrlDAOFactory.getUrlDAOInstance();
		Map<Integer, String> UrlMap = UrlDAO.doFindAllEnabledAndRealTimePush();
		UrlDAO.free();
		for(Integer urlID: UrlMap.keySet()){
			IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
			Content content = contentDAO.doFindAllByUrlID(urlID);
			if(content != null && !"".equals(content.getDelta())){
				Map<String, Object>  parameters = new HashMap<String, Object>();
				parameters.put("name", UrlMap.get(urlID));
				SendMail.sendMail("2316367336@qq.com", parameters);
				content.setDelta("");
				content.setHtml("");
				contentDAO.doUpdate(content);
			}
		}
	}
	
}
