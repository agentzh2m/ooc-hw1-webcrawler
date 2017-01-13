package io.muic.ooc;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CrawlerUtils {
    public static Set<String> getLinkFromURL(String content){
        try{
            Document doc = Jsoup.parse(content);
            Elements links = doc.getElementsByTag("a");
            Set<String> allURL = new HashSet<String>();
            for(Element link: links){allURL.add(link.attr("href"));}
            return allURL;
        }catch (Exception ex){
            ex.printStackTrace();
            return new HashSet<>();
        }
    }
    public static boolean pathChecker(String url){
        if(url.length() > 0 && !url.startsWith("http://") && !url.startsWith("#")){
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
        String[] fileTok = file.split("#");
        return fileTok[fileTok.length-1];
    }

    public static boolean htmlChecker(String path){
        String file = fileFromPath(path);
        return FilenameUtils.getExtension(file).equals("html");
    }

    public static boolean relChecker(String path){
        return path.contains("..");
    }

    //resolve path to the original absolute path
    public static String resolvePath(String path, String curPath){
        String[] pathTok = path.split("/");
        String[] curPathTok = curPath.split("/");
        int backCount = 0;
        for(String s: pathTok)
            if(s.equals("..")) backCount++;
        String resPath = "";
        for(int i = 0; i < curPathTok.length-backCount-1; i++)
            resPath+=curPathTok[i]+"/";
        for(int i = backCount; i < pathTok.length; i++)
            if(i != pathTok.length-1){
                resPath+= pathTok[i]+"/";
            }else{
                resPath+= pathTok[i];
            }
        return resPath;
    }
}
