from antlr4 import *
from autogen.TestParser import TestParser 
from autogen.TestVisitor import TestVisitor

class TestExtendVisitor(TestVisitor):

	# Visit a parse tree produced by TestParser#Expressions.
	def visitExpressions(self, ctx:TestParser.ExpressionsContext):
		return self.visitChildren(ctx)

	# Visit a parse tree produced by TestParser#Assign.
	def visitAssign(self, ctx:TestParser.AssignContext):
		print("ZA WARUDO",ctx.ID())
		return self.visitChildren(ctx)

	# Visit a parse tree produced by TestParser#AddSub.
	def visitAddSub(self, ctx:TestParser.AddSubContext):
		left = self.visit(ctx.expr()[0])
		right= self.visit(ctx.expr()[1])
		print("TOKI O TOMARE  ",left,"   ", right)
		print(ctx.op.text)
		if (ctx.op.text == '+'):
			print("KONO DIO DA ", str(int(left) + int(right)))
		else:
			print("KONO DIO DA ", str(int(left) - int(right)))
		return self.visitChildren(ctx)

	# Visit a parse tree produced by TestParser#Int.
	def visitInt(self, ctx:TestParser.IntContext):
		return int(ctx.INT().getText())
	
	# Visit a parse tree produced by TestParser#Identifier.
	def visitIdentifier(self, ctx:TestParser.IdentifierContext):
		return self.visitChildren(ctx)

	# Visit a parse tree produced by TestParser#Paren.
	def visitParen(self, ctx:TestParser.ParenContext):
		return self.visitChildren(ctx)

	# Visit a parse tree produced by TestParser#MultDiv.
	def visitMultDiv(self, ctx:TestParser.MultDivContext):
		return self.visitChildren(ctx) 
