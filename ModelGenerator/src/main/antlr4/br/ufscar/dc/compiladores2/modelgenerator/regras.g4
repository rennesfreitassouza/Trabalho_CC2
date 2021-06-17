grammar regras;

/*Regras léxicas:*/

TERMINAIS_LITERAIS /*Expressão regular definida para identificar terminais literais*/
    : 'Model-Begin' | 'Model-End' | 'Serializer-Begin' | 'Serializer-End'
    | 'Class-Begin' | 'class Meta' | 'class-end' | 'Class-End' | 'View-Begin'
    | 'View-End' | 'AppUrls-Begin' | 'AppUrlConf-Begin' | 'AppUrlConf-End' 
    | 'AppUrls-End' | 'urlpatterns-Begin' | 'urlpatterns-End' | 'Env-Begin'
    | 'Env-End' | 'Settings-Begin' | 'EnvSetup-Begin' | 'EnvSetup-End' 
    | 'AddVariables-Begin' | 'AddVariables-End' | 'Settings-End' 
    | 'DefaultUrls-Begin' | 'DefaultUrls-End' |'string' | 'int'
    | 'date' |'True' | 'False' | '=' | ',' | '[' | ']' | '(' | ')'
    | '.' | '{' | '}' | '-' | '+' | '*' | '/' | '%' | '&' | '!=' | '>='
    | '<=' | '>' | '<'
    ;

NUM_INT /*Expressão regular definida para reconhecer números inteiros*/
    :('0'..'9')+;

NUM_REAL /*Expressão regular definida para reconhecer números reais*/
    :('0'..'9')+ '.' ('0'..'9')+;

IDENT /*Expressão regular definida para reconhecer identificadores */
    : ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;

CADEIA /*Expressão regular definida para reconhecer expressões literiais*/
    : '"' (ESC_SEQ | ~('"' | '\\') )* '"';


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
    : model serializers? views? appurls? env? settings? defaulturls? fim_de_arquivo /**/
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

appurls
    : 'AppUrls-Begin' imports* 'AppUrlConf-Begin' router_attrs 'AppUrlConf-End' 'urlpatterns-Begin' paths 'urlpatterns-End' 'AppUrls-End'
    ;

env
    : 'Env-Begin' 'DEBUG' ':' 'on' environment_vars+ 'Env-End'
    ;

settings
    : 'Settings-Begin' imports* 'EnvSetup-Begin' environ_attr environ_call 'EnvSetup-End'
    'AddVariables-Begin' settings_vars 'AddVariables-End' 'Settings-End'
    ;

defaulturls
    : 'DefaultUrls-Begin' imports* 'urlpatterns-Begin' paths 'urlpatterns-End' 'DefaultUrls-End'
    ;

environ_attr
    : IDENT ':' 'environ.Env' '(' 'DEBUG=' '(' 'bool' ',' ('False' | 'True') ')' ')'
    ;

environ_call
    : 'environ.Env.read_env()'
    ;

settings_vars /**/
    : 'SECRET_KEY' ':' '\'' IDENT '\''
    'INSTALLED_APPS' ':' '[' '\'' IDENT '\'' (',' '\'' IDENT '\'' )*  ']' 
    'REST_FRAMEWORK' ':' '{' '\'' 'DEFAULT_PERMISSION_CLASSES' '\'' ':' '[' ('\'' IDENT '\'')+ ']' ',' '\'' 'DEFAULT_PAGINATION_CLASS' '\'' ':' '\'' 'rest_framework_pagination_PageNumberPagination' '\'' ',' '\'' 'PAGE_SIZE' '\'' ':' NUM_INT ',' '}' databases
    ;

databases
    : 'DATABASES' ':' '{' '\'' 'default' '\'' ':' '{' '\'' 'ENGINE' '\'' ':' eng=IDENT ',' '\'' 'NAME' '\'' ':'	name=IDENT ',' '\'' 'USER' '\'' ':'	user=IDENT ',' '\'' 'PASSWORD' '\'' ':' pass=IDENT ',' '\'' 'HOST' '\'' ':' host=IDENT ',' '\'' 'PORT' '\'' ':' IDENT '}' '}'
    ;

environment_vars
    : var_name=IDENT ':' (IDENT | NUM_INT)
    ;

imports
    : 'import' ( modules ','? )+
    ;

modules
    : 'models' | '.models' | 'rest_framework' | 'HttpResponse' | 'viewsets'
    | 'authentication' | 'permissions' | 'ModelViewSet' | 'Response'
    | '.serializers' | 'json' | 'include' | 'path' | 'routers' | '.views'
    | 'Path' | 'environ' | 'admin' 
    ;

entity
    : 'Entity-Begin' IDENT field+ 'Entity-End'
    ;

classes
    : 'Class-Begin' IDENT (serializer_classes | view_classes)
    | 'Class-End'
    ;

router_attrs
    : router_default? router_reg+
    ;

router_default
    : 'router' ':' 'routers.DefaultRouter()'
    ;

router_reg
    : 'router.reg' '(' '\'' IDENT  '\'' ',' view_name=IDENT ')'
    ;

paths
    : (path_call (',' path_call)* )+
    ;

path_call
    : 'path' '(' path_params ')'
    ;

path_params
    : '\'' str=IDENT '\'' ',' parametros_ident
    ;

parametros_ident
    : (IDENT | include_class)
    ;
include_class
    : 'include' '(' '\''?  IDENT '\''? (',' 'namespace=' '\'' IDENT '\'' )? ')'
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
