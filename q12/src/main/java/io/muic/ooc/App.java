package io.muic.ooc;

import org.apache.commons.cli.*;

import java.io.File;

public class App 
{
    public static void main( String[] args )
    {
        final String PATH= "/Users/Hamuel/Documents/CS-stuff/ooc/hw1/q12/docs";
        Walker walker = new Walker(new File(PATH));
        Options options = new Options();
        options.addOption("h", "help", false, "Display the usage information");
        options.addOption("a", "total-num-files", false, "The total number of files");
        options.addOption("b", "total-num-dirs", false, "Total number of directory");
        options.addOption("c", "total-unique-exts", false, "Total number of unique file extensions");
        options.addOption("d", "list-exts", false, "List all unique file extensions");
        options.addOption(Option.builder().longOpt("num-exts")
                                       .desc("List total number of file for the specified extension EXT")
                                       .hasArg(true)
                                       .argName("EXT")
                                       .build());
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try{
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h")){
                formatter.printHelp( "dirwalker", options );
            }
            if (line.hasOption("a")){
                System.out.printf("Total number of files: %d\n", walker.getTotalNumFiles());
            }
            if (line.hasOption("b")){
                System.out.printf("Total number of directory: %d\n", walker.getTotalNumDirectory());
            }
            if (line.hasOption("c")){
                System.out.printf("Total number of unique file extensions: %d\n", walker.getTotalUniqueExt());
            }
            if (line.hasOption("d")){
                System.out.println("List of all Unique File Extensions: ");
                for (String elt: walker.getUniqueExt()){
                    System.out.printf("%s ", elt);
                }
            }
            if (line.hasOption("num-exts")){
                System.out.printf("Total number of files with extension %s: %d \n",
                        line.getOptionValue("num-exts"),
                        walker.getTotalNumFileNumExt(line.getOptionValue("num-exts")));
            }
        }catch (ParseException exp){
            System.err.println( "Parsing failed. -h for more info Reason: " + exp.getMessage() );
        }

    }
}
