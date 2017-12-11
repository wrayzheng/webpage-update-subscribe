/*
 * @(#)module-scraper --- PushUpdateTest.java 
 */
package com.codebelief.app.pushupdate;

import java.sql.Time;
import java.text.SimpleDateFormat;
/**
 * @author 何涛
 * @version 1st   on 2017年12月9日
 */
public class PushUpdateTest {
	public static void main(String[] args) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		java.util.Date d = null;
		try {  
	         d = format.parse("20:00:00");  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     }  
	     Time time = new Time(d.getTime()); 
	     PushUpdateMessageTime.PushUpdateTime(time);
	}
}
