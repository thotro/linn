/**
 */
grammar Linn;

@header {
    package linn.grammar;
}

linn : 'linn' name=ID ('(' keyValue ')')? '{' 
		linnRule+ 
	'}' ;
linnRule : name=ID ('--' weight=FLOAT)? '->' 
	(linnProduction+ | '[' {
		System.out.println("Branching");
	} linnProduction+ ']')+ ';' ;
linnProduction : (ID {
		System.out.println($ID.text);
	} | PROD {
		System.out.println($PROD.text);
	})+ ;
params : ID (',' ID)* ;
keyValue : ID '=' ID (',' ID '=' ID)* ;

// general rules
fragment LETTER : [a-zA-Z_] ;
fragment DIGIT : [0-9] ;
fragment INT : DIGIT+ ;
FLOAT : INT ('.' INT)? ;
ID : LETTER (LETTER | DIGIT)* ;
PROD : [+<>\-|\\/&] ;
WS : [ \t\r\n]+ -> skip ;
