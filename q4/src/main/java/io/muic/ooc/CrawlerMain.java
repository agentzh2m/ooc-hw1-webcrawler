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
        int cc = 0;
        CrawlConnection crawlConnection = new CrawlConnection();
        Deque<String> Q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Q.push(urlStart+"/index.html");
        while (!Q.isEmpty()){
            String fullPathURL = Q.pop(); cc++;
            String fullPathLocal = outPath +"/"+fullPathURL.substring(urlStart.length()+1, fullPathURL.length());
            System.out.printf("html-> sSize: %d,  cc: %d, %s\n", Q.size(), cc, fullPathLocal);
            if(CrawlerUtils.htmlChecker(fullPathURL)){
                String buff = crawlConnection.downloadHTMLFile(fullPathURL, fullPathLocal);
//                System.out.printf("from: %s \n to: %s \n", fullPathURL, fullPathLocal);
                Set<String> links = CrawlerUtils.getLinkFromURL(buff, fullPathURL);
                for(String link: links){
                    String procPath = CrawlerUtils.cleanURL(link);
                    if(CrawlerUtils.pathChecker(procPath, urlStart) &&
                            procPath.length() > 0 && !visited.contains(procPath)){
                        Q.push(procPath);
                        visited.add(procPath);
                    }
                }
            }else{
                crawlConnection.downloadBinaryFile(fullPathURL, fullPathLocal); cc++;
                System.out.printf("bin-> sSize: %d,  cc: %d\n", Q.size(), cc);
//                System.out.printf("save binary %s at %s", CrawlerUtils.fileFromPath(curNode), curNode);
            }
        }
    }
}
