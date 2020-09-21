from antlr4 import *
from autogen.CymbolParser import CymbolParser 
from autogen.CymbolVisitor import CymbolVisitor

class Type:
	VOID = "void"
	INT = "int"


class CymbolCheckerVisitor(CymbolVisitor):
	id_values = {}

	def visitIntExpr(self, ctx:CymbolParser.IntExprContext):
		print("visting "+Type.INT)
		return Type.INT



	def visitVarDecl(self, ctx:CymbolParser.VarDeclContext):
		var_name = ctx.ID().getText()
		tyype = ctx.tyype().getText()
		print("tyype = " + tyype)
		
		if (tyype == Type.VOID):
			result = Type.VOID
			print("Mensagem de erro 1...")
			exit(1)
		else:
			if ctx.expr() != None:
				init = ctx.expr().accept(self)
				print("init = " + init)
				if init != tyype:
					print("Mensagem de erro 2...")
					exit(2)

			result = tyype
			self.id_values[var_name] = tyype

		print("saved variable " + var_name + " of type " + tyype)
		return result



	def visitAddSubExpr(self, ctx:CymbolParser.AddSubExprContext):
		left = ctx.expr()[0].accept(self)
		right = ctx.expr()[1].accept(self)

		if left == Type.INT and right == Type.INT:
			result = Type.INT
		else:
			reult = Type.VOID
			print("Mensagem de erro 3...")
			exit()
		
		print("addition or subtraction of " + left + " " + right + " that results in a " + result)
		return result



	def aggregateResult(self, aggregate:Type, next_result:Type):
		return next_result if next_result != None else aggregate
