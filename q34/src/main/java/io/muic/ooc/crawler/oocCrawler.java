package io.muic.ooc.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class oocCrawler extends WebCrawler {
    private static File storageFolder;
    private static String crawlDomain;

    public static void configure(String domain, String storageFolderName) {
        crawlDomain = domain;
        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
    }

   @Override
    public boolean shouldVisit(Page referringPage, WebURL url){
       String href = url.getURL().toLowerCase();
       return href.startsWith(crawlDomain);
   }

   @Override
    public void visit(Page page){
        URL pageURL = null;
       try {
           pageURL = new URL(page.getWebURL().getURL());
       } catch (MalformedURLException e) {
           e.printStackTrace();
       }
       String urlPath = pageURL.getPath();
//       System.out.println(urlPath);
       String localPath = storageFolder.getAbsolutePath()+ "/" + urlPath;
       try{
           File file = new File(localPath);
           if(!file.exists() && urlPath.charAt(urlPath.length()-1) == '/'){
               file.mkdirs();
           }else{
               String newDir = urlPath.substring(0, urlPath.lastIndexOf('/'));
               File tempFile = new File(storageFolder.getAbsolutePath()+"/"+newDir);
               if(!tempFile.exists()){tempFile.mkdirs();}
           }
           if(urlPath.charAt(urlPath.length()-1) != '/'){
               FileOutputStream fos = new FileOutputStream(new File(localPath));
               System.out.println("Add file to: " + localPath);
               IOUtils.write(page.getContentData(), fos);
           }
       }catch (IOException iex){
           iex.printStackTrace();
       }

   }
}
