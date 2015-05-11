#!/bin/bash

OUT=${HOME}/projects/compilers-cin/pin/out
mkdir -p ${OUT}

## I have no idea how to add an rule in the makefile to compile
## the tool directly. In the meantime, this script will do the job.

### that is fine -Marcelo

# compile our pintool
# see this link for details: http://www.cs.swarthmore.edu/~bylvisa1/cs97/f13/pin_tutorial.html
make -e ${OUT}/memorytrace.so

# compile the example
gcc -g test.c -o test.out

