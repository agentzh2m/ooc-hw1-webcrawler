package io.muic.ooc;


public class App 
{
    public static void main( String[] args )
    {
        final String SRC_URL = "http://www.oracle.com/technetwork/java/javase/jdk8-naming-2157130.html";
        try {
            FileDownloaderOne dl1 = new FileDownloaderOne(SRC_URL);
            FileDownloaderTwo dl2 = new FileDownloaderTwo(SRC_URL);
            FileDownloaderThree dl3 = new FileDownloaderThree(SRC_URL);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( "Hello World!" );
    }
}
