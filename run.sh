#!/bin/bash
git pull
chmod 777 gradlew
./gradlew build
chmod 777 build/libs/lab5.jar
java -jar build/libs/lab5.jar "$1"
