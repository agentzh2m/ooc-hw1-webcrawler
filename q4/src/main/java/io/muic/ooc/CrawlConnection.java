package io.muic.ooc;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
            System.out.println("Response code " + response.getStatusLine().getStatusCode());
            File f = new File(outPath);
            out = new FileOutputStream(f);
            out.write(IOUtils.toByteArray(response.getEntity().getContent()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) try {
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public String getHTMLFile(String url, String outPath){
        HttpGet request = new HttpGet(url);
        FileOutputStream out = null;
        try {
            HttpResponse response = client.execute(request);
            File f = new File(outPath);
            out = new FileOutputStream(f);
            out.write(IOUtils.toByteArray(response.getEntity().getContent()));
            return response.getEntity().getContent().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (out != null) try {
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
