// Generated from autogen/Grammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(GrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(GrammarParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(GrammarParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(GrammarParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(GrammarParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(GrammarParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#floater}.
	 * @param ctx the parse tree
	 */
	void enterFloater(GrammarParser.FloaterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#floater}.
	 * @param ctx the parse tree
	 */
	void exitFloater(GrammarParser.FloaterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#types}.
	 * @param ctx the parse tree
	 */
	void enterTypes(GrammarParser.TypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#types}.
	 * @param ctx the parse tree
	 */
	void exitTypes(GrammarParser.TypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(GrammarParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(GrammarParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(GrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(GrammarParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#argumentsType}.
	 * @param ctx the parse tree
	 */
	void enterArgumentsType(GrammarParser.ArgumentsTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#argumentsType}.
	 * @param ctx the parse tree
	 */
	void exitArgumentsType(GrammarParser.ArgumentsTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(GrammarParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(GrammarParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#assigment}.
	 * @param ctx the parse tree
	 */
	void enterAssigment(GrammarParser.AssigmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#assigment}.
	 * @param ctx the parse tree
	 */
	void exitAssigment(GrammarParser.AssigmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#returnExp}.
	 * @param ctx the parse tree
	 */
	void enterReturnExp(GrammarParser.ReturnExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#returnExp}.
	 * @param ctx the parse tree
	 */
	void exitReturnExp(GrammarParser.ReturnExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#functionDec}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDec(GrammarParser.FunctionDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#functionDec}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDec(GrammarParser.FunctionDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#statment}.
	 * @param ctx the parse tree
	 */
	void enterStatment(GrammarParser.StatmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#statment}.
	 * @param ctx the parse tree
	 */
	void exitStatment(GrammarParser.StatmentContext ctx);
}