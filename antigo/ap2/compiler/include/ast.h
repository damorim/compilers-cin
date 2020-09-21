#ifndef AST_HEADER
#define AST_HEADER

#include <stdbool.h>
#include "lexer.h"

typedef struct List List;
typedef union AST AST;

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
        char type;

        struct {
            char decl_type;
            int type;
            AST *id;
            AST *param_type_list;
            AST *block;
        } function; // 'f'

        struct {
            char decl_type; // useless space to match union fields
            int type;
            AST *id;
            AST *expr;
        } variable; // 'v'

    } decl; // base type


    struct {
        int num_items;
        List *first;
        List *last;
    } list; // expr_list param_type_list stat_block/block file var_decl_list

    struct {
        int type;
        AST *id;
    } param_type;


    struct {
        char type;

        union {
			List var_decl_list;

            struct {
                int type;
                AST *id;
                AST *expr;
            } var_decl; // 'd'

            struct {
                AST *id;
                AST *expr;
            } assign; // '='

            struct {
                AST *expr;
            } ret; // 'r'

            struct {
                AST *expr;
            } expr; // 'e'
        };

    } stat; // base type


    struct {
        char type;

        union {
            struct {
				char operation;
                AST *left_expr;
                AST *right_expr;
            } binary_expr; // '+', '-', '*', '/'

            struct {
                AST *expr;
            } unary_minus; // 'm'

            struct {
				int type;
				union {
					double float_value;
					long int_value;
				};
            } literal; // 'n'

            struct {
                AST *id;
            } id; // 'i'

            struct {
                AST *id;
                AST *expr_list;
            } function_call; // 'c'
        };

    } expr; // base type

    struct {
        char *string;
        int hash;
        int length;
		Token *token;

		int type;
		union {
			long int_value;
			double float_value;
		};
        // only filled at backend time
		/*
        int last_scope; // function that used it
        union {
            int ir_number;
            int cte_value;
        };
        bool is_global;
        bool is_global_in_this_scope;
        bool is_cte;
		*/
    } id;

};

extern AST *root;

void init_memory ();
AST *new_literal (int type, void *value);
AST *new_identifier (Token *token);
AST *new_id_expr (AST *id);
AST *new_binary_expr (AST *leftExpr, char op, AST *right_expr);
AST *new_unary_minus (AST *expr);
AST *new_function_call (AST *id, AST *expr_list);
AST *new_list (AST *expr);
AST *add_to_list (AST *expr_list, AST *expr);
AST *new_var_decl_list (AST *ast);
AST *new_expr_stat (AST *expr);
AST *new_return_stat (AST *expr);
AST *new_assign_stat (AST *id, AST *expr);
AST *new_var_decl (int type, AST *id, AST *expr);
AST *new_param_type (int type, AST *id);
AST *new_function_decl (int type, AST *id, AST *param_type_list, AST *block);
AST *new_file (AST *decl);
AST *add_to_file (AST *file, AST *decl);

#endif // AST_HEADER 
