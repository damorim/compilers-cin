import sys
from antlr4 import *
from autogen.CymbolLexer import CymbolLexer
from autogen.CymbolParser import CymbolParser
from autogen.CymbolCheckerVisitor import CymbolCheckerVisitor

def main (argv):
	inpt = 0 if len(argv) <= 1 else argv[1]
	input_stream = FileStream(inpt)
	lexer = CymbolLexer(input_stream)
	stream = CommonTokenStream(lexer)
	parser = CymbolParser(stream)
	tree = parser.fiile()
	visitor = CymbolCheckerVisitor()
	visitor = tree.accept(visitor)

if __name__ == '__main__':
	main(sys.argv)
