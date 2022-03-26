#!/bin/bash
rm -r lab5_prog/build/
cd lab5_prog
git pull
./gradlew jar
cd ..
chmod 777 lab5_prog/build/libs/lab5_prog.jar
cd lab5_prog/build/libs
java -jar lab5_prog.jar "$1"
