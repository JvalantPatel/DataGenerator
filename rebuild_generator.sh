#!/bin/bash

export CLASSPATH=$(hadoop classpath)
export HADOOP_CLASSPATH=$CLASSPATH

javac -d classes DataGeneratorMapper.java
jar -cvf DataGenerator.jar -C classes/ .
javac -classpath $CLASSPATH:DataGenerator.jar -d classes DataGeneratorDriver.java
jar -uvf DataGenerator.jar -C classes/ .
