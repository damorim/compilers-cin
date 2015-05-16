#include <iostream>
#include <fstream>
#include <map>
#include <string>
#include <sstream>
#include <stdlib.h>
#include "pin.H"

using namespace std;

fstream outfile;
fstream infile;

enum var_type {
  T_INT,
  T_WIDE,
  T_BOOL,
  T_CHARP,
  T_INTP,
  T_ENUM,
  T_LONG,
  T_UNK
};

struct var_info {
  string name;
  var_type type;
};

var_type toEnum(string type) {
  if (type == "int") 
	return T_INT;
  else if (type == "int*") 
	return T_INTP;
  else if (type == "char*") 
	return T_CHARP;
  else if (type == "HOST_WIDE_INT")
	return T_WIDE;
  else if (type == "bool")
	return T_BOOL;
  else if (type == "enum")
	return T_ENUM;
  else if (type == "long")
	return T_LONG;
  else 
	return T_UNK;
}

map<long,var_info> pointerMap;

KNOB<string> KnobInputFile(KNOB_MODE_WRITEONCE, "pintool",	"i", "addresses.in", "name of the file with addresses to be watched");
KNOB<string> KnobOutputFile(KNOB_MODE_WRITEONCE, "pintool",	"o", "values.out", "name of the file to write the results");

// Print a memory read record
VOID RecordMemRead(VOID * ip, VOID * addr)
{
  map<long,var_info>::iterator it = pointerMap.find((long) addr);
  if (it != pointerMap.end()) {
	ostringstream svalue;

	switch (it->second.type) {
	case T_INT:
	case T_BOOL:
	case T_ENUM:
	  {
	  int value;
	  PIN_SafeCopy(&value, addr, sizeof(int));
	  svalue << value;
	  }
	  break;
	case T_WIDE:
	case T_LONG:
	  {
	  long value;
	  PIN_SafeCopy(&value, addr, sizeof(long));
	  svalue << value;
	  }
	  break;
	case T_CHARP:
	  {
	  char* pointer;
	  PIN_SafeCopy(&pointer, addr, sizeof(char*));
	  char value;
	  PIN_SafeCopy(&value, pointer, sizeof(char));
	  svalue << value;
	  }
	  break;
	case T_INTP:
	  {
	  int* pointer;
	  PIN_SafeCopy(&pointer, addr, sizeof(int*));
	  int value;
	  PIN_SafeCopy(&value, pointer, sizeof(int));
	  svalue  << value;
	  }
	  break;
	case T_UNK:
	  svalue << "ERROR! T_UNK types should never appear at this point!";
	  break;
	}
	outfile << "memread: name=" << it->second.name << ", val=" << svalue.str() << "\n";
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
	  int i = 0;
	  while (getline(infile, address, ' ')) {
		string name;
		string stype;
		getline(infile, name, ' ');
		getline(infile, stype);


		var_type type = toEnum(stype);

		if (type == T_UNK) {
		  PIN_ERROR("Unknown type : " + stype);
		  return -1;
		} else {
		  //pointers are represented in hexadecimal
		  long pointerAddr = strtol(address.c_str(),NULL,16);

		  var_info vinfo = {name,type};
		  pointerMap[pointerAddr] = vinfo;
		  i++;
		}
	  }

	  for (map<long,var_info>::iterator it=pointerMap.begin(); it!=pointerMap.end(); ++it)
		outfile << it->first << " => " << it->second.name << " " << it-> second.type << '\n';

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
