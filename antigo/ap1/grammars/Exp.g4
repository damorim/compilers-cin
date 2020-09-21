grammar Exp;

s : e;
e : e '*' e
  | e '+' e
  | INT
  ;

INT : [0-9]+;
WS : [ \t\r\n]+ -> skip;