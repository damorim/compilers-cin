// Generated from autogen/C.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CParser}.
 */
public interface CListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(CParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(CParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(CParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#function_definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(CParser.Function_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(CParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(CParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(CParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(CParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void enterElse_statement(CParser.Else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void exitElse_statement(CParser.Else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(CParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(CParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#for_initializer}.
	 * @param ctx the parse tree
	 */
	void enterFor_initializer(CParser.For_initializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#for_initializer}.
	 * @param ctx the parse tree
	 */
	void exitFor_initializer(CParser.For_initializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#for_condition}.
	 * @param ctx the parse tree
	 */
	void enterFor_condition(CParser.For_conditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#for_condition}.
	 * @param ctx the parse tree
	 */
	void exitFor_condition(CParser.For_conditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#for_step}.
	 * @param ctx the parse tree
	 */
	void enterFor_step(CParser.For_stepContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#for_step}.
	 * @param ctx the parse tree
	 */
	void exitFor_step(CParser.For_stepContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#variable_definition}.
	 * @param ctx the parse tree
	 */
	void enterVariable_definition(CParser.Variable_definitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#variable_definition}.
	 * @param ctx the parse tree
	 */
	void exitVariable_definition(CParser.Variable_definitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void enterVariable_assignment(CParser.Variable_assignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#variable_assignment}.
	 * @param ctx the parse tree
	 */
	void exitVariable_assignment(CParser.Variable_assignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(CParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(CParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#array_literal}.
	 * @param ctx the parse tree
	 */
	void enterArray_literal(CParser.Array_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#array_literal}.
	 * @param ctx the parse tree
	 */
	void exitArray_literal(CParser.Array_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(CParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(CParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(CParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(CParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(CParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(CParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#floating}.
	 * @param ctx the parse tree
	 */
	void enterFloating(CParser.FloatingContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#floating}.
	 * @param ctx the parse tree
	 */
	void exitFloating(CParser.FloatingContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(CParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(CParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link CParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(CParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(CParser.IdentifierContext ctx);
}