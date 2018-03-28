grammar If1;

s : id 
  | iff
  ;
  
id: ID;
iff: IF;

IF: 'if';
ID: [a-z]+;
WS : [ \t\r\n]+ -> skip;