grammar regras;

/*Regras léxicas:*/

TERMINAIS_LITERAIS /*Expressão regular definida para identificar terminais literais*/
    : 'Model-Begin' | 'Model-End' | 'Serializer-Begin' | 'Serializer-End'
    | 'Class-Begin' | 'class Meta' | 'class-end' | 'Class-End' | 'View-Begin'
    | 'View-End' | 'string' | 'int' | 'date' |'True' | 'False' | '=' | ','
    | '[' | ']' | '(' | ')' | '.' | '{' | '}' | '-'
    | '+' | '*' | '/' | '%' | '&' | '!=' | '>=' | '<=' | '>' | '<'
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
    : '\\"';

COMENTARIO /* Expressão regular definida para reconhecer e ignorar comentários */
    : '#' ~('\n'|'\r')* ('\r')* '\n' {skip();}; /*Somente são permitidos comentários de um linha e terminados com '\n'.*/


CARACTERESIGNORADOS /*Expressão regular definida para reconhecer e ignorar espaços em branco e os caracteres '\t', '\r' e '\n'*/
    : ( ' ' | '\t' | '\r' | '\n') { skip(); };

CADEIANFECHADA /*Expressão regular definida para reconhecer cadeias não fechadas.*/
    : '"' (ESC_SEQ | ~('"'))*? '\n';

ErrorCharacter /*Expressão regular definida para reconhecer qualquer caractere que não esteja definido nas outras regras léxicas acima.*/
    : . ;

/*Fim das regras léxicas.*/

/*Regras sintáticas*/

program
    : model serializers? views? fim_de_arquivo /**/
    ;

model
    : 'Model-Begin' imports* entity* 'Model-End'   
    ;

serializers
    : ('Serializer-Begin' imports* classes*  'Serializer-End')
    ;

views
    : 'View-Begin' imports* classes* 'View-End'
    ;

imports
    : 'import' ( modules ','? )+
    ;

modules
    : 'models' | '.models' | 'rest_framework' | 'HttpResponse' | 'viewsets'
    | 'authentication' | 'permissions' | 'ModelViewSet' | 'Response'
    | '.serializers' | 'json'
    ;

entity
    : 'Entity-Begin' IDENT field+ 'Entity-End'
    ;

classes
    : 'Class-Begin' IDENT (serializer_classes | view_classes)
    | 'Class-End'
    ;

field /*Em desenvolv.*/
    : fieldName=IDENT ':' ( tipo_basico | otherModelName=IDENT) ( '('
     ( params TL=','? )* ')' )* 
    ;

tipo_basico
    : 'int' | 'date' | 'string'  
    ;

params
    : (parCharFieldML='max_length' parCharFieldTL='=' NUM_INT)
    | (parFieldUnique='unique' parFieldTL='=' parFieldBoolean='True')
    ;

serializer_classes
    : class_Meta+
    ;

view_classes
    : class_definitions
    ;

class_Meta
    : 'class_Meta' ':' inner_validators* 'class_M_end'
    ;

inner_validators
    : (model_attr | field_attr)
    ;

model_attr
    : 'model' ':' IDENT
    ;

field_attr
    : 'fields' ':' 'all'
    ;

class_definitions
    : (rest_view_definitions)+
    ;

rest_view_definitions
    : ('queryset' ':' django_querys)
    | ('serializer_class' ':' serializer_name=IDENT)
    | ('permission_classes' ':' '[' permissions ']')
    ;

django_querys
    : (model_name=IDENT '.' 'objects' '.' 'all')
    ;

permissions
    : 'perm.IsAuth'
    ;
fim_de_arquivo
    :   EOF
    ;
