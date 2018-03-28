grammar If2;

s : iff 
  | id
  ;
  
id: ID;
iff: IF;

ID: [a-z]+;
IF: 'if';
WS : [ \t\r\n]+ -> skip;