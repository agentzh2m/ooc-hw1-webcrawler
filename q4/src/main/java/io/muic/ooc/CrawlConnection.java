package io.muic.ooc;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class CrawlConnection {
    private HttpClient client;
    public CrawlConnection(){
        client = HttpClientBuilder
                .create()
                .build();
    }
    public boolean downloadBinaryFile(String url, String outPath){
        HttpGet request = new HttpGet(url);
        FileOutputStream out = null;
        try {
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == 200){
                File f = new File(outPath);
                handlePath(outPath);
                out = new FileOutputStream(f);
                out.write(IOUtils.toByteArray(response.getEntity().getContent()));
                out.close();
                return true;
            }else{
                System.out.printf("file %s fail to download", url);
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    public String downloadHTMLFile(String url, String outPath){
        HttpGet request = new HttpGet(url);
        FileOutputStream out = null;
        try {
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == 200){
                File f = new File(outPath);
                handlePath(outPath);
                out = new FileOutputStream(f);
                byte[] buff = IOUtils.toByteArray(response.getEntity().getContent());
                out.write(buff);
                out.close();
                return new String(buff);
            }else{
                System.out.printf("file %s fail to download", url);
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void handlePath(String path){
        String[] pathTok = path.split("/");
        String fileDirStr = "";
        for(int i = 0; i < pathTok.length-1; i++)
            fileDirStr+=pathTok[i]+"/";
        System.out.println(fileDirStr);
        File fileDir = new File(fileDirStr);
        if(pathTok[pathTok.length-1].length() > 0){
            if(!fileDir.exists()) fileDir.mkdirs();
        }
    }

}
