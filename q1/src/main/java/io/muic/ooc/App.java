package io.muic.ooc;


import java.io.File;

public class App
{
    public static void main( String[] args )
    {
        final String PATH= "/Users/Hamuel/Documents/CS-stuff/ooc/hw1/q12/docs";
        // Example usage of this program
        Walker walker = new Walker(new File(PATH));
        System.out.println("Total number of files "+walker.getTotalNumFiles());
        System.out.println("Total number of directories " + walker.getTotalNumDirectory());
        System.out.println("Total number of unique file extension " + walker.getTotalUniqueExt());
        System.out.println("List of unique file extension \n " + walker.getUniqueExt());
        System.out.println("Total number of html files " + walker.getTotalNumFileNumExt("html"));
    }
}
