grammar JSON;

file   : object ;

// Note: the object and array rules are separated in the ANTLR book's version
// which makes sense. Rework this when I reach that section
object : '{' field (',' field)* '}'
       | '{' '}'
       | INT
       | STRING
       ;

field  : STRING ':' object ;

INT : [0-9]+ ;
STRING : ["A-Za-z0-9 \n\t]+ ;
