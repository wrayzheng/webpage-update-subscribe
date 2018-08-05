/*
 * @(#)module-scraper --- PushUpdateMessageRealtime.java 
 */
package com.codebelief.app.pushupdate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codebelief.app.bean.Content;
import com.codebelief.app.bean.Url;
import com.codebelief.app.bean.User;
import com.codebelief.app.compare.SingleUpdateRecord;
import com.codebelief.app.dao.ContentDao;
import com.codebelief.app.dao.UrlDao;
import com.codebelief.app.dao.UserDao;
import com.codebelief.app.mail.DeltaObject;
import com.codebelief.app.mail.SendMail;

/**
 * @author 何涛
 * @version 1st   on 2017年11月13日
 */
@Component
public class PushUpdateMessageRealtime {
	
	private static ContentDao contentDao;
	private static UrlDao urlDao;
	private static UserDao userDao;
	
	/**
	 * 
	 * @Title: PushUpdateRealTime
	 * @Description: Push updating email real-time
	 * @throws Exception
	 */
	public static void PushUpdateRealTime() throws Exception{
		List<Content> changed = contentDao.doFindAllChanged();	//查找有变化的网页的更新内容（根据Delta是否为null）
		Map<String,LinkedList<Url>> userToUrls = new HashMap<String, LinkedList<Url>>();
		Map<Integer,Content> urlToContent = new HashMap<Integer,Content>();
		for(Content cont: changed){
			int UrlID = cont.getUrlID();
			Url url = urlDao.doFind(UrlID);
			if(url.isEnabled() && url.isRealTimePush()){
				urlToContent.put(UrlID, cont);		//记录UrlID与cont的对应
				String userName = urlDao.doFindUserName(UrlID);	//根据UrlID查找用户姓名
				if(!userToUrls.containsKey(userName))
					userToUrls.put(userName, new LinkedList<Url>());
				userToUrls.get(userName).add(url);		//记下用户对应的已更新的url
			}
		}
		for(String username:userToUrls.keySet()){	//针对每个用户，将其所有更新的url的变化聚集起来一起发给用户
			User user = userDao.doFindAll(username);	
			Map<Object, Object> parameters = new HashMap<Object, Object>();
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
			
			Map<Object, Object> urlMap = new HashMap<>();
			urlMap.put("urlMap", parameters);
			SendMail.sendMail("update", "网页更新订阅新内容推送", email, urlMap);
		}
		for(Content cont:changed){		//将Delta置为空
			cont.setDelta("");
			contentDao.doUpdate(cont);
		}
	}
	
	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		PushUpdateMessageRealtime.urlDao = urlDao;
	}
	
	@Autowired
	public void setContentDao(ContentDao contentDao) {
		PushUpdateMessageRealtime.contentDao = contentDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		PushUpdateMessageRealtime.userDao = userDao;
	}
}
