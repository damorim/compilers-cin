#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "ast.h"

#define INI_MAX_COLUMNS 4
#define INI_MAX_ROWS 256

typedef struct Memory Memory;

struct Memory {
	union {
		AST **tree;
		ListNode **ptrs;
		char **strings;
		AST **array;
		AbbrevToken **tokens;

	};
	int current_column;
	int current_row; // at current column
	int columns_allocated;
	int rows_allocated; // at current column

} ast, astptr, str, id_buffer, token_buffer;


void init_memory () {
	token_buffer.current_column = ast.current_column = astptr.current_column = str.current_column = id_buffer.current_column = 0;
	token_buffer.current_row = ast.current_row = astptr.current_row = str.current_row = id_buffer.current_row = 0;
	token_buffer.columns_allocated = ast.columns_allocated = astptr.columns_allocated = str.columns_allocated = id_buffer.columns_allocated = INI_MAX_COLUMNS;
	token_buffer.rows_allocated = ast.rows_allocated = astptr.rows_allocated = str.rows_allocated = id_buffer.rows_allocated = INI_MAX_ROWS;

	ast.tree = malloc(INI_MAX_COLUMNS * sizeof(AST *));
	astptr.ptrs = malloc(INI_MAX_COLUMNS * sizeof(ListNode *));
	str.strings = malloc(INI_MAX_COLUMNS * sizeof(char *));
	id_buffer.array = malloc(INI_MAX_COLUMNS * sizeof(AST *));
	token_buffer.tokens = malloc(INI_MAX_COLUMNS * sizeof(AbbrevToken *));

	assert(ast.tree && astptr.ptrs && str.strings && id_buffer.array);

	ast.tree[0] = malloc(INI_MAX_ROWS * sizeof(AST));
	astptr.ptrs[0] = malloc(INI_MAX_ROWS * sizeof(ListNode));
	str.strings[0] = malloc(INI_MAX_ROWS * sizeof(char));
	id_buffer.array[0] = malloc(INI_MAX_ROWS * sizeof(AST));
	token_buffer.tokens[0] = malloc(INI_MAX_ROWS * sizeof(AbbrevToken));

	assert(ast.tree[0] && astptr.ptrs[0] && str.strings[0] && id_buffer.array[0] && token_buffer.tokens[0]);
}

#define row_full(x) ((x)->current_row + input_length > (x)->rows_allocated)
#define all_columns_full(x) ((x)->current_column + 1 >= (x)->columns_allocated)

void resize_if_needed (Memory* mem, unsigned long type_size, int input_length) {
	if (row_full(mem)) {
		if (all_columns_full(mem)) {
			mem->columns_allocated *= 2;
			mem->ptrs = realloc(mem->ptrs, mem->columns_allocated * sizeof(void *));
			assert(mem->ptrs);
		}
		mem->current_column++;
		mem->rows_allocated *= 2;
		mem->ptrs[mem->current_column] = malloc(mem->rows_allocated * type_size);
		assert(mem->ptrs[mem->current_column]);
		mem->current_row = 0;
	}
}

AST *alloc_ast () {
	//print("ast: %d %d %d %d\n", ast.columns_allocated, ast.rows_allocated, ast.current_column, ast.current_row);
	resize_if_needed(&ast, sizeof(AST), 1);
	int previous_row = ast.current_row++;
	return &ast.tree[ast.current_column][previous_row];
}

char *alloc_str (int length) {
	//print("str: %d %d %d %d\n", str.columns_allocated, str.rows_allocated, str.current_column, str.current_row);
	resize_if_needed(&str, sizeof(char), length);
	int previous_row = str.current_row;
	str.current_row += length;
	return &str.strings[str.current_column][previous_row];
}

AbbrevToken *new_abbrev_token (Token *token) {
	AbbrevToken *abbrev_token;
	resize_if_needed(&token_buffer, sizeof(AbbrevToken), 1);
	int previous_row = token_buffer.current_row++;
	abbrev_token = &token_buffer.tokens[token_buffer.current_column][previous_row];

	*abbrev_token = (AbbrevToken) {
		.filename = token->filename,
		.line_start = token->line_start,
		.line = token->line,
		.column = token->column,
	};
	return abbrev_token;
}

// a mess
ListNode *alloc_astptr () {
	resize_if_needed(&astptr, sizeof(ListNode), 1);
	int previous_row = astptr.current_row++;
	return &astptr.ptrs[astptr.current_column][previous_row];
}

