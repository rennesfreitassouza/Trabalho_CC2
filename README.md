## 1. Exemplo de algoritmo para a criação de uma biblioteca de jogos que será utilizado neste tutorial.
```
Config-Begin
	SITE: games
	APP: gamelib
Config-End

Model-Begin	
	Entity-Begin Games
		name: string(max_length=255, unique)
		publisher: string(max_length=255, unique)
	Entity-End

	Entity-Begin Platforms
		name: string(max_length=255, unique)
	Entity-End

	Entity-Begin Releases
		game: Games
		platform: Platforms
		releasedate: date
		version: int
	Entity-End
Model-End

Env-Begin
	DB: gamelib_schema
	USER: root
	PASS: test1234
Env-End
```

## 2. Como criar um projeto com o framework Django para uso do compilador ModelGenerator

* Requisitos para executar o compilador ModelGenerator e para criar e rodar seu projeto Django:
  * Algum conhecimento em programação e banco de dados.
  * [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html#license-lightbox)
  * [Python Interpreter](https://www.python.org/downloads/)
  * [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) (durante a instalação do MySQL Community Server, definir um usuário e uma senha para criar databases (ou schemas) locais).
 
* Criar e usar um [ambiente de desenvolvimento](https://developer.mozilla.org/pt-BR/docs/Learn/Server-side/Django/development_environment#usando_django_em_um_ambiente_virtual_python):
  * Exemplos:
    * `mkvirtualenv my_django_env` (para criar o ambiente).
    * `workon my_django_env` (para usar o ambiente).

* Instalar o framework Django e outros módulos adicionais (para compatibilidade com os arquivos criados a partir do compilador ModelGenerator) nesse ambiente:
  * Exemplo:
    * `pip install Django django-allauth django-environ django-filter django-rest-framework djangorestframework mysqlclient`

* Criar um projeto Django. O nome dele deve ser igual ao que será utilizado em seu código escrito em ModelGenerator na definição do valor para `SITE`, presente no início do algoritmo deste exemplo. Aqui, o valor `games` foi utilizado:
  * Exemplo:
    * `django-admin startproject games`
    * `django-admin startproject escola`

* Acessar o diretório com o nome do projeto que acabou de ser criado. Para este exemplo, acessar a pasta `games` via linha de comando.

* [Criar um app](https://docs.djangoproject.com/en/3.2/intro/tutorial01/#creating-the-polls-app) para seu projeto Django. O nome dele deve ser igual ao valor que será definido no código em ModelGenerator para `APP`. Neste exemplo, o valor `gamelib` foi utilizado:
    * `python manage.py startapp gamelib`
    * `python manage.py startapp escolalib`

* Escrever seu código na linguagem ModelGenerator, como nos exemplos armazenados no diretório `Exemplos/` deste repositório. Para este tutorial, foi utilizado o código de [gamelib.txt](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/Exemplos/gamelib.txt). Realizar as modificações necessárias de acordo com suas Entidades.

* Executar o compilador [ModelGenerator](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/ModelGenerator.jar) a partir do java. Os argumentos de entrada para o ModelGenerator devem ser dois: o primeiro deve ser o caminho para [um arquivo de texto contendo código em ModelGenerator sem erros](https://github.com/rennesfreitassouza/Trabalho_CC2/blob/main/Exemplos/gamelib.txt) (para o compilador gerar arquivos válidos para o projeto Django) e o segundo argumento que deve ser o caminho para o diretório raiz do seu projeto Django criado de acordo com este tutorial:
   * Exemplo:
     * `java -jar ModelGenerator.jar Exemplos\gamelib.txt Exemplos\games\` (relative path)
     * `java -jar C:\Users\Usuário\.m2\repository\br\ufscar\dc\compiladores2\ModelGenerator\1.0-SNAPSHOT\ModelGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar C:\Trabalho_CC2\Exemplos\escola.txt C:\Trabalho_CC2\Exemplos\escola\` (full path)

## 3. Tutorial para execução do projeto Django

- Criar um SCHEMA com, por exemplo, o SQL shell MySQL:
    - Executar o comando neste shell:
    - `CREATE DATABASE gamelib_schema;`

- Navegar até o diretório do seu app Django e editar o arquivo `.env`.

- Para cada variável apresentada, trocar os valores padrão por aqueles definidos para o seu banco de dados MySQL.

- Executar os seguintes comandos na raiz do projeto Django. Neste exemplo, os comandos foram escritos para o mesmo projeto Django games:
  - `python manage.py makemigrations gamelib` ou `python manage.py makemigrations escolalib`
  - `python manage.py migrate`
  - `python manage.py createsuperuser` (definir valores)

- Executar o projeto Django:
  - `python manage.py runserver 8000`

- Então, acessar o endereço: http://127.0.0.1:8000/api-auth/login/
- Realizar a autenticação com os valores definidos para seu usuário no ambiente depois da execução do comando `manage.py createsuperuser`.
- (opcional):
  - Acessar: `http://127.0.0.1:8000/gamelib/` ou `http://127.0.0.1:8000/ + nome do seu app`.
  - Acessar: `http://127.0.0.1:8000/gamelib/Games/`  ou `http://127.0.0.1:8000/ + nome do seu app + nome da sua entidade`. Inserir valores no banco de dados local a partir de requisições POST.
  - Verificar se os valores inseridos estão no banco de dados MySql apontado pelas variáveis definidas no código em `.env`:
    - Exemplo: no SQL shell MySQL, executar os comandos:
      - `USE DATABASE gamelib_schema;`
      - `SHOW TABLES;`
      - `SELECT * FROM gamelib_games;`
