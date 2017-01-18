package io.muic.ooc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class App 
{
    public static void main( String[] args )
    {
        final String SRC_URL = "http://www.oracle.com/technetwork/java/javase/documentation/jdk8-doc-downloads-2133158.html";
        System.out.println(DownloderOne(SRC_URL));
        System.out.println(DownloaderTwo(SRC_URL));
        System.out.println(DownloaderThree(SRC_URL));
    }
    //return true if successful and return false if not successful
    public static boolean DownloderOne(String url){
        BufferedInputStream in = null;
        FileOutputStream out = null;
        try{
            URL myURL = new URL(url);
            URLConnection yc = myURL.openConnection();
            in = new BufferedInputStream(yc.getInputStream());
            String[] urlTok = myURL.getPath().split("/");
            out = new FileOutputStream(urlTok[urlTok.length-1]+"_fdOne");
            int curByte = 0;
            byte[] buffer = new byte[8192];
            while ((curByte = in.read(buffer)) != -1){
                out.write(buffer, 0, curByte);
            }
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            try {
                if(in != null) in.close();
                if(out != null){
                    out.flush();
                    out.close();
                }
            }catch (IOException ix){
                ix.printStackTrace();
            }
        }
    }

    public static boolean DownloaderTwo(String url){
        URL myURL = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        String[] urlTok = myURL.getPath().split("/");
        File file = new File(urlTok[urlTok.length-1]+"_fdTwo");
        try {
            FileUtils.copyURLToFile(myURL, file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean DownloaderThree(String url){
        URL myURL = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        String[] urlTok = myURL.getPath().split("/");
        FileOutputStream out = null;
        try{
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == 200){
                out = new FileOutputStream(new File(urlTok[urlTok.length-1]+"_fdThree"));
                response.getEntity().writeTo(out);
                request.releaseConnection();
                IOUtils.closeQuietly(out);
                return true;
            }else {
                return false;
            }
        }catch (IOException ix){
            ix.printStackTrace();
            return false;
        }finally {
            if (out != null) IOUtils.closeQuietly(out);
        }
    }


}
