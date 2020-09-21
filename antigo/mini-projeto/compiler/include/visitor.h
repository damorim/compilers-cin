#ifndef VISITOR_HEADER
#define VISITOR_HEADER

#include "ast.h"

enum ExprResultType {
	LLIR_REGISTER,
	INTEGER_CONSTANT,
	FLOAT_CONSTANT,
};

typedef struct ExprResult ExprResult;
struct ExprResult {
	union {
		long int_value;
		double float_value;
		long ssa_register;
	};
	int type; // enum ExprResultType
};

void visit_file (AST *root);
//void visit_global_var_decl (AST *ast);
ExprResult visit_stat (AST *stat);
ExprResult visit_return_stat (AST *assign);
void visit_assign_stat (AST *assign);
void visit_var_decl (AST *ast);
void visit_function_decl (AST *ast);
ExprResult visit_stat_block (AST *stat_block, AST *params, int return_type);
ExprResult visit_expr (AST *expr);
ExprResult visit_add (AST *expr);
ExprResult visit_sub (AST *expr);
ExprResult visit_mul (AST *expr);
ExprResult visit_div (AST *expr);
ExprResult visit_mod (AST *expr);
ExprResult visit_id (AST *ast);
ExprResult visit_literal (AST *ast);
ExprResult visit_unary_minus (AST *ast);
ExprResult visit_function_call (AST *ast);

#endif // VISITOR_HEADER
