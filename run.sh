#!/bin/bash
git pull
chmod 777 gradlew
./gradlew build
chmod 777 lab5_prog/build/libs/lab5.jar
java -jar lab5_prog/build/libs/lab5.jar "$1"
