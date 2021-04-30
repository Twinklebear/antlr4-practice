grammar ArrayInit;

// Match sets of { value }, with potentially nested { value } sets
init : '{' value (',' value)* '}' ;

value : init
      | INT
      ;

// Parser rules start with lower case, lexer rules with uppercase
INT : [0-9]+;
WS  : [ \t\r\n]+ -> skip; // skip whitespace

