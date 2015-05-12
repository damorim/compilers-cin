1. Download your "Compiler Kit" from the URL below for your OS.

 https://software.intel.com/en-us/articles/pintool-downloads

2. Decompress the pin distribution file 

3. Set the PIN_ROOT variable in "Makefile" and "run.sh" to the directory where pin was decompressed

4. Run "build.sh". 

5. Check the addresses of the global variables of "test.out" with the "nm" command.

6. Update "address.in" with the information from the previous step. Please pay
   attention to the file format.

7. Run "run.sh".

8. You should find in "trace.out" the name and value of the variables that
   were read during the execution of the program.

