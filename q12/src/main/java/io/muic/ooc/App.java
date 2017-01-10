package io.muic.ooc;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final String PATH= "/Users/Hamuel/Documents/CS-stuff/ooc/hw1/q12/docs";
        Walker w1 = new Walker(new File(PATH));
        System.out.println(w1.getTotalNumFiles());
        System.out.println(w1.getTotalNumDirectory());
        System.out.println(w1.getTotalUniqueExt());
        System.out.println(w1.getUniqueExt());
        System.out.println(w1.getTotalNumFileNumExt("java"));
    }
}
