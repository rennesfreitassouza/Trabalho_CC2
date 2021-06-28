package br.ufscar.dc.compiladores2.modelgenerator;

public class GeradorDefaultUrls extends regrasBaseVisitor<Void> {

    String site;
    String app;

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorDefaultUrls(String s, String p) {
        site = s;
        app = p;
        saida = new StringBuilder();
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        saida.append("from django.contrib import admin\n");
        saida.append("from django.urls import include, path\n");
        saida.append("from rest_framework import routers\n");
        saida.append("\n");

        saida.append("urlpatterns = [\n");
        saida.append("\tpath('" + app + "/', include('" + app + ".urls')),\n");
        saida.append("\tpath('admin/', admin.site.urls),\n");
        saida.append("\tpath('api-auth/', include('rest_framework.urls', namespace='rest_framework'))\n");
        saida.append("]\n");

        return null;
    }
}
