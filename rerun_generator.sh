#!/bin/bash

BASEDIR=/user/user01/project

export CLASSPATH=$(hadoop classpath)
export HADOOP_CLASSPATH=$CLASSPATH

rm -rf $BASEDIR/OUT 
hadoop jar DataGenerator.jar DataGeneratorDriver $BASEDIR/DATA/training_set $BASEDIR/OUT
