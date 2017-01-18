# Homework One for OOC

Note: all project is a "maven project" you can notice that from the pom.xml
instruction to run for each different question is different

* Question 1 is in the q1 folder
  * You can run from the main class (App.java) using intellij or any editor can that compile maven project
  * I did not put a shade plugin for this so the jar file did not include the dependencies
  * If you put doc in a different path change the PATH string to change the path

* Question 2 is in the q2 folder
  * To run you can do `java -jar /target/q2-1.0-SNAPSHOT.jar -a -b -f=path_to_folder`
  * this maven project have a shade plugin therefore you can run from jar
  * the command line specs command can be view from the homework or just run it without argument it will display how to use it

* Question 3 is in the q3 folder
  * You can run from the main class (App.java) there is no shade plugin
  * it will download three files which is the same with the name of each downloader attach in the suffix
  * each method return true if it downloaded successfully and false if it fails

* Question 4 is in the q4 folder
  * you can run using the exec.sh it will save the doc folder in the parent of the root directory
  * modify the path by changing the constructor parameter in the CrawlerMain class
  * This question have the shade plugin so you can run directly from the jar file
  * There is some stuff that cannot be downloaded properly due to some weird path and how some path does not specify the index.html that is the only bug left
  * Any errors will be printed out the progress WILL NOT be printed because it is extraneous prints
  * It is pretty messy and have lots of weird bug due to the imperfect nature of html and http very hard to debug
