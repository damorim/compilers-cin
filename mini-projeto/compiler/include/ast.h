#ifndef AST_HEADER
#define AST_HEADER

#include "lexer.h"

typedef union AST AST;
typedef struct ListNode ListNode;
typedef struct AbbrevToken AbbrevToken;

typedef struct Identifier Identifier;
typedef struct List List;

typedef union Declaration   Declaration;
typedef struct FunctionDecl FunctionDecl;
typedef struct VarDecl      VarDecl;

typedef struct Expression       Expreession;
typedef struct BinaryExpr       BinaryExpr;
typedef struct UnaryMinusExpr   UnaryMinusExpr;
typedef struct LiteralExpr      LiteralExpr;
typedef struct IdExpr           IdExpr;
typedef struct FunctionCallExpr FunctionCallExpr;

typedef struct Statement  Statement;
typedef struct AssignStat AssignStat;
typedef struct ReturnStat ReturnStat;
typedef struct ExprStat   ExprStat;


enum AST_Types {
	DECLARATION,
	VARIABLE_DECLARATION,
	VARIABLE_DECLARATION_LIST,
	FUNCTION_DECLARATION,

	STATEMENT,
	ASSIGN_STATEMENT,
	RETURN_STATEMENT,
	EXPRESSION_STATEMENT,

	EXPRESSION,
	BINARY_EXPRESSION,
	UNARY_MINUS_EXPRESSION,
	LITERAL_EXPRESSION,
	IDENTIFIER_EXPRESSION,
	FUNCTION_CALL_EXPRESSION,
};

struct ListNode {
	AST *ast;
	ListNode *next;
};

union AST {

	union Declaration {
		int type;

		struct FunctionDecl {
			int decl_type; // useless space to align union fields
			int type;
			AST *id;
			AST *param_decl_list;
			AST *stat_block;
			AbbrevToken *token;
		} function;

		struct VarDecl {
			int decl_type; // useless space to align union fields
			int type;
			AST *id;
			AST *expr;
			AbbrevToken *token;
			AbbrevToken *assign_token;
		} variable;

	} decl;


	struct List {
		int num_items;
		ListNode *first, *last;
	} list; // expr_list param_decl_list stat_block file var_decl_list


	struct Expression {
		int type;

		union {
			struct BinaryExpr {
				char operation;
				AST *left_expr;
				AST *right_expr;
				AbbrevToken *token;
			} binary_expr; // '+', '-', '*', '/' '%'

			struct UnaryMinusExpr {
				AST *expr;
				AbbrevToken *token;
			} unary_minus;

			struct LiteralExpr {
				int type;
				union {
					double float_value;
					long int_value;
				};
				AbbrevToken *token;
			} literal;

			struct IdExpr {
				AST *id;
				AbbrevToken *token;
			} id;

			struct FunctionCallExpr {
				AST *id;
				AST *expr_list;
				AbbrevToken *token;
			} function_call;
		};

	} expr; // base type


	struct Statement {
		int type;

		union {
			List var_decl_list; // TODO: implement this

			//struct VarDecl var_decl; //use decl.variable

			struct AssignStat {
				AST *id;
				AST *expr;
				AbbrevToken *token;
			} assign;

			struct ReturnStat {
				AST *expr;
				AbbrevToken *token;
			} ret;

			struct ExprStat {
				AST *expr; // TODO: remove this indirection
			} expr;
		};

	} stat; // base type


	struct Identifier {
		char *string;
		int hash;
		int length;

		AST *declaration;
		int decl_type; // VARIABLE_DECLARATION or FUNCTION_DECLARATION

		short type; // enum TypeType (TYPE_INT, TYPE_FLOAT, TYPE_VOID, or TYPE_STRING)
		short flags; // enum IdFlags (IS_GLOBAL, IS_CONSTANT)
		union {
			long int_value;
			double float_value;
			long ssa_register;
		};
	} id;

};

enum IdFlags {
	IS_GLOBAL      = 0x01,
	IS_CONSTANT    = 0x02,
	IS_INITIALIZED = 0x04, // unused
};

struct AbbrevToken {
	char *filename, *line_start;
	int line, column;
};
	

void init_memory ();
AST *new_literal (int type, void *value, Token *token);
AST *new_identifier (Token *token, int at_global_scope);
AST *new_id_expr (AST *id, Token *token);
AST *new_binary_expr (AST *leftExpr, char op, AST *right_expr, Token *token);
AST *new_unary_minus (AST *expr, Token *token);
AST *new_function_call (AST *id, AST *expr_list, Token *token);
AST *new_list (AST *expr);
AST *add_to_list (AST *expr_list, AST *expr);
AST *new_var_decl_list (AST *ast);
AST *new_expr_stat (AST *expr);
AST *new_return_stat (AST *expr, Token *token);
AST *new_assign_stat (AST *id, AST *expr, Token *token);
AST *new_var_decl (int type, AST *id, AST *expr, Token *token, Token *assign_token);
AST *new_param_decl (int type, AST *id, Token *token);
AST *new_function_decl (int type, AST *id, AST *param_decl_list, AST *stat_block, Token *token);
AST *new_file (AST *decl);
AST *add_to_file (AST *file, AST *decl);
char *strlinea (const AbbrevToken *restrict token);

#endif // AST_HEADER
