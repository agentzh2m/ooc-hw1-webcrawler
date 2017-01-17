package io.muic.ooc;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CrawlerUtils {
    public static Set<String> getLinkFromURL(String content, String baseURI){
        try{
            Document doc = Jsoup.parse(content, baseURI);
            Elements links = doc.getElementsByTag("a");
            Set<String> allURL = new HashSet<>();
            for(Element link: links){allURL.add(link.attr("abs:href"));}
            links = doc.getElementsByTag("img");
            for(Element link: links) allURL.add(link.attr("abs:src"));
            links = doc.getElementsByTag("link");
            for(Element link: links) allURL.add(link.attr("abs:href"));
            return allURL;
        }catch (Exception ex){
            ex.printStackTrace();
            return new HashSet<>();
        }
    }

    public static int getWordCount(String content){
        Document doc = Jsoup.parse(content);
        return doc.text().split("\\w+").length;
    }
    public static boolean pathChecker(String url, String startURL){
        if(url.length() > 0 && url.contains(startURL) && !url.startsWith("#") && !url.contains("javascript:show")
                 && !fileFromPath(url).contains("http")){
            String[] strTok = url.split("#");
            if(strTok.length > 1){
                return true;
            }
            return true;
        }
        return false;
    }

    public static String fileFromPath(String path){
        String[] strTok = path.split("/");
        String file = strTok[strTok.length-1];
        return file;
    }

    public static boolean htmlChecker(String path){
        String file = fileFromPath(path);
        return FilenameUtils.getExtension(file).equals("html");
    }

    public static String cleanURL(String path){
        if(path.length() > 0){
            String finalPath = path;
            if(path.contains("#")) finalPath =  path.substring(0, path.indexOf('#'));
            if(path.contains("?")) finalPath = path.substring(0, path.indexOf('?'));
            if(finalPath.charAt(finalPath.length()-1) == '/'){
                return "";
            }else{
                return finalPath;
            }
        }else{
            return "";
        }

    }

}