AST *alloc_id () {
	resize_if_needed(&id_buffer, sizeof(AST), 1);
	int previous_row = id_buffer.current_row++;
	return &id_buffer.array[id_buffer.current_column][previous_row];
}


AST *new_literal (int type, void *value, Token *token) {
	//print("NEW_NUMBER\n");
	AST *ast = alloc_ast();
	ast->expr.type = LITERAL_EXPRESSION;
	switch (type) {
	case INTEGER:
		ast->expr.literal.int_value = *(long *)value;
		ast->expr.literal.type = TYPE_INT;
		break;
	case FLOAT:
		ast->expr.literal.float_value = *(double *)value;
		ast->expr.literal.type = TYPE_FLOAT;
		break;
	case STRING:
		ast->expr.literal.type = TYPE_STRING;
		break;
	}
	ast->expr.literal.token = new_abbrev_token(token);
	return ast;
}

AST *locate_id (char *string, int hash, int length) {
	AST *ptr;
	int i, j;
	//print("hash: 0x%08x, length: %d\n", hash, length);
	for (i = 0; i < id_buffer.current_column; i++) {
		for (j = 0, ptr = id_buffer.array[i]; j < INI_MAX_ROWS << i; j++, ptr++) {
			if (ptr->id.hash == hash && ptr->id.length == length && strcmp(ptr->id.string, string) == 0) return ptr;
		}
	}
	for (j = 0, ptr = id_buffer.array[id_buffer.current_column]; j < id_buffer.current_row; j++, ptr++) {
		if (ptr->id.hash == hash && ptr->id.length == length && strcmp(ptr->id.string, string) == 0) return ptr;
	}
	return NULL;
}


AST *new_identifier (Token *token, int at_global_scope) {
	//print("NEW_ID\n");
	char *id_str = strtoken(token);
	AST *ast = locate_id(id_str, token->id_hash, token->length);
	if (ast == NULL) {
		//print("id %s is brand new\n", id);
		ast = alloc_id();
		ast->id.length = token->length;
		ast->id.hash = token->id_hash;
		ast->id.string = strcpy(alloc_str(token->length+1), id_str);
		ast->id.type = NO_TYPE;
		ast->id.declaration = NULL;
		ast->id.flags = 0;
		if (at_global_scope) {
			ast->id.flags |= IS_GLOBAL;
		}
	}
	return ast;
}

AST *new_id_expr (AST *id, Token *token) {
	AST *ast = alloc_ast();
	ast->expr.type = IDENTIFIER_EXPRESSION;
	ast->expr.id.id = id;
	ast->expr.id.token = new_abbrev_token(token);
	return ast;
}

AST *new_binary_expr (AST *left_expr, char op, AST *right_expr, Token *token) {
	/*switch (op) {
		case '+':
			if (left_expr->expr.type == LITERAL_EXPRESSION && left_expr->expr.literal.value == 0)
				return right_expr;
			if (right_expr->expr.type == LITERAL_EXPRESSION && right_expr->expr.literal.value == 0)
				return left_expr;
			break;
		case '-':
			if (right_expr->expr.type == LITERAL_EXPRESSION && right_expr->expr.literal.value == 0)
				return left_expr;
			break;
		case '*':
			if (left_expr->expr.type == LITERAL_EXPRESSION) {
				if (left_expr->expr.literal.value == 0) return left_expr;
				if (left_expr->expr.literal.value == 1) return right_expr;
			}
			if (right_expr->expr.type == LITERAL_EXPRESSION) {
				if (right_expr->expr.literal.value == 0) return right_expr;
				if (right_expr->expr.literal.value == 1) return left_expr;
			}
			break;
		case '/':
			if (left_expr->expr.type == LITERAL_EXPRESSION && left_expr->expr.literal.value == 0)
				return left_expr;
			if (right_expr->expr.type == LITERAL_EXPRESSION && right_expr->expr.literal.value == 1)
				return left_expr;
			break;
		default: break;
	}*/
	//print("NEW_BIN_EXPR\n");
	AST *ast = alloc_ast();
	ast->expr.type = BINARY_EXPRESSION;
	ast->expr.binary_expr.operation = op;
	ast->expr.binary_expr.left_expr = left_expr;
	ast->expr.binary_expr.right_expr = right_expr;
	ast->expr.binary_expr.token = new_abbrev_token(token);
	return ast;
}

