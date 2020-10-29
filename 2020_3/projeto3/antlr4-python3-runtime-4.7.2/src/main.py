import sys
from antlr4 import *
from autogen.GrammarLexer import GrammarLexer
from autogen.GrammarParser import GrammarParser
from autogen.GrammarCheckerVisitor import GrammarCheckerVisitor

def main (argv):
	inpt = 0 if len(argv) <= 1 else argv[1]
	input_stream = FileStream(inpt)
	lexer = GrammarLexer(input_stream)
	stream = CommonTokenStream(lexer)
	parser = GrammarParser(stream)
	tree = parser.fiile()
	visitor = GrammarCheckerVisitor()
	visitor = tree.accept(visitor)

if __name__ == '__main__':
	main(sys.argv)
