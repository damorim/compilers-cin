#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "parser.h"

#define err(...) fprintf(stderr, __VA_ARGS__)
static char *read_file (const char *filename);
enum { false, true };
static int at_global_scope = true;

AST *parse_file (const char *filename) {
	AST *root = NULL, *ast;
	Token token = { .filename = (char *)filename, .line = 1, .column = 1 };
	token.file_buffer = read_file(filename);
	if (!token.file_buffer) return NULL;
	token.buffpos = token.file_buffer;
	token.line_start = token.buffpos - 1;
	at_global_scope = true;

	while (next_token(&token)) {
		ast = parse_declaration(&token);
		if (!ast) return NULL;
		root = add_to_list(root, ast);
	}

	return root;
}

AST *parse_statement (Token *token) {
	Token prev_token = *token, stored_token;
	AST *stat = NULL, *id, *ast;

	switch (token->type) {
	case TYPE:
		stat = parse_declaration(token);
		if (!stat) return NULL;
		if (token->type != ';') {
			err("%s:%d:%d: syntax error: expecting a ';' after a variable declaration statement, but '%s' is not ';'\n  %d |%s\n",
					token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			return NULL;
		}
		next_token(token);
		break;
	case IDENTIFIER:
		id = new_identifier(token, at_global_scope);
		next_token(token);
		switch (token->type) {
		case '(':
			*token = prev_token;
			ast = parse_expression(token);
			if (!ast) return NULL;
			if (token->type != ';') {
				err("%s:%d:%d: syntax error: expecting a ';' after an expression statement, but '%s' is not ';'\n  %d |%s\n",
					token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
				if (prev_token.line != token->line) {
					err("%s:%d:%d: the statement started here:\n  %d | %s\n",
						prev_token.filename, prev_token.line, prev_token.column, prev_token.line, strline(&prev_token));
				}
				return NULL;
			}
			stat = new_expr_stat(ast);
			next_token(token);
			break;
		case '=':
			stored_token = *token;
			next_token(token);
			ast = parse_expression(token);
			if (!ast) return NULL;
			if (token->type != ';') {
				err("%s:%d:%d: syntax error: expecting a ';' after an assignment statement, but '%s' is not ';'\n  %d |%s\n",
						token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
				if (prev_token.line != token->line) {
					err("%s:%d:%d: the statement started here:\n  %d | %s\n",
						prev_token.filename, prev_token.line, prev_token.column, prev_token.line, strline(&prev_token));
				}
				return NULL;
			}
			stat = new_assign_stat(id, ast, &stored_token);
			next_token(token);
			break;
		default:
			err("%s:%d:%d: syntax error: expecting '(' or '=' after starting a statement with an identifier, but '%s' is neither of them\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			if (prev_token.line != token->line) {
				err("%s:%d:%d: the statement started here:\n  %d | %s\n",
					prev_token.filename, prev_token.line, prev_token.column, prev_token.line, strline(&prev_token));
			}
			return NULL;
		}
		break;
	case RETURN:
		stored_token = *token;
		ast = NULL;
		next_token(token);
		if (token->type != ';') {
			ast = parse_expression(token);
			if (!ast) return NULL;
			if (token->type != ';') {
				err("%s:%d:%d: syntax error: expecting a ';' after a return statement, but '%s' is not ';'\n  %d |%s\n",
					token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
				if (prev_token.line != token->line) {
					err("%s:%d:%d: the statement started here:\n  %d | %s\n",
						prev_token.filename, prev_token.line, prev_token.column, prev_token.line, strline(&prev_token));
				}
				return NULL;
			}
		}
		stat = new_return_stat(ast, &stored_token);
		next_token(token);
		break;
	default:
		err("%s:%d:%d: syntax error: expecting type, identifier, constant or keyword to start a statement, but '%s' is neither of them\n  %d |%s\n",
			token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
		return NULL;
	}

	return stat;
}


AST *parse_declaration (Token *token) {
	Token prev_token = *token, stored_token = *token, assign_token = {};
	AST *decl = NULL, *id, *ast, *expr = NULL, *list;
	int type;

	if (token->type != TYPE) {
		err("%s:%d:%d: syntax error: expecting a declaration, but '%s' is not a type\n  %d |%s\n",
			token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
		return NULL;
	}
	type = token->type_type;

	next_token(token);
	if (token->type != IDENTIFIER) {
		err("%s:%d:%d: syntax error: expecting an identifier for declaration, but '%s' is not\n  %d |%s\n",
			token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
		return NULL;
	}
	id = new_identifier(token, at_global_scope);

	next_token(token);
	switch (token->type) {
	case '(': {
		if (!at_global_scope) {
			err("%s:%d:%d: syntax error: functions can only be declared at global scope\n  %d |%s\n",
					token->filename, token->line, token->column, token->line, strline(token));
			return NULL;
		}
		at_global_scope = false;
		AST *param_decl_list = NULL, *stat_block = NULL;
		// parse_call_parameteres(token);
		prev_token = *token;
		next_token(token);
		if (token->type != ')') {
			*token = prev_token;
			param_decl_list = parse_declaration_parameters(token);
			if (!param_decl_list) return NULL;
			if (token->type != ')') {
				err("%s:%d:%d: syntax error: expecting ')' enclosing function parameters declarations, but got '%s'\n  %d |%s\n",
						token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
				return NULL;
			}
		}
		next_token(token);
		if (token->type != '{') {
			err("%s:%d:%d: syntax error: expecting '{' for function declaration, but got '%s'\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			return NULL;
		}

		next_token(token);
		while (token->type != '}') {
			ast = parse_statement(token);
			if (!ast) return NULL;
			stat_block = add_to_list(stat_block, ast);
		}

		if (token->type != '}') {
			err("%s:%d:%d: syntax error: expecting '}' at the end of a function declaration, but got '%s'\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			return NULL;
		}
		decl = new_function_decl(type, id, param_decl_list, stat_block, &stored_token);
		at_global_scope = true;
		break; }
	case '=':
		assign_token = *token;
		next_token(token);
		expr = parse_expression(token);
		if (!expr) return NULL;
		// NO break;
	case ',':
		decl = ast = new_var_decl(type, id, expr, &stored_token, &assign_token);
		if (token->type == ',') {
			list = new_list(decl);
			decl = new_var_decl_list(list);
		}
		while (token->type == ',') {
			expr = NULL;
			next_token(token);
			if (token->type != IDENTIFIER) {
				err("%s:%d:%d: syntax error: expecting an identifier for declaration, but '%s' is not an identifier\n  %d |%s\n",
					token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
				return NULL;
			}
			id = new_identifier(token, at_global_scope);
			next_token(token);
			if (token->type == '=') {
				assign_token = *token;
				next_token(token);
				expr = parse_expression(token);
				if (!expr) return NULL;
			}
			ast = new_var_decl(type, id, expr, &stored_token, &assign_token);
			list = add_to_list(list, ast);
		}

		if (token->type != ';') {
			err("%s:%d:%d: syntax error: expecting a ';' after variable declaration statement, but '%s' is not ';'\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			if (prev_token.line != token->line) {
				err("%s:%d:%d: the statement started here:\n  %d | %s\n",
					prev_token.filename, prev_token.line, prev_token.column, prev_token.line, strline(&prev_token));
			}
			return NULL;
		}
		break;
	case ';':
		decl = new_var_decl(type, id, NULL, &stored_token, NULL);
		break;
	default:
		err("%s:%d:%d: syntax error: expecting ';', ',', '=' or '(' after declaring identifier, but '%s' is neither\n  %d |%s\n",
			token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
		return NULL;
	}
	return decl;
}


AST *parse_declaration_parameters (Token *token) {
	// token poninter starts over the '(' char
	Token stored_token = *token;
	AST *param = NULL, *ast, *id;
	int type;

	do {
		next_token(token);
		if (token->type == TYPE) {
			type = token->type_type;
			next_token(token);
			if (token->type != IDENTIFIER) {
				err("%s:%d:%d: syntax error: expecting an identifier after a type at parameters declarations for function declaration, but '%s' is not\n  %d |%s\n",
					token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
				return NULL;
			}
			id = new_identifier(token, at_global_scope);
			next_token(token);
		} else {
			err("%s:%d:%d: syntax error: expecting comma separated parameter declarations, but '%s' fits neither\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			return NULL;
		}
		ast = new_var_decl (type, id, NULL, &stored_token, NULL);
		//ast = new_param_decl(type, id, &stored_token);
		param = add_to_list(param, ast);
	} while (token->type == ',');

	return param;
}


AST *parse_expression_op (Token *token, int prev_op) {
	Token prev_token;
	int empty = true, op;
	AST *expr = NULL, *id, *ast, *list;

	while (1) {
		switch (token->type) {
		case IDENTIFIER:
			prev_token = *token;
			id = new_identifier(token, at_global_scope);
			next_token(token);
			if (token->type == '(') {
				list = NULL;
				do {
					next_token(token);
					if (token->type != ')') {
						ast = parse_expression(token);
						if (!ast) return NULL;
						list = add_to_list(list, ast);
					}
				} while (token->type == ',');

				if (token->type != ')') {
					err("%s:%d:%d: syntax error: expecting a ')' after function call parameters, but '%s' is not ')'\n  %d |%s\n",
						token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
					return NULL;
				}
				expr = new_function_call(id, list, &prev_token);
			} else {
				*token = prev_token;
				expr = new_id_expr(id, token);
			} // NO break;
			next_token(token);
			goto jump_point;
		case '-': // unary minus
			prev_token = *token;
			next_token(token);
			ast = parse_expression_op(token, UNARY_MINUS_EXPRESSION);
			if (!ast) return NULL;
			expr = new_unary_minus(ast, &prev_token);
			goto jump_point;
		case '+': // unary plus
			next_token(token);
			break;
		case '(':
			empty = false;
			prev_token = *token;
			next_token(token);
			expr = parse_expression(token);
			if (!expr) return NULL;

			if (token->type != ')') {
				err("%s:%d:%d: syntax error: unclosed '(' in expression\n  %d |%s\n",
					prev_token.filename, prev_token.line, prev_token.column, prev_token.line, strline(&prev_token));
				return NULL;
			}
			next_token(token);
			goto jump_point;
		case INTEGER:
		case FLOAT:
		case STRING:
			expr = new_literal(token->type, &token->int_value, token);
			next_token(token);
			// NO break;
		jump_point:
			empty = false;
			while (1) {
				prev_token = *token;
				op = token->type;
				switch (op) {
				case '+':
				case '-':
					if (prev_op != 0) {
						return expr;
					}
					next_token(token);
					ast = parse_expression_op(token, op);
					if (!ast) return NULL;
					expr = new_binary_expr(expr, op, ast, &prev_token);
					break;
				case '*':
				case '/':
				case '%': // TODO: implement this with prev_op_precedence
					if (prev_op != 0 && prev_op != '+' && prev_op != '-') {
						return expr;
					}
					next_token(token);
					ast = parse_expression_op(token, op);
					if (!ast) return NULL;
					expr = new_binary_expr(expr, op, ast, &prev_token);
					break;
				default:
					return expr;
				}
			}
			break;
		default:
			if (!empty) return expr;
			err("%s:%d:%d: syntax error: expecting an expression, but '%s' does not fit\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			return NULL;
		}
	}
	return expr;
}

static char *read_file (const char *filename) {
	FILE *file;
	char *buffer;
	long size, nread;

	// open file
	file = fopen(filename, "rb");
	if (!file) {
		err("error: failed to open file '%s': %s\n", filename, strerror(errno));
		goto open_file_error;
	}
	// get file size
	fseek(file, 0, SEEK_END);
	size = ftell(file);
	if (size <= 0) {
		err("error: failed to get '%s' file size: %s\n", filename, strerror(errno));
		goto file_size_error;
	}
	fseek(file, 0, SEEK_SET);
	// allocate buffer for file
	buffer = malloc(size + 2);
	if (!buffer) {
		err("error: failed to allocate %ld bytes for file '%s'\'s buffer: %s\n", size, filename, strerror(errno));
		goto buffer_alloc_error;
	}
	// read entire file
	nread = fread(buffer, 1, size, file);
	if (nread < size) {
		err("error: failed to read the %ld bytes of file '%s' (%ld were read): %s\n", size, filename, nread, strerror(errno));
		goto file_read_error;
	}
	fclose(file);

	buffer[nread] = '\n';
	buffer[nread+1] = '\0';
	printf("read %ld bytes from file '%s' and it has %ld\n", nread, filename, size);
	return buffer;

file_read_error:
	free(buffer);
buffer_alloc_error:
file_size_error:
	fclose(file);
open_file_error:
	return NULL;
}

