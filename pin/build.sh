#!/bin/bash

## I have no idea how to add an rule in the makefile to compile
## the tool directly. In the meantime, this script will do the job.


# compile our pintool
# see this link for details: http://www.cs.swarthmore.edu/~bylvisa1/cs97/f13/pin_tutorial.html

mkdir -p obj-intel64/
make obj-intel64/memorytrace.so

# compile the example

gcc -g test.c -o test.out

