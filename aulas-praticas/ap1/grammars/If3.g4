grammar If3;

s : id 
  ;
  
id: ID;

IF: 'if';
ID: [a-z]+;
WS : [ \t\r\n]+ -> skip;