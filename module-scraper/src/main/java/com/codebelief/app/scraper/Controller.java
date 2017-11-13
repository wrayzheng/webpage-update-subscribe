package com.codebelief.app.scraper;

import java.util.LinkedList;
import java.util.Map;

import com.codebelief.app.rwDatabase.GetURL;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author Surflyan
 */

public class Controller {
    //public static void main(String[] args) throws Exception {
	
	//存储从数据库url 表获取的待爬取url map，addSeed使用
    // 同时在MyCrawler 通过调用 get 方法获取，保证读取和写入时数据库中的数据是一致的，避免在爬取时，有新增加url 
    public static Map<String, LinkedList<Integer>> urlMap = GetURL.getAllUrl();; 
                                 
	public static void execute() throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        //设置并行爬虫个数
        int numberOfCrawlers = 2;
 
        CrawlConfig config = new CrawlConfig();
        //设置存放爬虫中间信息的文件目录
        config.setCrawlStorageFolder(crawlStorageFolder);
        
        //设置爬取深度
        config.setMaxDepthOfCrawling(0);
        
        //设置是否爬取二进制数据的页面
        config.setIncludeBinaryContentInCrawling(false);
        
        //避免极快访问服务器，带来负荷，阻断请求，设置请求前等待200毫秒（默认）
        config.setPolitenessDelay(200);
        
        //重新开启爬虫
        //config.setResumableCrawling(true);
        
        //初始化爬虫配置信息 
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
 
      
        /*
         *  为每个爬虫添加初始爬取页面，后面以每个页面发现的链接作为爬取对象
         *  将数据库中要爬取的url添加到爬取列表中
         */
        //controller.addSeed("http://today.hit.edu.cn/css2010/style.css?123");
//        controller.addSeed("http://today.hit.edu.cn/");
//        controller.addSeed("http://today.hit.edu.cn/phb/1.htm");
//        controller.addSeed("http://www.sina.com.cn/");
//        controller.addSeed("http://www.tsinghua.edu.cn/publish/newthu/index.html");
        
        //note: map.values and map.keySet 顺序是否一致（检查）
        for (String url : urlMap.keySet()) {
        	controller.addSeed(url);
        }
 
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.startNonBlocking(MyCrawler.class, numberOfCrawlers);
        
        Thread.sleep(1*1000);
        
        //controller.shutdown();
        controller.waitUntilFinish();
        
    }
	/*
	 * 从数据库url表获取待爬取的url map
	 * 定义为static，
	 */
	
}