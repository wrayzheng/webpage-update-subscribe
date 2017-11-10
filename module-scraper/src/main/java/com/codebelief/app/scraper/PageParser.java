package com.codebelief.app.scraper;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * @author Surflyan
 */

public class PageParser {
    
    /*
     * 定义白名单
     * 防止跨站脚本攻击
     * 限制标签范围，以适应提取HTML文章链接
     */
	private final static Whitelist myWhiteList  = Whitelist.relaxed();
	static {
		myWhiteList.removeTags("img");
		
	}
	
	/* 
	 * 过滤 Html 页面, 按照白名单规定元素提取 HTML 元素
	 */
	private static String htmlFilter(String html,String baseUri) {
		if(StringUtil.isBlank(html)) return "";
		return Jsoup.clean(html, baseUri,myWhiteList);
	}
	
	
    /* 
     * 过滤链接，提取有效文章链接
	 * 过滤掉链接文本描述少于5个字节的链接
	 */
    private static Elements linkFilter(Document doc) {  	
    	// 提取链接a[href]
    	Elements links = doc.select("a[href]");  	
    	// 过滤Text 字符小于5的链接，一般为分类链接，非目标链接
    	int linkNum = 0;

    	System.out.println("linkNumBefore: " + links.size());
    	for (linkNum = 0; linkNum < links.size(); linkNum++) {
    		   	 
    		 String linkHref = links.get(linkNum).attr("href");
    		 String linkText = links.get(linkNum).text();
    		
    		 if(linkText.length() <= 5) {
    			 links.remove(linkNum);
    			 linkNum--;	 
    			 continue;
    		 }  		 
    		 //System.out.println("Href:" + linkHref);
    		 //System.out.println("Text: " + linkText);
    	}
        System.out.println("linkNumAfter = " + links.size());
   
        return links;
    }
    
    /* 
     *  HTML 提取有效文章链接处理管道函数
     *  返回包含有效链接的 Elements 列表
     */
    public static Elements getLinks(String html, String baseUri) {
    	String filtHtml = htmlFilter(html,baseUri);
    	Document doc = Jsoup.parse(filtHtml);
   
        return linkFilter(doc);
    }
    
}
