grammar CSV;

file : (row '\n')* ;
row  : field (',' field)* ;
field: ITEM;

ITEM : [A-Za-z0-9 ]+ ;
