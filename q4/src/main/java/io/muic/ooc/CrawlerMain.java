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
            String curNode = Q.pop(); cc++;
            System.out.println("total file downloaded: " + cc);
            String fullPathURL = curNode;
            String fullPathLocal = outPath +"/"+curNode.substring(urlStart.length()+1, curNode.length());
//            System.out.printf("cur node is %s stack size is %d\n",fullPathURL, Q.size());
            if(CrawlerUtils.htmlChecker(curNode)){
                String buff = crawlConnection.downloadHTMLFile(fullPathURL, fullPathLocal);
//                System.out.printf("from: %s \n to: %s \n", fullPathURL, fullPathLocal);
                Set<String> links = CrawlerUtils.getLinkFromURL(buff, fullPathURL);
                for(String link: links){
                    if(CrawlerUtils.pathChecker(link, urlStart)){
                        String procPath = CrawlerUtils.cleanURL(link);
                        if(!visited.contains(procPath)) {
                            Q.push(procPath);
                            visited.add(procPath);
                        }
                    }
                }
            }else{
                crawlConnection.downloadBinaryFile(fullPathURL, fullPathLocal); cc++;
                System.out.println("Total file downloaded: " + cc);
//                System.out.printf("save binary %s at %s", CrawlerUtils.fileFromPath(curNode), curNode);
            }
        }
    }
}
