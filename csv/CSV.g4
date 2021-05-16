grammar CSV;

file : (row '\r'? '\n')* ;
row  : field (',' field)* ;
field: ITEM;

ITEM : ~[,\n\r"]+ ;
