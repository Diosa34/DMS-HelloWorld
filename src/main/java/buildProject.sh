#!/bin/bash
rm -r lab5_prog/build/
rm -r ObjectConverter/build/
cd lab5_prog
git pull
./gradlew jar
cd ../
cd ObjectConverter
git pull
./gradlew jar
cd ..
chmod 777 lab5_prog/build/libs/lab5_prog.jar
cp ObjectConverter/build/libs/ObjectConverter.jar lab5_prog/build/libs/
chmod 777 lab5_prog/build/libs/ObjectConverter.jar
cd lab5_prog/build/libs
java -jar lab5_prog.jar "$1"
