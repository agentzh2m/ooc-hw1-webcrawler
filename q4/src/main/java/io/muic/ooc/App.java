package io.muic.ooc;


public class App
{
    public static void main( String[] args ) throws Exception
    {
        String tempUrl = "https://cs.muic.mahidol.ac.th/courses/ooc/docs";
        CrawlerMain crawlerMain = new CrawlerMain(tempUrl, "../docs");
        System.out.println("total word count: " + crawlerMain.startCrawling());

    }
}
