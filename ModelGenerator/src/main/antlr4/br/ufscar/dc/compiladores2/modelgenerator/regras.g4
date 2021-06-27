grammar regras;


/* _____Análise Léxica_____ */

/* O analisador deve ignorar os seguintes símbolos */
COMENTARIO
:   '#' ~('\n')* '\n' -> skip
;
ESPACO
:   (' ' | '\t' | '\n') -> skip
;

/* Expressão regular definida para reconhecer números inteiros */
NUMERO
:   ('0'..'9')+
;

/* Expressão regular definida para reconhecer identificadores */
IDENTIFICADOR
:   ('a'..'z' | 'A'..'W' | 'Y'..'Z') ('a'..'z' | 'A'..'W' | 'Y'..'Z' | '0'..'9')*
;


/** _____Captura de Erros Léxicos_____ */

/** Expressão regular definida para reconhecer qualquer caractere que não esteja definido nas regras léxicas acima. */
ERRO_SIMBOLO
:   .
;


/* _____Análise Sintática_____ */

program
:   model database EOF
;

model
:   'Model-Begin' entity* 'Model-End'
;

entity
:   'Entity-Begin' IDENTIFICADOR field* 'Entity-End'
;

field
:   IDENTIFICADOR ':' ( tipo_basico | IDENTIFICADOR) ( '(' parameter (',' parameter)* ')' )?
;

tipo_basico
:   'int' | 'string' | 'date'
;

parameter
:   ('max_length' '=' NUMERO)
|   (unique='unique')
;

database
:   'Env-Begin' db_config* 'Env-End'
;

db_config
:   environment ':' (IDENTIFICADOR | NUMERO)
;

environment
:   'HOST'
|   'PORT'
|   'DB'
|   'USER'
|   'PASS'
;