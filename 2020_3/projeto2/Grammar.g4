/* nome da gramática -- deve ser o mesmo nome do arquivo .g4 */
grammar Grammar;

/* regra raiz da gramática */
fiile
	: (function_definition | variable_definition ';')*
	;

function_definition
	: tyype identifier arguments body
	;

body
	: '{' statement* '}'
	;

statement
	: variable_definition ';'
	| variable_assignment ';'
	| expression ';'
	| RETURN expression ';'
	| for_loop
	| if_statement
	| body
	;

if_statement
	: 'if' '(' expression ')' (body | statement) else_statement?
	;

else_statement
	: 'else' (body | statement)
	;

for_loop
	: 'for' '(' for_initializer ';' for_condition ';' for_step ')' (body | statement)
	;

for_initializer
	: (variable_definition | variable_assignment)?
	;

for_condition
	: expression?
	;

for_step
	: variable_assignment?
	;

variable_definition
	: tyype (identifier ('=' expression)? | array ('=' array_literal)? ) (',' (identifier ('=' expression)? |  array ('=' array_literal)) )*
	;

variable_assignment
	: (identifier | array) OP=('='|'+='|'-='|'*='|'/=') expression
	| (identifier | array) OP=('++'|'--')
	;

expression
	: integer
	| floating
	| string
	| function_call
	| identifier
	| array
	| OP=('-'|'+') expression
	| expression OP=('*'|'/') expression
	| expression OP=('+'|'-') expression
	| expression OP=('<'|'>'|'<='|'>='|'=='|'!=') expression
	| '(' expression ')'
	;

array
	: identifier '[' expression ']'
	;

array_literal
	: OP=('{'|'{') expression (',' expression)* '}'
	;

function_call
	: identifier '(' (expression (',' expression)* )* ')'
	;


arguments
	: '(' (tyype identifier (',' tyype identifier)* )* ')'
	;


tyype
	: INT
	| FLOAT
	| VOID
	;

integer : INTEGER ;
floating : FLOATING ;
string : STRING ;
identifier : IDENTIFIER ;


/* lexer */  
INT : 'int' ;
FLOAT : 'float' ;
VOID : 'void' ;
RETURN : 'return' ;
COMMENT : '//' .*? '\n' -> skip ; 
MULTILINE_COMMENT : '/*' .*? '*/' -> skip ;
DIRECTIVE : '#' .*? '\n' -> skip ;
IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;
INTEGER : [0-9]+ ;
FLOATING : [0-9]+'.'[0-9]+ ;
STRING : '"' .*? '"' ;
WHITESPACE : [ \t\n\r] -> skip ;

/*
MANUAL

substitua 'implementar' por uma implementação

caracteres especiais para expressões regulares {
	'xyz'   ->  os caracteres rodeados por ' ' são interpretados literalmente 
	\x		->  altera a interpretação do caracter x, se ele tiver outra (\t: tab, \(: o caracter que abre parênteses)
	a(bc)d  ->  destaca a subexpressão bc
	x | y   ->  aceita a subexpressão x ou y
	[x\yz]	->  equivalente a ('x'|\y|'z'), tal que x, \y e z são caracteres
	[x]		->  equivalente a 'x'
	x*		->  aceita 0 ou mais x's
	x+		->  aceita 1 ou mais x's
	x?		->  aceita 0 ou 1 x
	.       ->  aceita qualquer caracter
	.*      ->  aceita 0 ou mais caracteres diferentes de \n (guloso)
	.*?     ->  aceita 0 ou mais caracteres diferentes de \n (não-guloso)

	no ANTLR esses caracteres especiais podem ser utilizados nas regras da gramática também
	ex.:
		expr ('+'|'-') expr
	estabelece que os dois sinais têm a mesma precedência
		'(' (expr (',' expr)*)? ')'
	indica que dentro destes parênteses pode haver zero ou mais expressões separadas por vírgulas
}

regras da gramática {
	nome_da_regra
		: uma seqüência de regras que satisfazem esta
		| outra
		| e mais outra
		;

	dentro de uma regra a primeira opção tem maior precedência (útil em expressões matemáticas)
}
*/
