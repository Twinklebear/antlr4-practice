grammar Cymbol;

file: (functionDecl | varDecl)+ ;

functionDecl: type ID '(' formalParameters? ')' block ;

varDecl: type ID ('=' expr)? ';' ;

formalParameters: formalParameter (',' formalParameter)* ;

formalParameter: type ID ;

block: '{' stat* '}' ;

stat: block
    | varDecl
    | 'if' expr 'then' stat ('else' stat)?
    | 'return' expr? ';'
    | expr '=' expr ';'
    | expr ';'
    ;

// expressions: listed in precedence order from high to low
expr: ID '(' exprList? ')'  # Call
    | expr '[' expr ']'     # Index
    | '-' expr              # Negate
    | '!' expr              # Not
    | expr '*' expr         # Mult
    | expr '/' expr         # Div
    | expr ('+'|'-') expr   # AddSub
    | expr '==' expr        # Equal
    | ID                    # Var
    | INT                   # Int
    | '(' expr ')'          # Parens
    ;

exprList: expr (',' expr)* ;

// TODO: For user-defined types (like structs), I guess this
// would also have to support any valid identifier like type as well
// and the type resolution has to happen later
type: 'float' | 'int' | 'void' ;

ID: [a-zA-Z] [a-zA-Z0-9]* ;
INT: '0' | [1-9] [0-9]*;
WS : [ \t\n\r]+ -> skip;
COMMENT: '//' .*? '\r'? '\n' -> skip;
