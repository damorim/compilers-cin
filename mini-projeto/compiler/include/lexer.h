#ifndef LEXER_HEADER
#define LEXER_HEADER

// structs prototypes
typedef struct Token Token;

struct Token {
	char *filename;    // name of the file read by the lexer
	char *file_buffer; // pointer to start of the file
	char *buffpos;     // pointer to next character to be read
	char *start;       // pointer to the start of the token just read
	char *line_start;  // pointer to start of line where buffpos is

	int length;    // length of the token
	int line;      // line of the token
	int column;    // column in current line where the token starts
	int line_span; // number of lines the token spans

	union {
		long int_value;     // value of the integer literal token
		double float_value; // value of the floating point literal token
		long id_hash;       // hash of the identifier's string
		long type_type;     // vide enum TypeType (used if the token type is TYPE)
	};

	int type; // vide enum TokenType
};

enum TokenType {
	// '\0' '=' '+' '-' '/' '*' '{' '}' '(' ')' ';' ','
	UNKNOWN = 0x80, // unrecognized character
	TYPE,           // the type of the type is specified at enum TypeType
	IDENTIFIER,     // variable or function name
	INTEGER,        // integer number literal
	FLOAT,          // floating point number literal
	STRING,         // a string delimited by " "
	RETURN,         // return
};

enum TypeType {
	NO_TYPE,        // identifier has no type (not used in lexer, but later)
	TYPE_VOID,      // only functions may be void
	TYPE_INT,
	TYPE_FLOAT,
	TYPE_STRING,    // placeholder for char * (not used in lexer, but later)
};


// function prototypes
int next_token (Token *restrict token);
char *strtoken (const Token *restrict token);
char *strline (const Token *restrict token);
int jenkins_hash (char *string, int length);

#endif // LEXER_HEADER
