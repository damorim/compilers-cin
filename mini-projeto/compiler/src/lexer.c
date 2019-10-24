#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "lexer.h"

#define err(...) fprintf(stderr, __VA_ARGS__)
enum { false, true };

char keywords_strings[] = "int\nfloat\nvoid\nreturn\n";
unsigned char keywords_types[] = { TYPE_INT, TYPE_FLOAT, TYPE_VOID, RETURN };


int next_token (Token *restrict token) {
	// token->buffpos must be one character after the end of the token on return
	char c;
	while (1) {
		token->line += token->line_span;
		token->length = token->line_span = 0;
		
		switch ((c = *token->buffpos)) {
		case '\0':
			token->length = 0;
			token->type = c;
			return 0;

		case '\n':
			token->line++;
			token->line_start = token->buffpos++;
			break;

		case ' ':
		case '\t':
		case '\r':
			token->buffpos++;
			break;

		case '=':
		case '+':
		case '-':
		case '%':
		case '{':
		case '}':
		case '(':
		case ')':
		case ';':
		case ',':
			token->column = token->buffpos - token->line_start;
			token->start = token->buffpos++;
			token->length = 1;
			token->type = c;
			return 1;

		case '*':
			token->column = token->buffpos - token->line_start;
			token->start = token->buffpos++;
			token->length = 1;
			token->type = c;
			if (*(token->buffpos) == '/') {
				err("%s:%d:%d: lexical error: unmatched closure of multiline comment\n  %d |%s\n",
					token->filename, token->line, token->column, token->line, strline(token));
				return 0;
			}
			return 1;

		case '/':
			c = *(token->buffpos + 1);
			// single line comment
			if (c == '/') {
				do {
					c = *++token->buffpos;
					if (c == '\n' && (*(token->buffpos-1) == '\\' || *(token->buffpos-1) == '\r' && *(token->buffpos-2) == '\\')) {
						token->line_span++;
						token->line_start = token->buffpos;
						c = *++token->buffpos;
					}
				} while (c != '\n');
			}
			// multiline comment
			else if (c == '*') {
				token->column = token->buffpos - token->line_start;
				token->start = token->buffpos;
				do {
					c = *++token->buffpos;
					if (c == '\n') {
						token->line_span++;
						token->line_start = token->buffpos;
					}
				} while (c != '\0' && c != '*' || c == '*' && *(token->buffpos+1) != '/');

				if (c == '\0') {
					err("%s:%d:%d: lexical error: missing closure of multiline comment\n  %d |%s\n",
						token->filename, token->line, token->column, token->line, strline(token));
					return 0;
				}
				token->buffpos += 2;
			}
			// just a division
			else {
				token->column = token->buffpos - token->line_start;
				token->start = token->buffpos++;
				token->length = 1;
				token->type = '/';
				return 1;
			}
			break;

		case '"':
			token->start = token->buffpos;
			token->column = token->buffpos - token->line_start;
			do {
				c = *++token->buffpos;
				if (c == '\n') {
					char b = *(token->buffpos - 1);
					token->line_start = token->buffpos;
					if (b == '\\' || b == '\r' && *(token->buffpos-2) == '\\') {
						token->line_span++;
					} else {
						err("%s:%d:%d: lexical error: missing closing quotes\n  %d |%s\n",
							token->filename, token->line, token->column, token->line, strline(token));
						return 0;
					}
				}
			} while (c != '"' || c == '"' && *(token->buffpos-1) == '\\');

			token->buffpos++;
			token->length = token->buffpos - token->start;
			token->type = STRING;
			return 1;

		default:
			// a word
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_') {
				token->start = token->buffpos;
				token->column = token->buffpos - token->line_start;
				do {
					c = *++token->buffpos;
				} while (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c >= '0' && c <= '9');
				token->length = token->buffpos - token->start;

				int i = 0, word_index = 0;
				while (keywords_strings[i]) {
					int equal = true;
					for (int j = 0; keywords_strings[i] != '\n'; j++, i++) {
						if (keywords_strings[i] != *(token->start + j)) {
							equal = false;
							while (keywords_strings[i++] != '\n');
							break;
						}
					}
					if (equal) {
						switch (keywords_types[word_index]) {
						case TYPE_INT:
						case TYPE_FLOAT:
						case TYPE_VOID:
							token->type = TYPE;
							token->type_type = keywords_types[word_index];
							break;
						default:
							token->type = keywords_types[word_index];
							break;
						}
						return 1;
					}
					word_index++;
				}
				// not a reserved word
				token->type = IDENTIFIER;
				token->id_hash = jenkins_hash(token->start, token->length);
				return 1;
			}
			// an integer or a float
		   	else if (c >= '0' && c <= '9' || c == '.') {
				token->start = token->buffpos;
				token->column = token->buffpos - token->line_start;
				token->type = INTEGER;
				do {
					if (c == '.') {
						if (token->type == INTEGER) {
							token->type = FLOAT;
						} else {
							token->length = token->buffpos - token->start;
							err("%s:%d:%d: lexical error: multiple '.'s on floating point '%s'\n  %d |%s\n",
								token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
							return 0;
						}
					}
					c = *++token->buffpos;
				} while (c >= '0' && c <= '9' || c == '.');
				token->length = token->buffpos - token->start;

				if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_') {
					err("%s:%d:%d: lexical error: invalid suffix for number constant '%s'\n  %d |%s\n",
						token->filename, token->line, token->column, strtoken(token), token->line, strline(token));
					return 0;
				}
				if (token->type == INTEGER) {
					token->int_value = strtol(token->start, NULL, 10);
				} else {
					token->float_value = strtod(token->start, NULL);
				}
				return 1;
			}
			// unknown character
		   	else {
				token->column = token->buffpos - token->line_start;
				token->start = token->buffpos++;
				token->length = 1;
				token->type = UNKNOWN;
				err("%s:%d:%d: lexical error: unknown character '%c'\n  %d |%s\n",
					token->filename, token->line, token->column, c, token->line, strline(token));
				return 1;
			}
			break;
		}

	}
	return 0;
}


char *strtoken (const Token *restrict token) {
#define STRING_SIZE 128
	static char string[STRING_SIZE];
	int ncopy = (token->length >= STRING_SIZE) ? STRING_SIZE-1 : token->length;
#undef STRING_SIZE
	for (int i = 0; i < ncopy; i++) {
		string[i] = token->start[i];
	}
	string[ncopy] = '\0';
	return string;
}


char *strline (const Token *restrict token) {
	int i;
	char *ptr = token->start - token->column + 1;
#define STRING_SIZE 256
	static char string[STRING_SIZE];
	for (i = 0; i < STRING_SIZE-1 && *ptr != '\n'; i++) {
		string[i] = *ptr++;
	}
#undef STRING_SIZE
	string[i] = '\0';
	return string;
}

int jenkins_hash (char *string, int length) {
	int hash = 0;

	for (int i = 0; i < length; i++) {
		hash += string[i];
		hash += hash << 10;
		hash ^= hash >> 6;
	}

	hash += hash << 3;
	hash ^= hash >> 11;
	hash += hash << 15;
	return hash;
}
