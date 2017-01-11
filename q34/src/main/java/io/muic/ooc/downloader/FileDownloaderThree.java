package io.muic.ooc.downloader;


import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class FileDownloaderThree {
    public FileDownloaderThree(String url) throws Exception{
        URL myURL = new URL(url);
        InputStream in = myURL.openStream();
        byte[] data = IOUtils.toByteArray(in);
        IOUtils.closeQuietly(in);
        FileOutputStream out = new FileOutputStream(new File(myURL.getHost()));
        IOUtils.write(data, out);
        IOUtils.closeQuietly(out);
    }
}
