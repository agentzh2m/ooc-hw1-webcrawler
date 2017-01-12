package io.muic.ooc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class App 
{
    public static void main( String[] args )
    {
        final String SRC_URL = "http://10.27.8.20:9327/primes11.txt";
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
        String[] urlTok = myURL.getPath().split("/");
        InputStream in = null;
        FileOutputStream out = null;
        try{
            in = myURL.openStream();
            byte[] data = IOUtils.toByteArray(in);
            IOUtils.closeQuietly(in);
            out = new FileOutputStream(new File(urlTok[urlTok.length-1]+"_fdThree"));
            IOUtils.write(data, out);
            IOUtils.closeQuietly(out);
            return true;
        }catch (IOException ix){
            ix.printStackTrace();
            return false;
        }finally {
            if (in != null) IOUtils.closeQuietly(in);
            if (out != null) IOUtils.closeQuietly(out);
        }
    }



}
