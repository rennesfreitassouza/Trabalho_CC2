# 1. README.md

## 1.1 Como criar um projeto com o framework Django para uso do compilador ModelGenerator:

* Requisitos para executar o compilador ModelGenerator e para criar e rodar o projeto Django Trabalho_CC2:
   * [java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html#license-lightbox)
   * [Python Interpreter](https://www.python.org/downloads/)
   * [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) 
     * (Durante a instalação do MySQL Community Server, definir usuário e senha para criar databases (ou schemas) locais).
 
* Criar e usar um [ambiente de desenvolvimento](https://developer.mozilla.org/pt-BR/docs/Learn/Server-side/Django/development_environment#usando_django_em_um_ambiente_virtual_python):
  * Exemplos:
    * `mkvirtualenv my_django_env` (para criar o ambiente).
    * `workon my_django_env` (para usar o ambiente).

* Instalar o framework Django e alguns módulos adicionais (para compatibilidade com os arquivos criados a partir do compilador ModelGenerator) nesse ambiente:
  * Exemplo: `pip install Django django-allauth django-environ django-filter django-rest-framework djangorestframework mysqlclient`

* Criar um projeto Django com o nome Trabalho_CC2:
  * Exemplo: `django-admin startproject Trabalho_CC2`

* Acessar o diretório Trabalho_CC2 criado.

* [Criar um app](https://docs.djangoproject.com/en/3.2/intro/tutorial01/#creating-the-polls-app) com nome game_lib para esse projeto:
  * `python manage.py startapp game_lib`

* Executar o compilador [ModelGenerator](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/Compilador/ModelGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar) a partir do java. Os argumentos de entrada para o ModelGenerator devem ser dois: o primero deve ser o caminho para [um arquivo de texto contendo código em ModelGenerator sem erros](https://raw.githubusercontent.com/rennesfreitassouza/Trabalho_CC2/main/Casos_de_teste/Lexico/caso_de_teste_1.txt) (para gerar arquivos válidos para o projeto Django Trabalho_CC2) e o segundo argumento que deve ser o caminho para o diretório raiz do projeto Django Trabalho_CC2 criado de acordo com este tutorial:
  * Exemplo: `java -jar C:\ModelGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar C:\Testes_lexico\caso_de_teste_1_ModelGenerator.txt C:\CC2\Trabalho_CC2\`

## 1.2 Tutorial para execução do projeto Django Trabalho_CC2:

- Seguir a primeira parte deste tutorial.

- Navegar até o diretório raiz do projeto Django Trabalho_CC2 via linha de comando.

- Renomear o arquivo Trabalho_CC2\Trabalho_CC2\.env.example (criado a partir da execução do ModelGenerator) para .env

- Criar um SCHEMA com o SQL shell MySQL:
  - Abrir o MySQL 8.0 Command Line Client.
  - Executar o comando neste shell:
    - `CREATE DATABASE TESTE;`

- Retornar ao arquivo Trabalho_CC2\Trabalho_CC2\.env e escrever os valores para as variáveis apresentadas de acordo com os valores do seu schema MySql.

- Então, executar os comandos na raiz do projeto Django Trabalho_CC2:
  - python manage.py makemigrations game_lib
  - python manage.py migrate
  - python manage.py createsuperuser (definir valores)

- Executar o projeto Django:
  - python manage.py runserver 8000
- Acessar o endereço: http://127.0.0.1:8000/api-auth/login/
- Entrar com os valores definidos para usuário e senha depois da execução do comando `manage.py createsuperuser`.
- (opcional):
  - Acessar: http://127.0.0.1:8000/game_lib/
  - Acessar: http://127.0.0.1:8000/game_lib/Games/ e inserir valores a partir de requisições POST.
  - Acessar: http://127.0.0.1:8000/game_lib/Platforms/ e inserir valores a partir de requisições POST.
  - Acessar: http://127.0.0.1:8000/game_lib/Releases/ e inserir valores a partir de requisições POST.
  - Verificar se os valores inseridos estão no banco de dados MySql apontado pelas variáveis definidas no arquivo .env:
    - Exemplo: no SQL shell MySQL, executar os comandos:
      - `USE DATABASE TESTE;`
      - `SHOW TABLES;`
      - `SELECT * FROM game_lib_games;`

