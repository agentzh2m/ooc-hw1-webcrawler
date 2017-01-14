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
        Deque<String> Q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        Q.push("index.html");
        while (!Q.isEmpty()){
            String curNode = Q.pop();
            String fullPathURL = urlStart+curNode;
            String fullPathLocal = outPath+curNode;
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.printf("cur node is %s stack size is %d\n",fullPathURL, Q.size());
            if(CrawlerUtils.htmlChecker(curNode)){
                String buff = crawlConnection.downloadHTMLFile(fullPathURL, fullPathLocal);
//                System.out.printf("from: %s \n to: %s \n", fullPathURL, fullPathLocal);
                Set<String> links = CrawlerUtils.getLinkFromURL(buff);
                for(String link: links){
                    if(CrawlerUtils.pathChecker(link)){
                        String procPath = CrawlerUtils.processPath(link, curNode);
                        if(!visited.contains(procPath)) {
                            Q.push(procPath);
                            visited.add(procPath);
                        }
                    }
                }
            }else{
                crawlConnection.downloadBinaryFile(fullPathURL, fullPathLocal);
//                System.out.printf("save binary %s at %s", CrawlerUtils.fileFromPath(curNode), curNode);
            }
        }
    }
}
