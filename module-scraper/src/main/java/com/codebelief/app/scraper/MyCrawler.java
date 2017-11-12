package com.codebelief.app.scraper;

import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;
import com.codebelief.app.scraper.PageParser;


import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Surflyan <yanjiliang0128@outlook.com>
 */

public class MyCrawler extends WebCrawler {
	//正则匹配指定的后缀文件
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");
     
     /*
      * 决定哪些url需要抓取，返回true是需要的
      * 第一个参数封装了当前爬取 的页面信息，第二个参数封装了当前爬取页面的url信息
      * 本爬虫系统，只下载特定页面，无需爬取更深层次页面
      * 始终返回（false）,留作功能拓展使用
      */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return !FILTERS.matcher(href).matches()
                && href.startsWith("http://www.ics.uci.edu/");
     }
 
     /*
      * 处理爬取后的页面
      * 提取有效链接（指向文章的链接），调用writeLinkToDB写回数据库content表
      * 
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         System.out.println("URL: " + url); 
         
 
         if (page.getParseData() instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String html = htmlParseData.getHtml();
             String title = htmlParseData.getTitle();
             
             System.out.println("Title: "+ title);           
             String baseUri = url;
             Elements validLinks = PageParser.getLinks(html, baseUri);
             
             writeLinkToDB(url,validLinks);   //写回数据库
            
         }
     }
    
     /*
      * 将爬取的 html 页面写入文件（临时测试）
      */
     public void writeFile(String html) {
    	 try {
	    	 FileWriter fw = new FileWriter("html.txt");
	    	 fw.write(html);
	    	 fw.flush();
	    	 fw.close();
    	 }catch (IOException e) {
    		 e.printStackTrace();
    	}
     }
     
     /*
      * 将爬取的页面解析之后获得的有效链接写入数据库
      * 通过比较此次爬取页面的主url 和 添加爬取种子时urlMap 里的url 对比，找到urlID，从而写入内容表里（内容表主键为urlID）
      * 由于使用了Crawler4j 项目，在爬取时无法另外添加数据项（urlID），使之与爬取url 绑定， 这里只能通过重新比对找到urlID。
      */
     //TO-DO WriteLinkToDB(暂未实现,提前调用)
     private void writeLinkToDB(String url, Elements validLinks) {
    	 
    	 LinkedList<String> HrefList;    //存放链接
    	 LinkedList<String> TextList;    //存放链接标题
    	 Map<Integer, String> urlMap = Controller.urlMap;  //从controller 获取urlMap ，确保和添加crawler seed 时数据一致。
    	 
    	 for (int linkNum = 0; linkNum < validLinks.size(); linkNum++) { 	 
    		 String linkHref = validLinks.get(linkNum).attr("href");
    		 String linkText = validLinks.get(linkNum).text();
    		 HrefList.add(linkHref);
    		 TextList.add(linkText);
    		
    	 }  		 
         
    	 // 遍历urlMap 中的 url，通过和爬取后的页面的主 url 比较，来找到对应urlID，从而写入对应的content 表里。
         for (Map.Entry<Integer, String> entry : urlMap.entrySet()) {
        	 if (entry.getValue().equals(url)) {
        		 WriteLinkToDB(entry.getKey(),HrefList,TextList);
        	 }
         }
       }
     
}