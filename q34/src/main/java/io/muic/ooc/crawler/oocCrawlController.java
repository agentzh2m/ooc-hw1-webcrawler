package io.muic.ooc.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class oocCrawlController {
    public oocCrawlController() throws Exception{
        final String ROOT_FOLDER = "/Users/Hamuel/Documents/CS-stuff/ooc/hw1/q34/tmp";
        final int CRAWLER_NUM = 4;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(ROOT_FOLDER);
        config.setIncludeBinaryContentInCrawling(true);
        final String CRAWL_DOMAIN = "https://cs.muic.mahidol.ac.th/courses/ooc/docs/";
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        controller.addSeed(CRAWL_DOMAIN);
        oocCrawler.configure(CRAWL_DOMAIN, "docs");
        controller.start(oocCrawler.class, CRAWLER_NUM);

    }
}
