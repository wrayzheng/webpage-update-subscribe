package com.codebelief.app.rwDatabase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codebelief.app.dao.UrlDao;

import edu.uci.ics.crawler4j.url.URLCanonicalizer;

@Component
public class GetURL {
	
	private static UrlDao urlDao;
	
	public static Map<String, LinkedList<Integer>> getAllUrl() throws Exception {		
		List<Map<String,String>> mapList = urlDao.getAllUrl();
		
		Map<String, LinkedList<Integer>> urlMap = new HashMap<String, LinkedList<Integer>>();
		
		for(Map<String,String> map : mapList) {
			String url = map.get("url");
			//url regularization
			String canonicalUrl = URLCanonicalizer.getCanonicalURL(url);
			if (canonicalUrl == null) continue;
			if(urlMap.containsKey(canonicalUrl)) {
				LinkedList<Integer> tempList = urlMap.get(canonicalUrl);
				tempList.add(Integer.valueOf(String.valueOf(map.get("urlID"))));
			}
			else {
				LinkedList<Integer> intList = new LinkedList<Integer>();
				intList.add(Integer.valueOf(String.valueOf(map.get("urlID"))));
				urlMap.put(canonicalUrl, intList);
			}
		}
		return urlMap; 
	}

	public static UrlDao getUrlDao() {
		return urlDao;
	}

	@Autowired
	public void setUrlDao(UrlDao urlDao) {
		GetURL.urlDao = urlDao;
	}
	
}
