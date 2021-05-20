Arquivo com as regras léxicas e sintáticas:

grammar NOMEDESTEARQUIVO;

/*Regras léxicas:*/


TERMINAIS_LITERAIS /*Expressão regular definida para identificar terminais literais*/
    : 'Model-Begin' | 'Model-End' | 'string' | 'int' | 'date' |'True' | 'False' | '=' | ',' | '[' | ']' | '(' | ')' | '.' | '{' | '}' | '-'
    | '+' | '*' | '/' | '%' | '&' | '!=' | '>=' | '<=' | '>' | '<';
    ;

NUM_INT /*Expressão regular definida para reconhecer números inteiros*/
    :('0'..'9')+;

NUM_REAL /*Expressão regular definida para reconhecer números reais*/
    :('0'..'9')+ '.' ('0'..'9')+;

IDENT /*Expressão regular definida para reconhecer identificadores */
    : ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;

CADEIA /*Expressão regular definida para reconhecer expressões literiais*/
    : '"' (ESC_SEQ | ~('"' | '\\') )* '"'; /*Explicação do professor: https://youtu.be/LDRA-VOy2Bs?t=2630*/

fragment /*Fragmento defino como regra auxiliar para definição da expressão regular CADEIA*/
ESC_SEQ
    : '\\\"';

COMENTARIO /* Expressão regular definida para reconhecer e ignorar comentários */
    : '#' ~( '\n' | '\r' )* '}' {skip();};


CARACTERESIGNORADOS /*Expressão regular definida para reconhecer e ignorar espaços em branco e os caracteres '\t', '\r' e '\n'*/
    : ( ' ' | '\t' | '\r' | '\n') { skip(); };

CADEIANFECHADA /*Expressão regular definida para reconhecer cadeias não fechadas.*/
    : '"' (ESC_SEQ | ~('"'))*? '\n';

ErrorCharacter /*Expressão regular definida para reconhecer qualquer caractere que não esteja definido nas outras regras léxicas acima.*/
    : . ;

/*Fim das regras léxicas.*/

/*Regras sintáticas*/

program: model;

model: "Model-Begin" entity* "Model-End";

entity: "Entity-Begin" IDENT field+ "Entity-End";

field: fieldName=IDENT ":" ( tipo_basico | modelname=IDENT) ;

tipo_basico: 'int' | 'string' | 'date';
