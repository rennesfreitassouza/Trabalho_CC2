## Tutorial para execução do projeto em Trabalho_CC2\\:

- Instalar: Server version: 8.0.25 MySQL Community Server e Python 3.8.x

- Clonar este repositório (opcional)

- Navegar até o diretório Trabalho_CC2 via linha de comando

- Executar o comando em um ambiente virtual:
  - pip install -r requirements.txt (opcional)

- Renomear o arquivo Trabalho_CC2\Trabalho_CC2\.env.example para .env

- Instanciar os valores de acordo com os que o seu banco de dados MySql que deve ser criado

- Executar os comandos:
  - python manage.py makemigrations game_lib
  - python manage.py migrate
  - python manage.py createsuperuser

  - python manage.py runserver 8000

- Acessar o endereço: http://127.0.0.1:8000/api-auth/login/
- Entrar
- (opcional):
  - Acessar: http://127.0.0.1:8000/game_lib/
  - Acessar: http://127.0.0.1:8000/game_lib/Games/ e inserir valores
  - Acessar: http://127.0.0.1:8000/game_lib/Platforms/ e inserir valores
  - Acessar: http://127.0.0.1:8000/game_lib/Releases/ e inserir valores
  - Verificar se os valores inseridos estão no banco de dados MySql.
  -
