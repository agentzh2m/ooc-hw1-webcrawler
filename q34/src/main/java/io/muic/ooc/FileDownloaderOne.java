package io.muic.ooc;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloaderOne {
    public FileDownloaderOne(String url) throws Exception{
        URL myURL = new URL(url);
        URLConnection yc = myURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        FileOutputStream out = new FileOutputStream(myURL.getHost());
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            out.write(inputLine.getBytes());
            out.write("\n".getBytes());
        }
        in.close();
        out.flush();
        out.close();
    }
}
