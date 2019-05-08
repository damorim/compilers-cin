grammar Cymbol;

//Lexer
fragment NUMBER    : [0-9];
fragment LETTER    : [a-zA-Z];

TYPEINT  : 'int';
TYPEFLOAT  : 'float';
TYPEBOOLEAN : 'boolean';

IF     : 'if';
ELSE   : 'else';
RETURN : 'return';

LP        : '(';
RP        : ')';
COMMA     : ',';
SEMICOLON : ';';
LB        : '{';
RB        : '}';

AS    : '=';
EQ    : '==';
NE    : '!=';
NOT   : '!';
GT    : '>';
LT    : '<';
GE    : '>=';
LE    : '<=';
MUL   : '*';
DIV   : '/';
PLUS  : '+';
MINUS : '-';
AND   : '&&';
OR    : '||';

BOOLEAN:  'true' | 'false';
ID  : (LETTER) (LETTER | NUMBER)*;
INT : NUMBER+;
FLOAT:  NUMBER+ '.' NUMBER+;

BLOCKCOMMENT : '/*' .*? '*/' -> skip;
LINECOMMENT  : '//' .*? '\n' -> skip;
WS           : [ \t\n\r]+ -> skip;

//Parser
fiile : (funcDecl | varDecl)+ EOF?
     ;

varDecl : type ID ('=' expr)? ';'
        ;

tyype : TYPEINT                                  
     | TYPEFLOAT                                 
     | TYPEBOOLEAN                               
     ;

funcDecl : type ID '(' paramTypeList? ')' block
         ;

paramTypeList : paramType (',' paramType)*
              ;

paramType : tyype ID
          ;

block : '{' stat* '}'
      ;

assignStat : ID '=' expr ';'
           ;

returnStat : 'return' expr? ';'
           ;

ifElseStat : ifStat (elseStat)?
           ;

ifElseExprStat : block 
               | ifElseStat
               | returnStat
               | assignStat
               | exprStat
               ;

ifStat : 'if' '(' expr ')' ifElseExprStat
       ;

elseStat : 'else' ifElseExprStat
         ;

exprStat : expr ';'
         ;

exprList : expr (',' expr)* 
         ;

stat : varDecl
     | ifElseStat
     | returnStat
     | assignStat
     | exprStat
     ;

expr : ID '(' exprList? ')'                      
     | op=('+' | '-') expr                       
     | '!' expr                                  
     | expr op=('<' | '>' | '<=' | '>=') expr    
     | expr op=('*' | '/') expr                  
     | expr op=('+' | '-') expr                  
     | expr op=("&&"|"||") expr                  
     | expr op=("=="|"!=") expr                 
     | ID                                        
     | INT                                       
     | FLOAT                                     
     | BOOLEAN                                  
     | '(' expr ')'                             
     ;
