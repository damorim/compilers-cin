#ifndef VISITOR_HEADER
#define VISITOR_HEADER

#include "ast.h"

typedef struct ExprReturn ExprReturn;
struct ExprReturn {
	union {
		long int_value;
		double float_value;
	};
	int type; // TYPE_INT, TYPE_FLOAT
};

void visit_file (AST *root);
//void visit_global_var_decl (AST *ast);
void visit_function_decl (AST *ast);
void visit_block (AST *block, AST *param);
void visit_stat (AST *stat);
void visit_assign_stat (AST *assign);
void visit_return_stat (AST *assign);
void visit_var_decl (AST *ast);
ExprReturn visit_expr (AST *expr);
ExprReturn visit_add (AST *expr);
ExprReturn visit_sub (AST *expr);
ExprReturn visit_mul (AST *expr);
ExprReturn visit_div (AST *expr);
ExprReturn visit_id (AST *ast);
ExprReturn visit_literal (AST *ast);
ExprReturn visit_unary_minus (AST *ast);
ExprReturn visit_function_call (AST *ast);

#endif // VISITOR_HEADER
