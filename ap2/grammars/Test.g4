grammar Test;

stat : ((attrStmt | expr) ';')+ EOF?		#Expressions
     ;

attrStmt: ID '=' expr						#Assign
        ;

expr : expr op=('*'|'/') expr				#MultDiv
     | expr op=('+'|'-') expr				#AddSub
     | INT									#Int
     | ID									#Identifier
     | '(' expr ')'							#Paren
     ;

//Fragmentos (Não constituem tokens por si só)
fragment NUMBER: [0-9];
fragment LETTER: [a-zA-Z];
fragment UNDERLINE: '_';

//Tokens
INT : NUMBER+ ;
ID : (UNDERLINE | LETTER) (UNDERLINE | LETTER | NUMBER)*;

//Tokens de operações aritméticas
MUL : '*';
DIV : '/';
ADD : '+';
SUB : '-';

BLOCKCOMMENT : '/*' .*? '*/' -> skip;
LINECOMMENT : '//' .*? '\n' -> skip;
WS : [ \t\r\n]+ -> skip;