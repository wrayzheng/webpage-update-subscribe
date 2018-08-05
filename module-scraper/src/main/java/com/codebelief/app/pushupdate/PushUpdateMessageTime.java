/*
 * @(#)module-scraper --- PushUpdateMessageTime.java 
 */
package com.codebelief.app.pushupdate;

import java.sql.Time;
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
public class PushUpdateMessageTime {
	
	private static UserDao userDao;
	private static UrlDao urlDao;
	private static ContentDao contentDao;
	
	/**
	 * 
	 * @Title: PushUpdateTime
	 * @Description: Push update email on given time
	 * @param pushTime
	 * @throws Exception
	 */
	public static void PushUpdateTime(Time pushTime) throws Exception{
		List<User> userList = userDao.doFindAllByPushTime(pushTime);
		for(User user: userList){
			Map<Object, Object> parameters = new HashMap<Object, Object>();
			List<Url> urlList = urlDao.doFindAllEnabled(user.getUserName());
			for(int i = 0; i<urlList.size(); i++){
				if(!urlList.get(i).isRealTimePush()){
					Content content = contentDao.doFindAllByUrlID(urlList.get(i).getUrlID());
					LinkedList<SingleUpdateRecord> updateList = new LinkedList<SingleUpdateRecord>();
					if(content != null &&!"".equals(content.getDelta())){
						String[] Deltas = content.getDelta().split("\n\n");
						for(String Delta:Deltas)
							updateList.add(new SingleUpdateRecord(Delta));
						parameters.put("" + i, new DeltaObject(urlList.get(i).getTitle(),urlList.get(i).getUrl(), updateList));
						content.setDelta("");
						contentDao.doUpdate(content);
					}
				}
			}
			if(parameters.size() != 0){
				String email = user.getEmail();
				Map<Object, Object> urlMap = new HashMap<>();
				urlMap.put("urlMap", parameters);
				SendMail.sendMail("update", "网页更新订阅新内容推送", email, urlMap);
			}
		}
	}
	
	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		PushUpdateMessageTime.urlDao = urlDao;
	}
	
	@Autowired
	public void setContentDao(ContentDao contentDao) {
		PushUpdateMessageTime.contentDao = contentDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		PushUpdateMessageTime.userDao = userDao;
	}
}
