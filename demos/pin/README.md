Step 1: setup PIN

* Download your "Compiler Kit" from the URL below for your OS.

  https://software.intel.com/en-us/articles/pintool-downloads

* Decompress the pin distribution file 
* Set the PIN_ROOT variable in "Makefile" and "run.sh" to the directory where pin was decompressed

Step 2: run the tool

* Compile the tool by running "build.sh". 
* Check the addresses of the global variables of "test.out" with the "nm" command. 
 * See the file 'gcc/gdb-dump-optstruct.txt' for an example of how to dump the memory layout of a struct. 
   The command 'pahole' may be helpful to find the size of each member, but it may fail with binaries compiled by recent compilers.

* Update "address.in" with the information from the previous step. Please pay attention to the file format.
* Run "run.sh".
* You should find in "trace.out" the name and value of the variables that were read during the execution of the program.


See the gcc/ folder for instructions on how to prepare a typical install of gcc for use with this tool. 

