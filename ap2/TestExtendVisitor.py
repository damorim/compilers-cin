from antlr4 import *
from autogen.TestParser import TestParser 
from autogen.TestVisitor import TestVisitor

class TestExtendVisitor(TestVisitor):
	def visitExpressions(self, ctx:TestParser.ExpressionsContext):
		return self.visitChildren(ctx)

	def visitAssign(self, ctx:TestParser.AssignContext):
		print("ZA WARUDO",ctx.ID())
		return self.visitChildren(ctx)

	# Visit a parse tree produced by TestParser#AddSub.
	def visitAddSub(self, ctx:TestParser.AddSubContext):
		left = self.visit(ctx.expr()[0])
		right= self.visit(ctx.expr()[1])
		print("TOKI O TOMARE  ",left,"   ", right)
		print("KONO DIO DA ", str(int(left)+int(right) ) )
		return self.visitChildren(ctx)

	def visitInt(self, ctx:TestParser.IntContext):
		return int(ctx.INT().getText())
