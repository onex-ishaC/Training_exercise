#!/bin/bash

echo "====== Cleaning Project ======"
mvn clean

echo "====== Compiling Code ======"
mvn compile

echo "====== Running Tests ======"
mvn test

echo "====== Running Application ======"
mvn exec:java

