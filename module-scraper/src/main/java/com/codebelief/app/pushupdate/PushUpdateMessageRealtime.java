/*
 * @(#)module-scraper --- PushUpdateMessageRealtime.java 
 */
package com.codebelief.app.pushupdate;

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
import com.codebelief.app.VO.User;
import com.codebelief.app.compare.SingleUpdateRecord;
import com.codebelief.app.mail.DeltaObject;
import com.codebelief.app.mail.SendMail;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class PushUpdateMessageRealtime {
	/**
	 * 
	 * @Title: PushUpdateRealTime
	 * @Description: Push updating email real-time
	 * @throws Exception
	 */
	public static void PushUpdateRealTime() throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		IContentDAO ContentDAO = ContentDAOFactory.getContentDAOInstance();
		LinkedList<Content> changed = ContentDAO.doFindAllChanged();	//查找有变化的网页的更新内容（根据Delta是否为null）
		ContentDAO.free();
		Map<String,LinkedList<Url>> userToUrls = new HashMap<String, LinkedList<Url>>();
		Map<Integer,Content> urlToContent = new HashMap<Integer,Content>();
		for(Content cont: changed){
			int UrlID = cont.getUrlID();
			urlToContent.put(UrlID, cont);		//记录UrlID与cont的对应
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			Url url = urlDAO.doFind(UrlID);
			String userName = urlDAO.doFindUserName(UrlID);	//根据UrlID查找用户姓名
			urlDAO.free();
			if(!userToUrls.containsKey(userName))
				userToUrls.put(userName, new LinkedList<Url>());
			userToUrls.get(userName).add(url);		//记下用户对应的已更新的url
		}
		for(String username:userToUrls.keySet()){	//针对每个用户，将其所有更新的url的变化聚集起来一起发给用户
			IUserDAO userDAO = UserDAOFactory.getUserDAOInstance();
			User user = userDAO.doFindAll(username);	
			userDAO.free();
			Map<String, Object> parameters = new HashMap<String, Object>();
			LinkedList<Url> urlList = userToUrls.get(username);
			for(int i = 0;i< urlList.size();i++)
				for(Content content: changed)
					if(content.getUrlID() == urlList.get(i).getUrlID()){
						LinkedList<SingleUpdateRecord> updateList = new LinkedList<SingleUpdateRecord>();
						String[] Deltas = content.getDelta().split("\n\n");
						for(String Delta:Deltas)
							updateList.add(new SingleUpdateRecord(Delta));
						parameters.put("" + i, new DeltaObject(urlList.get(i).getTitle(),urlList.get(i).getUrl(),updateList));
					}
			String email = user.getEmail();
			SendMail.sendMail(email, parameters);
		}
		ContentDAO = ContentDAOFactory.getContentDAOInstance();
		for(Content cont:changed){		//将Delta置为空
			cont.setDelta("");
			ContentDAO.doUpdate(cont);
		}
		ContentDAO.free();
	}
	
}
