/*
 * @(#)module-scraper --- PushMail.java 
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
import com.codebelief.app.compare.SingleUpdateRecord;
import com.codebelief.app.dao.ContentDao;
import com.codebelief.app.dao.UrlDao;
import com.codebelief.app.dao.UserDao;
import com.codebelief.app.mail.DeltaObject;
import com.codebelief.app.mail.SendMail;

/**
 * @author 何涛
 * @version 1st   on 2017年11月14日
 */
@Component
public class PushMail {
	
	private static ContentDao contentDao;
	private static UrlDao urlDao;
	private static UserDao userDao;
	
	public static void PushUpdateMail(String UserName) throws Exception{
		Map<Object, Object> parameters = new HashMap<Object, Object>();
		List<Url> urlList = urlDao.doFindAllEnabled(UserName);
		if(urlList.size()!=0){
			for(int i = 0; i < urlList.size(); i++){
				Content content = contentDao.doFindAllByUrlID(urlList.get(i).getUrlID());
				LinkedList<SingleUpdateRecord> updateList = new LinkedList<SingleUpdateRecord>();
				if(content != null &&!"".equals(content.getDelta())){
					String[] Deltas = content.getDelta().split("\n\n");
					for(String Delta:Deltas)
						updateList.add(new SingleUpdateRecord(Delta));
					parameters.put("" + i, new DeltaObject(urlList.get(i).getTitle(),urlList.get(i).getUrl(),updateList));
				}
			}
			String email = userDao.doFindEmail(UserName);
			
			Map<Object, Object> urlMap = new HashMap<>();
			urlMap.put("urlMap", parameters);
			SendMail.sendMail("update", "网页更新订阅新内容推送", email, urlMap);
		}
	}
	
	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		PushMail.urlDao = urlDao;
	}
	
	@Autowired
	public void setContentDao(ContentDao contentDao) {
		PushMail.contentDao = contentDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		PushMail.userDao = userDao;
	}
}
