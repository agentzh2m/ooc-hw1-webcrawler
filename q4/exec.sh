#!/usr/bin/env bash

rm -rf ../docs
rm ../runlog
mvn package
java -jar target/q4-1.0-SNAPSHOT.jar > ../runlog