grammar AnnotExp;

s : e;
e : e MUL e		#MulExp
  | e ADD e		#AddExp
  | INT			#Int
  ;

MUL : '*';
ADD : '+';
INT : [0-9]+;
WS : [ \t\r\n]+ -> skip;