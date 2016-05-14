#!/bin/bash

export CLASSPATH=$(hadoop classpath)
export HADOOP_CLASSPATH=$CLASSPATH

rm -rf /user/user01/project/OUT2
hadoop jar DataCombiner.jar DataCombinerDriver -files /user/user01/project/DATA/movies_titles.txt /user/user01/project/OUT/part-r-00000 /user/user01/project/OUT2

