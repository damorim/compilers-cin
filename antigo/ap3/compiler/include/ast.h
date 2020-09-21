#ifndef AST_HEADER
#define AST_HEADER

#include <stdbool.h>
#include "lexer.h"

typedef struct List List;
typedef union AST AST;
typedef struct AbbrevToken AbbrevToken;

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

struct List {
    AST *ast;
    List *next;
};

union AST {

    union {
        int type;

        struct {
            int decl_type; // useless space to align union fields
            int type;
            AST *id;
            AST *param_decl_list;
            AST *block;
			AbbrevToken *token;
        } function;

        struct {
            int decl_type; // useless space to align union fields
            int type;
            AST *id;
            AST *expr;
			AbbrevToken *token;
			AbbrevToken *assign_token;
        } variable;

    } decl;


    struct {
        int num_items;
        List *first;
        List *last;
    } list; // expr_list param_decl_list stat_block/block file var_decl_list

    struct {
        int type;

        union {
			List var_decl_list; // TODO: implement this

            struct {
                int type;
                AST *id;
                AST *expr;
				AbbrevToken *token;
            } var_decl; //use decl.variable

            struct {
                AST *id;
                AST *expr;
				AbbrevToken *token;
            } assign;

            struct {
                AST *expr;
				AbbrevToken *token;
            } ret;

            struct {
                AST *expr; // TODO: remove this indirection
            } expr;
        };

    } stat; // base type


    struct {
        int type;

        union {
            struct {
				char operation;
                AST *left_expr;
                AST *right_expr;
				AbbrevToken *token;
            } binary_expr; // '+', '-', '*', '/' '%'

            struct {
                AST *expr;
				AbbrevToken *token;
            } unary_minus;

            struct {
				int type;
				union {
					double float_value;
					long int_value;
				};
				AbbrevToken *token;
            } literal;

            struct {
                AST *id;
				AbbrevToken *token;
            } id;

            struct {
                AST *id;
                AST *expr_list;
				AbbrevToken *token;
            } function_call;
        };

    } expr; // base type

    struct {
        char *string;
        int hash;
        int length;

		AST *declaration;
		int decl_type; // VARIABLE_DECLARATION or FUNCTION_DECLARATION

		int type; // TYPE_INT, TYPE_FLOAT, TYPE_VOID, or TYPE_STRING
		union {
			long int_value;
			double float_value;
		};
    } id;

};

struct AbbrevToken {
	char *filename, *line_start;
	int line, column;
};
	

void init_memory ();
AST *new_literal (int type, void *value, Token *token);
AST *new_identifier (Token *token);
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
AST *new_function_decl (int type, AST *id, AST *param_decl_list, AST *block, Token *token);
AST *new_file (AST *decl);
AST *add_to_file (AST *file, AST *decl);

#endif // AST_HEADER 
