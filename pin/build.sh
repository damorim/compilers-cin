#!/bin/bash

## I have no idea how to add a rule in the makefile to compile the
## tool directly. In the meantime, this script will do the job.

#######################
## compile the pin tool
#######################

# PIN uses the name of the folder + the extension of the file as a
# makefile rule.  In this specific case, we want to compile a shared
# object (.so, our pintool) for the 64-bit (obj-intel64) instruction
# set.
machine=`uname -m`
dirname="obj-intel64/"
if [ $machine == "i686" ]
then
    ## this is for my old x61s
    dirname="obj-ia32"
fi
# compile our pintool see this link for a full explanation:
# http://www.cs.swarthmore.edu/~bylvisa1/cs97/f13/pin_tutorial.html
mkdir -p ${dirname}
make ${dirname}/memorytrace.so

#######################
## compile subject
#######################
gcc -g test.c -o test.out