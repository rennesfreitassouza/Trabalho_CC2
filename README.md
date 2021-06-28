## 1. Como criar um projeto com o framework Django para uso do compilador ModelGenerator

* Requisitos para executar o compilador ModelGenerator e para criar e rodar o projeto:
   * [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html#license-lightbox)
   * [Python Interpreter](https://www.python.org/downloads/)
   * [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) (durante a instalação do MySQL Community Server, definir um usuário e uma senha para criar databases (ou schemas) locais).
 
* Criar e usar um [ambiente de desenvolvimento](https://developer.mozilla.org/pt-BR/docs/Learn/Server-side/Django/development_environment#usando_django_em_um_ambiente_virtual_python):
  * Exemplos:
    * `mkvirtualenv my_django_env` (para criar o ambiente).
    * `workon my_django_env` (para usar o ambiente).

* Instalar o framework Django e alguns módulos adicionais (para compatibilidade com os arquivos criados a partir do compilador ModelGenerator) nesse ambiente:
  * Exemplo:
    * `pip install Django django-allauth django-environ django-filter django-rest-framework djangorestframework mysqlclient`

* Criar um projeto Django com o mesmo nome definido no código utilizando a palavra-chave `SITE` (`games` para esse exemplo):
  * Exemplo:
    * `django-admin startproject games`

* Acessar o diretório `games` criado.

* [Criar um app](https://docs.djangoproject.com/en/3.2/intro/tutorial01/#creating-the-polls-app) com o mesmo nome definido no código utilizando a palavra-chave `APP` (`gamelib` para esse exemplo):
    * `python manage.py startapp gamelib`

* Executar o compilador [ModelGenerator](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/ModelGenerator.jar) a partir do java. Os argumentos de entrada para o ModelGenerator devem ser dois: o primero deve ser o caminho para [um arquivo de texto contendo código em ModelGenerator sem erros](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/Exemplos/gamelib.txt) (para gerar arquivos válidos para o projeto Django) e o segundo argumento que deve ser o caminho para o diretório raiz do projeto Django criado de acordo com este tutorial:
   * Exemplo:
     * `java -jar ModelGenerator.jar Exemplos\gamelib.txt Exemplos\games\`

## 2. Tutorial para execução do projeto Django

- Navegar até o diretório raiz do projeto Django via linha de comando.

- Criar um SCHEMA com o SQL shell MySQL utilizando as mesmas configurações definidas no código em `Env`:
  - Exemplo:  
    - Abrir o MySQL 8.0 Command Line Client.
    - Executar o comando neste shell:
    - `CREATE DATABASE gamelib_schema;`

- Executar os comandos na raiz do projeto Django:
  - python manage.py makemigrations gamelib
  - python manage.py migrate
  - python manage.py createsuperuser (definir valores)

- Executar o projeto Django:
  - python manage.py runserver 8000
- Acessar o endereço: http://127.0.0.1:8000/api-auth/login/
- Entrar com os valores definidos para usuário e senha depois da execução do comando `manage.py createsuperuser`.
- (opcional):
  - Acessar: http://127.0.0.1:8000/gamelib/
  - Acessar: http://127.0.0.1:8000/gamelib/Games/ e inserir valores a partir de requisições POST.
  - Acessar: http://127.0.0.1:8000/gamelib/Platforms/ e inserir valores a partir de requisições POST.
  - Acessar: http://127.0.0.1:8000/gamelib/Releases/ e inserir valores a partir de requisições POST.
  - Verificar se os valores inseridos estão no banco de dados MySql apontado pelas variáveis definidas no código em `Env`:
    - Exemplo: no SQL shell MySQL, executar os comandos:
      - `USE DATABASE gamelib_schema;`
      - `SHOW TABLES;`
      - `SELECT * FROM gamelib_games;`

