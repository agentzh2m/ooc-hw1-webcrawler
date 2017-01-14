package io.muic.ooc;


public class App
{
    public static void main( String[] args ) throws Exception
    {
//        String tempUrl = "https://cs.muic.mahidol.ac.th/courses/ooc/docs/";
//        CrawlerMain crawlerMain = new CrawlerMain(tempUrl, "docs/");
//        crawlerMain.startCrawling();
        String path = "./../legal/cpyr.html";
        String curPath = "docs/api/overview-summary.html";
        System.out.println(CrawlerUtils.processPath(path, curPath));
        System.out.println(CrawlerUtils.relChecker(path));

    }
}
