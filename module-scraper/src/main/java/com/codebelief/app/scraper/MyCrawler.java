package com.codebelief.app.scraper;

import java.util.LinkedList;

import com.codebelief.app.compare.ContentHandler;
import com.codebelief.app.compare.SingleUpdateRecord;
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
     /*
      * 决定页面中哪些url需要抓取，返回true是需要的
      * 第一个参数封装了当前爬取的页面信息，第二个参数封装了当前爬取页面的url信息
      * 本爬虫系统，只下载特定页面，无需爬取更深层次页面
      * 始终返回（false）,留作功能拓展使用
      */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         return false;
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
             
             writeContentToDB(url,validLinks);   //写回数据库
         	 System.out.println("Saved updates to database.");
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
      * 通过使用此次爬取页面的主url（键）来在controller 的urlMap找到对应的urlID（值）
      * 由于使用了Crawler4j 项目，在爬取时无法另外添加数据项（urlID），使之与爬取url 绑定， 这里只能重新获取urlID
      */
     private void writeContentToDB(String url, Elements validLinks) {
    	 //存放链接标题和链接<singleUpdateRecord> 自定义数据类型
    	 LinkedList<SingleUpdateRecord> updateRecords = new LinkedList<SingleUpdateRecord>();

    	 for (int linkNum = 0; linkNum < validLinks.size(); linkNum++) { 	 
    		 String linkHref = validLinks.get(linkNum).attr("href");
    		 String linkText = validLinks.get(linkNum).text();
//    		 System.out.println(linkText);
//    		 System.out.println(linkHref);
    		 updateRecords.add(new SingleUpdateRecord(linkText, linkHref));
    	 }  		 
    	
  	 //从controller 获取urlMap, 确保和添加crawler seed 时数据一致。
  	 LinkedList<Integer> urlIDList = Controller.urlMap.get(url);
  	 
  	 
     for(int urlID: urlIDList) {
        //System.out.println("urlID"+ urlID);
        ContentHandler.updateProcess(urlID, updateRecords);
        }
     }
     
}
