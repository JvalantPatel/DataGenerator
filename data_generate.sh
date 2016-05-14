#!/usr/bin/env bash
set -x
./rebuild_generator.sh
./rerun_generator.sh
LC_ALL=C sed 's/NULL/0/g' movie_titles.txt >> clean_movie_titles.txt
./rebuild_combiner.sh
./rerun_combiner.sh
set +x