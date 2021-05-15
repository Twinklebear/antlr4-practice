grammar Arithmetic;

prog: stat+ ;

stat: expr NEWLINE             # printExpr
    | ID '=' expr NEWLINE      # assign
    | NEWLINE                  # blank
    ;

// Setting the op = part instructs ANTLR to create a
// variable in the context with the label name (e.g., MulDiv)
// whose value holds that token/item
expr: expr op=('*'|'/') expr   # MulDiv
    | expr op=('+'|'-') expr   # AddSub
    | INT                      # int
    | ID                       # id
    | '(' expr ')'             # parens
    ;

ID  : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS  : [ \t]+ -> skip ;
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
