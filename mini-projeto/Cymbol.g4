grammar Cymbol;

//Lexer
fragment NUMBER    : [0-9];
fragment LETTER    : [a-zA-Z];

TYPEINT  : 'int';

RETURN : 'return';

LP        : '(';
RP        : ')';
COMMA     : ',';
SEMICOLON : ';';
LB        : '{';
RB        : '}';

AS    : '=';
MUL   : '*';
DIV   : '/';
PLUS  : '+';
MINUS : '-';

ID  : (LETTER) (LETTER | NUMBER)*;
INT : NUMBER+;

BLOCKCOMMENT : '/*' .*? '*/' -> skip;
LINECOMMENT  : '//' .*? '\n' -> skip;
WS           : [ \t\n\r]+ -> skip;



//Parser
file : (funcDecl | varDecl)+ EOF?
     ;

varDecl : type ID ('=' expr)? ';'
        ;

type : TYPEINT                                   #FormTypeInt
     ;

funcDecl : type ID '(' paramTypeList? ')' block
         ;

paramTypeList : paramType (',' paramType)*
              ;

paramType : type ID
          ;

block : '{' stat* '}'
      ;

assignStat : ID '=' expr ';'
           ;

returnStat : 'return' expr? ';'
           ;

exprStat : expr ';'
         ;

exprList : expr (',' expr)* 
         ;

stat : varDecl
     | returnStat
     | assignStat
     | exprStat
     ;

expr : ID '(' exprList? ')'                      #FunctionCallExpr
     | op=('+' | '-') expr                       #SignedExpr
     | expr op=('*' | '/') expr                  #MulDivExpr
     | expr op=('+' | '-') expr                  #AddSubExpr
     | ID                                        #VarIdExpr
     | INT                                       #IntExpr
     | '(' expr ')'                              #ParenExpr
     ;
