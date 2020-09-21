#include <stdio.h>
#include <stdlib.h>
#include "parser.h"
#include "visitor.h"

#define err(...) fprintf(stderr, __VA_ARGS__) 

int main (int argc, char **argv) {
	if (argc < 2) {
	   	err("error: input file missing\n");
		exit(-1);
	}
	AST *root;

	init_memory();
	root = parse_file(argv[1]);
	if (!root) exit(-1);
	visit_file(root);
	return 0;
}

