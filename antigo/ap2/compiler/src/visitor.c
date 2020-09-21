#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "visitor.h"


int current_scope = 0;
int next_scope = 1;
int next_ir_number;
int obsolete_ir_for_globals;


void visit_file (AST *root) {
	printf("visiting_file\n");
    printf("; file has %d declarations\n\n", root->list.num_items);

    for (List *ptr = root->list.first; ptr != NULL; ptr = ptr->next) {
        switch (ptr->ast->decl.type) {
            case FUNCTION_DECLARATION:
				visit_function_decl(ptr->ast); break;
            case VARIABLE_DECLARATION:
				visit_var_decl(ptr->ast); break;
            default:
				fprintf(stderr, "UNKNOWN DECLARATION TYPE %c\n", ptr->ast->decl.type);
				break;
        }
    }
}

void visit_function_decl (AST *ast) {
	printf("visiting_function_decl\n");
    AST *params = ast->decl.function.param_type_list;
    if (params != NULL) {
        printf("i32");
        for (int i = 1; i < params->list.num_items; i++) {
            printf(", i32");
        }
    }
    printf(") {\n");
    if (ast->decl.function.block != NULL) {
        visit_block(ast->decl.function.block, params);
    }
    printf("}\n\n");
}

// what is surrounded by { }
void visit_block (AST *block, AST *params) {
	printf("visiting_block\n");

    for (List *ptr = block->list.first; ptr != NULL; ptr = ptr->next) {
        visit_stat(ptr->ast);
    } 
}


void visit_stat (AST *stat) {
	printf("visiting_statement\n");
    switch (stat->stat.type) {
        case VARIABLE_DECLARATION: visit_var_decl(stat); break;
        case ASSIGN_STATEMENT: visit_assign_stat(stat); break;
        case RETURN_STATEMENT: visit_return_stat(stat); break;
        case EXPRESSION_STATEMENT: visit_expr(stat->stat.expr.expr); break;
        default: fprintf(stderr, "UNKNOWN STATEMENT TYPE %c\n", stat->stat.type); break;
    }
}

void visit_var_decl (AST *ast) {
	printf("visiting_var_decl\n");
    AST *id = ast->decl.variable.id;

    if (ast->decl.variable.expr != NULL) {
        ExprReturn expr = visit_expr(ast->decl.variable.expr);
	}
}


void visit_return_stat (AST *ast) {
	printf("visiting_return stat\n");
    ExprReturn expr = visit_expr(ast->stat.ret.expr);
}

void visit_assign_stat (AST *assign) {
	printf("visiting_assign stat\n");
    ExprReturn expr = visit_expr(assign->stat.assign.expr);
}

ExprReturn visit_expr (AST *expr) {
	printf("visiting_expression\n");
    ExprReturn ret = { -1, false };
    switch (expr->expr.type) {
		case BINARY_EXPRESSION:
			switch (expr->expr.binary_expr.operation) {
			case '+': ret = visit_add(expr); break;
			case '-': ret = visit_sub(expr); break;
			case '*': ret = visit_mul(expr); break;
			case '/': ret = visit_div(expr); break;
			default: fprintf(stderr, "WTF?\n");
			}
			break;
        case UNARY_MINUS_EXPRESSION:
			ret = visit_unary_minus(expr); break;
        case LITERAL_EXPRESSION:
			ret = visit_literal(expr); break;
        case IDENTIFIER_EXPRESSION:
			ret = visit_id(expr->expr.id.id); break;
        case FUNCTION_CALL_EXPRESSION:
			ret = visit_function_call(expr); break;
        default:
			fprintf(stderr, "UNKNOWN EXPRESSION TYPE %c\n", expr->expr.type);
			break;
    }
    return ret;
}

// não implementar
ExprReturn visit_function_call (AST *ast) {
	printf("visiting_function_call\n");
    ExprReturn ret = {}, arg_expr[20]; //instead of alloca
    AST *arg_list = ast->expr.function_call.expr_list;

    if (arg_list != NULL) {
        int i = 0;
        for (List *ptr = arg_list->list.first; ptr != NULL; ptr = ptr->next) {
            arg_expr[i++] = visit_expr(ptr->ast);
        }
    }
    return ret;
} 

ExprReturn visit_id (AST *ast) {
	printf("visiting_identifier\n");
    ExprReturn ret = {}; // armazenar aqui
	if (ast->id.type == TYPE_INT) {
		ret = (ExprReturn){ ast->id.int_value, TYPE_INT };
	} else if (ast->id.type == TYPE_FLOAT) {
		ret = (ExprReturn){ ast->id.float_value, TYPE_FLOAT };
	}
    return ret;
}

ExprReturn visit_literal (AST *ast) {
	printf("visiting_literal\n");
    ExprReturn ret = {};
    return ret;
}

ExprReturn visit_unary_minus (AST *ast) {
	printf("visiting_unary_minus\n");
    ExprReturn expr, ret = {};
    expr = visit_expr(ast->expr.unary_minus.expr);
    return ret;
}

ExprReturn visit_add (AST *ast) {
	printf("visiting_add\n");
    ExprReturn left, right, ret = {};
    left  = visit_expr(ast->expr.binary_expr.left_expr);
    right = visit_expr(ast->expr.binary_expr.right_expr);
    return ret;
}

ExprReturn visit_sub (AST *ast) {
	printf("visiting_sub\n");
    ExprReturn left, right, ret = {};
    left  = visit_expr(ast->expr.binary_expr.left_expr);
    right = visit_expr(ast->expr.binary_expr.right_expr);
    return ret;
}

ExprReturn visit_mul (AST *ast) {
	printf("visiting_mul\n");
    ExprReturn left, right, ret = {};
    left  = visit_expr(ast->expr.binary_expr.left_expr);
    right = visit_expr(ast->expr.binary_expr.right_expr);
    return ret;
}

ExprReturn visit_div (AST *ast) {
	printf("visiting_div\n");
    ExprReturn left, right, ret = {};
    left  = visit_expr(ast->expr.binary_expr.left_expr);
    right = visit_expr(ast->expr.binary_expr.right_expr);
    return ret;
}

