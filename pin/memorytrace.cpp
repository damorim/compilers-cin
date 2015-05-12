#include <iostream>
#include <fstream>
#include <map>
#include <string>
#include <stdlib.h>
#include "pin.H"

using namespace std;

fstream outfile;
fstream infile;
map<long,string> pointerMap;

KNOB<string> KnobInputFile(KNOB_MODE_WRITEONCE, "pintool",	"i", "addresses.in", "name of the file with addresses to be watched");
KNOB<string> KnobOutputFile(KNOB_MODE_WRITEONCE, "pintool",	"o", "values.out", "name of the file to write the results");

// Print a memory read record
VOID RecordMemRead(VOID * ip, VOID * addr)
{
  map<long,string>::iterator it = pointerMap.find((long) addr);
  if (it != pointerMap.end()) {
	int value;
	//TODO handle non-integer values
	PIN_SafeCopy(&value, addr, sizeof(int));
	outfile << "memread: name=" << it->second << ", val=" << value << "\n";
  }
  
  //  fprintf(trace,"memread: pos=%p, val=%d\n", addr, value);
}

// Print a memory write record
VOID RecordMemWrite(VOID * ip, VOID * addr)
{
  //    fprintf(trace,"%p: W %p\n", ip, addr);
}

// Is called for every instruction and instruments reads and writes
VOID Instruction(INS ins, VOID *v)
{
    // Instruments memory accesses using a predicated call, i.e.
    // the instrumentation is called iff the instruction will actually be executed.
    //
    // On the IA-32 and Intel(R) 64 architectures conditional moves and REP 
    // prefixed instructions appear as predicated instructions in Pin.
    UINT32 memOperands = INS_MemoryOperandCount(ins);

    // Iterate over each memory operand of the instruction.
    for (UINT32 memOp = 0; memOp < memOperands; memOp++)
    {
        if (INS_MemoryOperandIsRead(ins, memOp))
        {
            INS_InsertPredicatedCall(
                ins, IPOINT_BEFORE, (AFUNPTR)RecordMemRead,
                IARG_INST_PTR,
                IARG_MEMORYOP_EA, memOp,
                IARG_END);
        }
        // Note that in some architectures a single memory operand can be 
        // both read and written (for instance incl (%eax) on IA-32)
        // In that case we instrument it once for read and once for write.
        // if (INS_MemoryOperandIsWritten(ins, memOp))
        // {
        //     INS_InsertPredicatedCall(
        //         ins, IPOINT_BEFORE, (AFUNPTR)RecordMemWrite,
        //         IARG_INST_PTR,
        //         IARG_MEMORYOP_EA, memOp,
        //         IARG_END);
        // }
    }
}

VOID Fini(INT32 code, VOID *v)
{
  outfile.close();
  infile.close();
}

/* ===================================================================== */
/* Print Help Message                                                    */
/* ===================================================================== */
   
INT32 Usage()
{
    PIN_ERROR( "This Pintool prints a trace of memory addresses\n" 
              + KNOB_BASE::StringKnobSummary() + "\n");
    return -1;
}

/* ===================================================================== */
/* Main                                                                  */
/* ===================================================================== */

int main(int argc, char *argv[])
{
    if (PIN_Init(argc, argv)) return Usage();

	infile.open(KnobInputFile.Value().c_str(),ios::in);
	outfile.open(KnobOutputFile.Value().c_str(),ios::out);

	if (infile.is_open() && outfile.is_open()) {
	  string address;
	  while (getline(infile, address, ' ')) {
		string name;
		getline(infile, name);

		//pointers are represented in hexadecimal
		long pointerAddr = strtol(address.c_str(),NULL,16);
		pointerMap[pointerAddr] = name;
	  }

	  for (map<long,string>::iterator it=pointerMap.begin(); it!=pointerMap.end(); ++it)
		outfile << it->first << " => " << it->second << '\n';

	} else {
	  PIN_ERROR( "Error opening input/output files!\n" 
              + KNOB_BASE::StringKnobSummary() + "\n");
	  return -1;
	}

    INS_AddInstrumentFunction(Instruction, 0);
    PIN_AddFiniFunction(Fini, 0);

    // Never returns
    PIN_StartProgram();
    
    return 0;
}
