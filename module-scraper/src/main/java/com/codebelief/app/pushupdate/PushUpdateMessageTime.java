/*
 * @(#)module-scraper --- PushUpdateMessageTime.java 
 */
package com.codebelief.app.pushupdate;

import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;
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
/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
public class PushUpdateMessageTime {
	/**
	 * 
	 * @Title: PushUpdateTime
	 * @Description: Push update email on given time
	 * @param pushTime
	 * @throws Exception
	 */
	public static void PushUpdateTime(Time pushTime) throws Exception{
		MySQLDatabaseConnection.initialDatabaseDeploy();
		IUserDAO UserDAO = UserDAOFactory.getUserDAOInstance();
		LinkedList<User> userList = UserDAO.doFindAllByPushTime(pushTime);
		UserDAO.free();
		for(User user: userList){
			Map<Object, Object> parameters = new HashMap<Object, Object>();
			IUrlDAO urlDAO = UrlDAOFactory.getUrlDAOInstance();
			LinkedList<Url> urlList = urlDAO.doFindAll(user.getUserName());
			urlDAO.free();
			IContentDAO contentDAO = ContentDAOFactory.getContentDAOInstance();
			for(int i = 0; i<urlList.size(); i++){
				Content content = contentDAO.doFindAllByUrlID(urlList.get(i).getUrlID());
				LinkedList<SingleUpdateRecord> updateList = new LinkedList<SingleUpdateRecord>();
				if(content != null &&!"".equals(content.getDelta())){
					String[] Deltas = content.getDelta().split("\n\n");
					for(String Delta:Deltas)
						updateList.add(new SingleUpdateRecord(Delta));
					parameters.put("" + i, new DeltaObject(urlList.get(i).getTitle(),urlList.get(i).getUrl(), updateList));
					content.setDelta("");
					contentDAO.doUpdate(content);
				}
			}
			if(parameters.size() != 0){
				String email = user.getEmail();
				
				Map<Object, Object> urlMap = new HashMap<>();
				urlMap.put("urlMap", parameters);
				SendMail.sendMail("update", "网页更新订阅新内容推送", email, urlMap);
			}
			contentDAO.free();
		}
	}
}
