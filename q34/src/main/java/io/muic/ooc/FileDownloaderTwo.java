package io.muic.ooc;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FileDownloaderTwo {
    public FileDownloaderTwo(String url) throws Exception{
        URL myURL = new URL(url);
        File file = new File(myURL.getHost());
        FileUtils.copyURLToFile(myURL, file);
    }
}
