#ifndef LEXER_HEADER
#define LEXER_HEADER

// structs prototypes
typedef struct Token Token;

struct Token {
	char *filename, *file_buffer, *buffpos, *start, *line_start;
	int length, line, column, line_span; // number of lines the token spans
	union { long int_value; double float_value; long id_hash; long type_type; };
	int type; // type_type (one line above) = TYPE_INT or TYPE_FLOAT
};

// function prototypes
int next_token (Token *restrict token);
char *strtoken (const Token *restrict token);
char *strline (const Token *restrict token);
int jenkins_hash (char *string, int length);

enum TokenType {
	// '=' '+' '-' '/' '*' '{' '}' '(' ')' ';' ','
	UNKNOWN = 0x80, // unrecognized character
	TYPE,           // int, float, etc
	TYPE_INT,       // int specifically
	TYPE_FLOAT,     // float specifically
	IDENTIFIER,     // variable or function name
	INTEGER,        // integer number literal
	FLOAT,          // floating point number literal
	STRING,         // a string delimited by " "
	RETURN,         // return
};

#endif // LEXER_HEADER
