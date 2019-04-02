import sys
from antlr4 import *
from autogen.TestLexer import TestLexer
from autogen.TestParser import TestParser
from autogen.TestListener import TestListener

def main (argv):
	inpt = 0 if len(argv) <= 1 else argv[1]
	input_stream = FileStream(inpt)
	lexer = TestLexer(input_stream)
	stream = CommonTokenStream(lexer)
	parser = TestParser(stream)
	tree = parser.stat()
	listener = TestListener()
	walker = ParseTreeWalker()
	walker.walk(listener, tree)

if __name__ == '__main__':
	main(sys.argv)
