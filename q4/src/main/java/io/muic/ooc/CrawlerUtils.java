package io.muic.ooc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CrawlerUtils {
    public static Set<String> getLinkFromURL(String content) throws IOException {
        Document doc = Jsoup.parse(content);
        Elements links = doc.getElementsByTag("a");
        Set<String> allURL = new HashSet<String>();
        for(Element link: links){allURL.add(link.attr("href"));}
        return allURL;
    }
    public static boolean cleanURL(String url){
        if(url.length() > 0 && !url.startsWith("http://") && !url.startsWith("#")){
            String[] strTok = url.split("#");
            if(strTok.length > 1){
                return true;
            }
            return true;
        }
        return false;
    }
}
