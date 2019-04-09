from antlr4 import *
from autogen.CymbolParser import CymbolParser 
from autogen.CymbolVisitor import CymbolVisitor

class CymbolCheckerVisitor(CymbolVisitor):

	# Visit a parse tree produced by CymbolParser#Expressions.
	def visitExpressions(self, ctx:CymbolParser.ExpressionsContext):
		return self.visitChildren(ctx)

	# Visit a parse tree produced by CymbolParser#Assign.
	def visitAssign(self, ctx:CymbolParser.AssignContext):
		print("ZA WARUDO",ctx.ID())
		return self.visitChildren(ctx)

	# Visit a parse tree produced by CymbolParser#AddSub.
	def visitAddSub(self, ctx:CymbolParser.AddSubContext):
		left = self.visit(ctx.expr()[0])
		right= self.visit(ctx.expr()[1])
		print("TOKI O TOMARE  ",left,"   ", right)
		print(ctx.op.text)
		if (ctx.op.text == '+'):
			print("KONO DIO DA ", str(int(left) + int(right)))
		else:
			print("KONO DIO DA ", str(int(left) - int(right)))
		return self.visitChildren(ctx)

	# Visit a parse tree produced by CymbolParser#Int.
	def visitInt(self, ctx:CymbolParser.IntContext):
		return int(ctx.INT().getText())
	
	# Visit a parse tree produced by CymbolParser#Identifier.
	def visitIdentifier(self, ctx:CymbolParser.IdentifierContext):
		return self.visitChildren(ctx)

	# Visit a parse tree produced by CymbolParser#Paren.
	def visitParen(self, ctx:CymbolParser.ParenContext):
		return self.visitChildren(ctx)

	# Visit a parse tree produced by CymbolParser#MultDiv.
	def visitMultDiv(self, ctx:CymbolParser.MultDivContext):
		return self.visitChildren(ctx) 
