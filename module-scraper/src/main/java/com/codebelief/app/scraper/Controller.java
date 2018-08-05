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


	//�洢�����ݿ�url ���ȡ�Ĵ���ȡurl map��addSeedʹ��
    //ͬʱ��MyCrawler ͨ������ get ������ȡ����֤��ȡ��д��ʱ���ݿ��е�������һ�µģ���������ȡʱ����������url

	public static Map<String, LinkedList<Integer>> urlMap;

    public static void main(String[] args) throws Exception { execute(); }

	public static void execute() throws Exception {
        urlMap = GetURL.getAllUrl();

        String crawlStorageFolder = "/data/crawl/root";

        //���ò����������
        int numberOfCrawlers = 2;

        CrawlConfig config = new CrawlConfig();
        //���ô�������м���Ϣ���ļ�Ŀ¼
        config.setCrawlStorageFolder(crawlStorageFolder);

        //������ȡ���
        config.setMaxDepthOfCrawling(0);

        //�����Ƿ���ȡ���������ݵ�ҳ��
        config.setIncludeBinaryContentInCrawling(false);

        //���⼫����ʷ��������������ɣ����������������ǰ�ȴ�200���루Ĭ�ϣ�
        config.setPolitenessDelay(200);

        //���¿�������
        //config.setResumableCrawling(true);

        //��ʼ������������Ϣ
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         *  Ϊÿ��������ӳ�ʼ��ȡҳ�棬������ÿ��ҳ�淢�ֵ�������Ϊ��ȡ����
         *  �����ݿ���Ҫ��ȡ��url��ӵ���ȡ�б���
         */


        //note: map.values and map.keySet ˳���Ƿ�һ�£���飩

        for (String url : urlMap.keySet()) {
        	controller.addSeed(url);
        }

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.startNonBlocking(MyCrawler.class, numberOfCrawlers);

        //�ȴ� 1 ���ӣ���ֹ������ȡ������վ��ֹ
        Thread.sleep(1000);

        controller.waitUntilFinish();
    }

}
