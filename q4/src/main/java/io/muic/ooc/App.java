package io.muic.ooc;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        String tempUrl = "https://cs.muic.mahidol.ac.th/courses/ooc/docs/";
        CrawlerMain crawlerMain = new CrawlerMain(tempUrl, "docs/");
        crawlerMain.startCrawling();
//        System.out.println(CrawlerUtils.processPath("../images/javalogo.gif", "docs/legal/docaccessibility.html"));
//        System.out.println(FilenameUtils.getExtension("JPasswordField.AccessibleJPasswordField.html"));
//        System.out.println(CrawlerUtils.processPath("docs/api/javax/swing/JPasswordField.AccessibleJPasswordField.html",
//                "docs/api/javax/swing/JPasswordField.AccessibleJPasswordField.html#AccessibleJPasswordField--"));


    }
}
