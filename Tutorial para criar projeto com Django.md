## 1. Como criar um projeto com o framework Django para uso do compilador ModelGenerator:

* Pré-requisitos:
   * java SDK 11
   * Python
 
* Criar e usar um [ambiente de desenvolvimento](https://developer.mozilla.org/pt-BR/docs/Learn/Server-side/Django/development_environment#usando_django_em_um_ambiente_virtual_python):
  * Exemplo: `mkvirtualenv my_django_env`

* Instalar o framework Django e módulos adicionais para compatibilidade com o compilador:
  * Exemplo: `pip install Django django-allauth django-environ django-filter django-rest-framework djangorestframework mysqlclient`

* Criar um projeto com o nome Trabalho_CC2:
  * Exemplo: `django-admin startproject Trabalho_CC2`

* Acessar o diretório: Trabalho_CC2

* Criar um app com nome game_lib com o framework:
  * `python manage.py startapp game_lib`

* Executar o compilador [ModelGenerator](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/Compilador/ModelGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar) com dois argumentos de entrada (atenção para o segundo, que deve ser o diretório raiz do projeto criado com o Django e os respectivos nomes apresentados):
  * Exemplo: `java -jar C:\ModelGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar C:\Testes_lexico\caso_de_teste_1_ModelGenerator.txt C:\CC2\Trabalho_CC2\`

* Seguir o tutorial deste [link](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/README.md).

