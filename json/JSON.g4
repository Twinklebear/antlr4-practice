grammar JSON;

json   : object
       | array
       ;

// Note: the object and array rules are separated in the ANTLR book's version
// which makes sense. Rework this when I reach that section
object : '{' pair (',' pair)* '}'
       | '{' '}'
       ;

array : '[' value (',' value)* ']'
      | '[' ']'
      ;

pair  : STRING ':' value ;

value : object
      | array
      | 'true'
      | 'false'
      | 'null'
      | NUMBER
      | STRING
      ;

NUMBER : '-'? INT '.' INT EXP? // 1.34, 1.34e-9, 0.3, -4.5
       | '-'? INT EXP          // 1e10 -3e4
       | '-'? INT              // -3, 5
       ;

fragment INT : '0' | [1-9] [0-9]* ; // no leading zeros
fragment EXP : [Ee] [+\-]? INT ; // Note: - without escape means range in the [].
                                 // I think could also write ('+' | '-')?

STRING : '"' (ESC | ~["\\])* '"' ;

fragment ESC : '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;

WS : [ \t\n\r]+ -> skip;