AST *new_unary_minus (AST *expr, Token *token) {
	//if (expr->expr.type == LITERAL_EXPRESSION && expr->expr.literal.int_value == 0)
		//return expr;
	//print("NEW_UNARY_EXPR\n");
	AST *ast = alloc_ast();
	ast->expr.type = UNARY_MINUS_EXPRESSION;
	ast->expr.unary_minus.expr = expr;
	ast->expr.unary_minus.token = new_abbrev_token(token);
	return ast;
}

AST *new_function_call (AST *id, AST *expr_list, Token *token) {
	//print("NEW_FUNCTION_CALL\n");
	AST *ast = alloc_ast();
	ast->expr.type = FUNCTION_CALL_EXPRESSION;
	ast->expr.function_call.id = id;
	ast->expr.function_call.expr_list = expr_list;
	ast->expr.function_call.token = new_abbrev_token(token);
	return ast;
}

AST *new_list (AST *node) {
	//print("NEW_EXPR_LIST\n");
	AST *ast = alloc_ast();
	ast->list.num_items = 1;
	ast->list.first = alloc_astptr();
	*ast->list.first = (ListNode){ node, NULL };
	ast->list.last = ast->list.first;
	return ast;
}

AST *add_to_list (AST *list, AST *node) {
	//print("ADD_EXPR_TO_LIST\n");
	if (list == NULL) {
		AST *ast = alloc_ast();
		ast->list.num_items = 1;
		ast->list.first = alloc_astptr();
		*ast->list.first = (ListNode){ node, NULL };
		ast->list.last = ast->list.first;
		return ast;
	} else {
		list->list.num_items++;
		ListNode *tmp = alloc_astptr();
		list->list.last->next = tmp;
		*tmp = (ListNode){ node, NULL };
		list->list.last = tmp;
		return list;
	}
}

AST *new_var_decl_list (AST *ast) {
	ast->stat.type = VARIABLE_DECLARATION_LIST;
	return new_list(ast);
}


AST *new_expr_stat (AST *expr) {
	//print("NEW_EXPR_STAT\n");
	AST *ast = alloc_ast();
	ast->stat.type = EXPRESSION_STATEMENT;
	ast->stat.expr.expr = expr;
	return ast;
}

AST *new_return_stat (AST *expr, Token *token) {
	//print("NEW_RETURN_STAT\n");
	AST *ast = alloc_ast();
	ast->stat.type = RETURN_STATEMENT;
	ast->stat.ret.expr = expr;
	ast->stat.ret.token = new_abbrev_token(token);
	return ast;
}

AST *new_assign_stat (AST *id, AST *expr, Token *token) {
	//print("NEW_ASSIGN_STAT\n");
	AST *ast = alloc_ast();
	ast->stat.type = ASSIGN_STATEMENT;
	ast->stat.assign.id = id;
	ast->stat.assign.expr = expr;
	ast->stat.assign.token = new_abbrev_token(token);
	return ast;
}

// TODO: distinguish between statement and declaration
AST *new_var_decl (int type, AST *id, AST *expr, Token *token, Token *assign_token) {
	//print("NEW_VAR_DECL\n");
	AST *ast = alloc_ast();
	ast->decl.type = VARIABLE_DECLARATION;
	ast->decl.variable.type = type;
	ast->decl.variable.id = id;
	ast->decl.variable.expr = expr;
	ast->decl.variable.token = new_abbrev_token(token);
	ast->decl.variable.assign_token = expr ? new_abbrev_token(assign_token) : NULL;

	id->id.decl_type = VARIABLE_DECLARATION;
	id->id.declaration = ast;
	id->id.type = type;
	return ast;
}


AST *new_function_decl (int type, AST *id, AST *param_decl_list, AST *stat_block, Token *token) {
	//print("NEW_FUNC_DECL\n");
	AST *ast = alloc_ast();
	ast->decl.type = FUNCTION_DECLARATION;
	ast->decl.function.type = type;
	ast->decl.function.id = id;
	ast->decl.function.param_decl_list = param_decl_list;
	ast->decl.function.stat_block = stat_block;
	ast->decl.function.token = new_abbrev_token(token);

	id->id.decl_type = FUNCTION_DECLARATION;
	id->id.declaration = ast;
	id->id.type = type;
	return ast;
}

char *strlinea (const AbbrevToken *restrict token) {
	int i;
	char *ptr = token->line_start;
#define STRING_SIZE 256
	static char string[STRING_SIZE];
	for (i = 0; i < STRING_SIZE-1 && *ptr != '\n'; i++) {
		string[i] = *ptr++;
	}
#undef STRING_SIZE
	string[i] = '\0';
	return string;
}
