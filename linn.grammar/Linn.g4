/**
 */
grammar Linn;

@header {
    package linn.grammar;
}

// container rule for meta info
linn : 'linn' name=ID ('(' keyValue ')')? '{' 
		linnRule+
		linnInitRule
	'}' 'environment' '{' '}';

// a single L-system rule supporting production, branching, etc.
linnRule : name=ID ('--' weight=FLOAT)? '->' 
	(linnProduction+ | '[' {
		System.out.println("Branching");
	} linnProduction+ ']')+ ';' ;
	
// a single production rule supporting references and L-system actions
linnProduction : (ID {
		System.out.println($ID.text);
	} | PROD {
		System.out.println($PROD.text);
	})+ ;
	
// initial production rule for an L-system definition
linnInitRule : 

// helper rules
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
