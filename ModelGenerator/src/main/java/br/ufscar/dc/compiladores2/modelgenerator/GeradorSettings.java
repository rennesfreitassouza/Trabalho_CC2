package br.ufscar.dc.compiladores2.modelgenerator;

public class GeradorSettings extends regrasBaseVisitor<Void> {

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorSettings() {
        saida = new StringBuilder();

        saida.append("from pathlib import Path\n");
        saida.append("import environ\n");
        saida.append("\n");

        saida.append("env = environ.Env(DEBUG=(bool, False))\n");
        saida.append("environ.Env.read_env()\n");
        saida.append("BASE_DIR = Path(__file__).resolve().parent.parent\n");
        saida.append("SECRET_KEY = 'test1234'\n");
        saida.append("DEBUG = True\n");
        saida.append("ALLOWED_HOSTS = []\n");
        saida.append("\n");

        saida.append("INSTALLED_APPS = [\n");
        saida.append("\t'django.contrib.admin',\n");
        saida.append("\t'django.contrib.auth',\n");
        saida.append("\t'django.contrib.contenttypes',\n");
        saida.append("\t'django.contrib.sessions',\n");
        saida.append("\t'django.contrib.messages',\n");
        saida.append("\t'django.contrib.staticfiles',\n");
        saida.append("\t'rest_framework',\n");
        saida.append("\t'game_lib.apps.GameLibConfig',\n");
        saida.append("]\n");
        saida.append("\n");

        saida.append("REST_FRAMEWORK = {\n");
        saida.append("\t'DEFAULT_PERMISSION_CLASSES': [\n");
        saida.append("\t\t'rest_framework.permissions.DjangoModelPermissionsOrAnonReadOnly'\n");
        saida.append("\t],\n");
        saida.append("\t'DEFAULT_PAGINATION_CLASS': 'rest_framework.pagination.PageNumberPagination',\n");
        saida.append("\t'PAGE_SIZE': 10,\n");
        saida.append("}\n");
        saida.append("\n");

        saida.append("MIDDLEWARE = [\n");
        saida.append("\t'django.middleware.security.SecurityMiddleware',\n");
        saida.append("\t'django.contrib.sessions.middleware.SessionMiddleware',\n");
        saida.append("\t'django.middleware.common.CommonMiddleware',\n");
        saida.append("\t'django.middleware.csrf.CsrfViewMiddleware',\n");
        saida.append("\t'django.contrib.auth.middleware.AuthenticationMiddleware',\n");
        saida.append("\t'django.contrib.messages.middleware.MessageMiddleware',\n");
        saida.append("\t'django.middleware.clickjacking.XFrameOptionsMiddleware',\n");
        saida.append("]\n");
        saida.append("\n");

        saida.append("ROOT_URLCONF = 'Trabalho_CC2.urls'\n");
        saida.append("\n");

        saida.append("TEMPLATES = [\n");
        saida.append("\t{\n");
        saida.append("\t\t'BACKEND': 'django.template.backends.django.DjangoTemplates',\n");
        saida.append("\t\t'DIRS': [],\n");
        saida.append("\t\t'APP_DIRS': True,\n");
        saida.append("\t\t'OPTIONS': {\n");
        saida.append("\t\t\t'context_processors': [\n");
        saida.append("\t\t\t\t'django.template.context_processors.debug',\n");
        saida.append("\t\t\t\t'django.template.context_processors.request',\n");
        saida.append("\t\t\t\t'django.contrib.auth.context_processors.auth',\n");
        saida.append("\t\t\t\t'django.contrib.messages.context_processors.messages',\n");
        saida.append("\t\t\t],\n");
        saida.append("\t\t},\n");
        saida.append("\t},\n");
        saida.append("]\n");
        saida.append("\n");

        saida.append("WSGI_APPLICATION = 'Trabalho_CC2.wsgi.application'\n");
        saida.append("\n");

        saida.append("DATABASES = {\n");
        saida.append("\t'default': {\n");
        saida.append("\t\t'ENGINE': 'django.db.backends.mysql',\n");
        saida.append("\t\t'NAME': env.str('TRABALHO_CC2_DB'),\n");
        saida.append("\t\t'USER': env.str('TRABALHO_CC2_USER'),\n");
        saida.append("\t\t'PASSWORD': env.str('TRABALHO_CC2_PASS'),\n");
        saida.append("\t\t'HOST': env.str('TRABALHO_CC2_HOST'),\n");
        saida.append("\t\t'PORT': env.str('TRABALHO_CC2_PORT')\n");
        saida.append("\t}\n");
        saida.append("}\n");
        saida.append("\n");

        saida.append("AUTH_PASSWORD_VALIDATORS = [\n");
        saida.append("\t{\n");
        saida.append("\t\t'NAME': 'django.contrib.auth.password_validation.UserAttributeSimilarityValidator',\n");
        saida.append("\t},\n");
        saida.append("\t{\n");
        saida.append("\t\t'NAME': 'django.contrib.auth.password_validation.MinimumLengthValidator',\n");
        saida.append("\t},\n");
        saida.append("\t{\n");
        saida.append("\t\t'NAME': 'django.contrib.auth.password_validation.CommonPasswordValidator',\n");
        saida.append("\t},\n");
        saida.append("\t{\n");
        saida.append("\t\t'NAME': 'django.contrib.auth.password_validation.NumericPasswordValidator',\n");
        saida.append("\t},\n");
        saida.append("]\n");
        saida.append("\n");

        saida.append("LANGUAGE_CODE = 'en-us'\n");
        saida.append("TIME_ZONE = 'UTC'\n");
        saida.append("USE_I18N = True\n");
        saida.append("USE_L10N = True\n");
        saida.append("USE_TZ = True\n");
        saida.append("STATIC_URL = '/static/'\n");
        saida.append("DEFAULT_AUTO_FIELD = 'django.db.models.BigAutoField'\n");
    }
}
