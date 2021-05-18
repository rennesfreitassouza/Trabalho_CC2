<<<<<<< HEAD
##Tutorial para execução deste projeto:

Instalar:
-Server version: 8.0.25 MySQL Community Server - GPL
-Python 3.8.7

-Clonar repositório

-Navegar até o diretório Trabalho_CC2 via linha de comando

-Executar o comando: pip install -r requirements.txt
-Renomear o arquivo Trabalho_CC2\Trabalho_CC2\.env.example para .env
-Instanciar os valores de acordo com os que o seu banco de dados MySql foi criado

-Executar os comandos:
-python manage.py makemigrations game_lib
-python manage.py migrate
-python manage.py createsuperuser

-python manage.py runserver 8000

-Abrir o navegador de colar o endereço http://127.0.0.1:8000/api-auth/login/
-Entrar

-Acessar: http://127.0.0.1:8000/game_lib/





=======
## Tutorial para execução deste projeto:

- Instalar: Server version: 8.0.25 MySQL Community Server e Python 3.8.7

- Clonar repositório

- Navegar até o diretório Trabalho_CC2 via linha de comando

- Executar o comando  em um ambiente virtual:
  - pip install -r requirements.txt

- Renomear o arquivo Trabalho_CC2\Trabalho_CC2\.env.example para .env

- Instanciar os valores de acordo com os que o seu banco de dados MySql que deve ser criado

- Executar os comandos:
  - python manage.py makemigrations game_lib
  - python manage.py migrate
  - python manage.py createsuperuser

  - python manage.py runserver 8000

- Abrir o navegador de colar o endereço http://127.0.0.1:8000/api-auth/login/
- Entrar
- Acessar: http://127.0.0.1:8000/game_lib/
>>>>>>> c68f745fdc41b76c71b97fa9657fd6fbe17615e4
