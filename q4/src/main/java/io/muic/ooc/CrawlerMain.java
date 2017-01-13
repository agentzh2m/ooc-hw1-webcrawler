package io.muic.ooc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class CrawlerMain {
    private String urlStart;
    private String outPath;
    public CrawlerMain(String urlStart, String outPath){
        this.urlStart = urlStart;
        this.outPath = outPath;
    }
    public void startCrawling(){
        CrawlConnection crawlConnection = new CrawlConnection();
        String initSt = crawlConnection.downloadHTMLFile(urlStart, outPath+"/index.html");
        Deque<String> Q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Q.push(urlStart+"/"+"index.html");
        while (!Q.isEmpty()){
            String curNode = Q.pop();
            if(CrawlerUtils.htmlChecker(curNode) && !visited.contains(curNode)){
                String buff = crawlConnection.downloadHTMLFile(urlStart+"/"+curNode, outPath+"/"+curNode);
                System.out.printf("from: %s \n to: %s", urlStart+"/"+curNode, outPath+"/"+curNode);
                visited.add(curNode);
                Set<String> links = CrawlerUtils.getLinkFromURL(buff);
                for(String link: links){
                    if(CrawlerUtils.pathChecker(link)){
                        if(CrawlerUtils.relChecker(link))
                            Q.push(CrawlerUtils.resolvePath(link, curNode));
                        else
                            Q.push(link);
                    }
                }
            }else{
                crawlConnection.downloadBinaryFile(urlStart+"/"+curNode, outPath+"/"+curNode);
            }
        }
    }
}
