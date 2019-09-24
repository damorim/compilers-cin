#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "parser.h"

#define err(...) fprintf(stderr, __VA_ARGS__) 
static char *read_file (const char *filename);

AST *parse_file (const char *filename) { // TODO: return the AST root
	AST *root = NULL, *ast;
	Token token = { .filename = (char *)filename, .line = 1, .column = 1 };
	token.file_buffer = read_file(filename);
	if (!token.file_buffer) return NULL;
	token.buffpos = token.file_buffer;
	token.line_start = token.buffpos - 1;

	while (next_token(&token)) {
		ast = parse_declaration(&token);
		if (!ast) return NULL;
		root = add_to_file(root, ast);
	}

	return root;
}

AST *parse_statement (Token *token) {
	Token prev_token = *token;
	AST *stat, *id, *ast;

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
		id = new_identifier(token);
		next_token(token);
		switch (token->type) {
		case '(':
			*token = prev_token; // TODO: have two tokens tied together at all time
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
			stat = new_assign_stat(id, ast);
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
		next_token(token);
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
		stat = new_return_stat(ast);
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
	Token prev_token = *token;
	AST *decl, *id, *ast, *expr = NULL, *list;
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
	id = new_identifier(token);

	next_token(token);
	switch (token->type) {
	case '(': {
		AST *param_type_list = NULL, *stat_block = NULL;
		// parse_call_parameteres(token);
		prev_token = *token;
		next_token(token);
		if (token->type != ')') {
			*token = prev_token;
			param_type_list = parse_declaration_parameters(token);
			if (!param_type_list) return NULL;
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
		decl = new_function_decl(type, id, param_type_list, stat_block);
		break; }
	case '=':
		next_token(token);
		expr = parse_expression(token);
		if (!expr) return NULL;
		// NO break;
	case ',':
		decl = ast = new_var_decl(type, id, expr);
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
			id = new_identifier(token);
			next_token(token);
			if (token->type == '=') {
				next_token(token);
				expr = parse_expression(token);
				if (!expr) return NULL;
			}
			ast = new_var_decl(type, id, expr);
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
		decl = new_var_decl(type, id, NULL);
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
			id = new_identifier(token);
			next_token(token);
		} else {
			err("%s:%d:%d: syntax error: expecting comma separated parameter declarations, but '%s' fits neither\n  %d |%s\n",
				token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
			return NULL;
		}
		ast = new_param_type(type, id);
		param = add_to_list(param, ast);
	} while (token->type == ',');

	return param;
}


AST *parse_expression_op (Token *token, int prev_op) {
	Token prev_token;
	int empty = true, op;
	AST *expr, *id, *ast, *list;

	while (1) {
		switch (token->type) {
		case IDENTIFIER:
			prev_token = *token;
			id = new_identifier(token);
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
				expr = new_function_call(id, list);
			} else {
				*token = prev_token;
				expr = new_id_expr(id);
			} // NO break;
			goto jump;
		case INTEGER:
		case FLOAT:
		case STRING:
			expr = new_literal(token->type, &token->int_value);
			// NO break;
		jump:
			empty = false;
			next_token(token);
			while (1) {
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
					expr = new_binary_expr(expr, op, ast);
					break;
				case '*':
				case '/':
					if (prev_op == '*' || prev_op == '/') {
						return expr;
					}
					next_token(token);
					ast = parse_expression_op(token, op);
					if (!ast) return NULL;
					expr = new_binary_expr(expr, op, ast); 
					break;
				default:
					return expr;
				}
			}
			break;
		case '-': // unary minus
			empty = false;
			next_token(token);
			ast = parse_expression(token);
			if (!ast) return NULL;
			expr = new_unary_minus(ast);
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

