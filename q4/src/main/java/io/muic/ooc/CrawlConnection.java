package io.muic.ooc;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CrawlConnection {
    private CloseableHttpClient client;
    private final int TIME_OUT = 10;
    public CrawlConnection(){
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(TIME_OUT*1000)
                .setConnectTimeout(TIME_OUT*1000)
                .setConnectionRequestTimeout(TIME_OUT*1000)
                .build();
        PoolingHttpClientConnectionManager oConnectionMgr = new PoolingHttpClientConnectionManager();
        oConnectionMgr.setMaxTotal(20000);
        oConnectionMgr.setDefaultMaxPerRoute(100);
        client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .setConnectionManager(oConnectionMgr)
                .build();
    }
    public boolean downloadBinaryFile(String url, String outPath){
        HttpGet request = new HttpGet(url);
        FileOutputStream out = null;
        try {
            HttpResponse response = client.execute(request);
//            System.out.println("status code is " + response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == 200){
                File f = new File(outPath);
                handlePath(outPath);
                out = new FileOutputStream(f);
                InputStream in = response.getEntity().getContent();
                out.write(IOUtils.toByteArray(in));
                out.close();
                in.close();
//                System.out.println("Successfully download " + url);
                return true;
            }else{
                System.out.printf("file %s fail to download\n", url);
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if(out != null) out.close();
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
//            System.out.println("status code is "+ response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == 200){
                File f = new File(outPath);
                handlePath(outPath);
                out = new FileOutputStream(f);
                InputStream in = response.getEntity().getContent();
                byte[] buff = IOUtils.toByteArray(response.getEntity().getContent());
                in.close();
                out.write(buff);
                out.close();
//                System.out.println("Successfully download " + url);
                return new String(buff);
            }else{
                System.out.printf("file %s fail to download\n", url);
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
            try {
                if(out != null) out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return "";
        }
    }

    public void handlePath(String path){
        String[] pathTok = path.split("/");
        String fileDirStr = "";
        for(int i = 0; i < pathTok.length-1; i++)
            fileDirStr+=pathTok[i]+"/";
        File fileDir = new File(fileDirStr);
        if(pathTok[pathTok.length-1].length() > 0){
            if(!fileDir.exists()) fileDir.mkdirs();
        }
    }

}
