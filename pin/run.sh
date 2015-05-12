#!/bin/bash

## Performs the instrumentation 

# TODO share the value of this variable with the makefile
PIN_ROOT="/home/mateus/tools/pin-2.14-71313-gcc.4.4.7-linux/"

echo "Current PIN_ROOT:$PIN_ROOT"
${PIN_ROOT}/pin -t obj-intel64/memorytrace.so -i addresses.in -o trace.out -- ./test.out
