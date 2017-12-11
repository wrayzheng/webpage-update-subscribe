/*
 * @(#)module-scraper --- PushMail.java 
 */
package com.codebelief.app.pushupdate;

import java.util.HashMap;
import java.util.Map;

import com.codebelief.app.DAO.IContentDAO;
import com.codebelief.app.DAO.IUrlDAO;
import com.codebelief.app.DAO.IUserDAO;
import com.codebelief.app.DAOFactory.ContentDAOFactory;
import com.codebelief.app.DAOFactory.UrlDAOFactory;
import com.codebelief.app.DAOFactory.UserDAOFactory;
import com.codebelief.app.DatabaseConnection.MySQLDatabaseConnection;
import com.codebelief.app.VO.Content;
import com.codebelief.app.VO.Url;
import com.codebelief.app.compare.SingleUpdateRecord;
import com.codebelief.app.mail.DeltaObject;
import com.codebelief.app.mail.SendMail;

import java.util.LinkedList;

/**
 * @author 何涛
 * @version 1st   on 2017年11月14日
 */
public class PushMail {
	public static void PushUpdateMail(String UserName) throws Exception{
		Map<Object, Object> parameters = new HashMap<Object, Object>();
		MySQLDatabaseConnection.initialDatabaseDeploy();
		IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
		LinkedList<Url> urlList = urlDAO.doFindAllEnabled(UserName);
		urlDAO.free();
		if(urlList.size()!=0){
			IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
			for(int i = 0; i < urlList.size(); i++){
				Content content = contentDAO.doFindAllByUrlID(urlList.get(i).getUrlID());
				LinkedList<SingleUpdateRecord> updateList = new LinkedList<SingleUpdateRecord>();
				if(content != null &&!"".equals(content.getDelta())){
					String[] Deltas = content.getDelta().split("\n\n");
					for(String Delta:Deltas)
						updateList.add(new SingleUpdateRecord(Delta));
					parameters.put("" + i, new DeltaObject(urlList.get(i).getTitle(),urlList.get(i).getUrl(),updateList));
				}
			}
			contentDAO.free();
			IUserDAO userDAO = UserDAOFactory.getUserDAOInstance();
			String email = userDAO.doFindEmail(UserName);
			userDAO.free();
			
			Map<Object, Object> urlMap = new HashMap<>();
			urlMap.put("urlMap", parameters);
			SendMail.sendMail("update", "网页更新订阅新内容推送", email, urlMap);
		}
	}
}
