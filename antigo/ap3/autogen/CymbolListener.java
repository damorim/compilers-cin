// Generated from autogen/Cymbol.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CymbolParser}.
 */
public interface CymbolListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CymbolParser#fiile}.
	 * @param ctx the parse tree
	 */
	void enterFiile(CymbolParser.FiileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#fiile}.
	 * @param ctx the parse tree
	 */
	void exitFiile(CymbolParser.FiileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(CymbolParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(CymbolParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FormTypeInt}
	 * labeled alternative in {@link CymbolParser#tyype}.
	 * @param ctx the parse tree
	 */
	void enterFormTypeInt(CymbolParser.FormTypeIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FormTypeInt}
	 * labeled alternative in {@link CymbolParser#tyype}.
	 * @param ctx the parse tree
	 */
	void exitFormTypeInt(CymbolParser.FormTypeIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FormTypeVoid}
	 * labeled alternative in {@link CymbolParser#tyype}.
	 * @param ctx the parse tree
	 */
	void enterFormTypeVoid(CymbolParser.FormTypeVoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FormTypeVoid}
	 * labeled alternative in {@link CymbolParser#tyype}.
	 * @param ctx the parse tree
	 */
	void exitFormTypeVoid(CymbolParser.FormTypeVoidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(CymbolParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(CymbolParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#paramTypeList}.
	 * @param ctx the parse tree
	 */
	void enterParamTypeList(CymbolParser.ParamTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#paramTypeList}.
	 * @param ctx the parse tree
	 */
	void exitParamTypeList(CymbolParser.ParamTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#paramType}.
	 * @param ctx the parse tree
	 */
	void enterParamType(CymbolParser.ParamTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#paramType}.
	 * @param ctx the parse tree
	 */
	void exitParamType(CymbolParser.ParamTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CymbolParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CymbolParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(CymbolParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#assignStat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(CymbolParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(CymbolParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(CymbolParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#ifElseStat}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStat(CymbolParser.IfElseStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#ifElseStat}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStat(CymbolParser.IfElseStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#ifElseExprStat}.
	 * @param ctx the parse tree
	 */
	void enterIfElseExprStat(CymbolParser.IfElseExprStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#ifElseExprStat}.
	 * @param ctx the parse tree
	 */
	void exitIfElseExprStat(CymbolParser.IfElseExprStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#exprStat}.
	 * @param ctx the parse tree
	 */
	void enterExprStat(CymbolParser.ExprStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#exprStat}.
	 * @param ctx the parse tree
	 */
	void exitExprStat(CymbolParser.ExprStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(CymbolParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(CymbolParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(CymbolParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(CymbolParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#elseStat}.
	 * @param ctx the parse tree
	 */
	void enterElseStat(CymbolParser.ElseStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#elseStat}.
	 * @param ctx the parse tree
	 */
	void exitElseStat(CymbolParser.ElseStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CymbolParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CymbolParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CymbolParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CymbolParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SignedExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSignedExpr(CymbolParser.SignedExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SignedExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSignedExpr(CymbolParser.SignedExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpr(CymbolParser.FunctionCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpr(CymbolParser.FunctionCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(CymbolParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(CymbolParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComparisonExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpr(CymbolParser.ComparisonExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComparisonExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpr(CymbolParser.ComparisonExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(CymbolParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(CymbolParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(CymbolParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(CymbolParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(CymbolParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(CymbolParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(CymbolParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(CymbolParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(CymbolParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(CymbolParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarIdExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarIdExpr(CymbolParser.VarIdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarIdExpr}
	 * labeled alternative in {@link CymbolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarIdExpr(CymbolParser.VarIdExprContext ctx);
}