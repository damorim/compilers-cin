#ifndef PARSER_HEADER
#define PARSER_HEADER

#include "lexer.h"
#include "ast.h"

#define parse_expression(token) parse_expression_op(token, 0)

// function prototypes
AST *parse_file (const char *filename);
AST *parse_declaration (Token *token);
AST *parse_declaration_parameters (Token *token);
AST *parse_expression_op (Token *token, int prev_op);
AST *parse_statement (Token *token);

#endif // PARSER_HEADER
