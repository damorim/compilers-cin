grammar Hello;

r  : 'hello' 'world';
ID : [a-z]+;
WS : [ \t\r\n]+ -> skip;