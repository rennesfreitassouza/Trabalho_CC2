package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.Token;

public class GeradorAppUrls extends regrasBaseVisitor<Void> {

    String site;
    String app;

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorAppUrls(String s, String p) {
        site = s;
        app = p;
        saida = new StringBuilder();
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        saida.append("from django.urls import include, path\n");
        saida.append("from rest_framework import routers\n");
        saida.append("from .views import *\n");
        saida.append("\n");

        saida.append("router = routers.DefaultRouter()\n");

        visitModel(ctx.model());

        saida.append("\n");
        saida.append("urlpatterns = [\n");
        saida.append("\tpath('', include(router.urls)),\n");
        saida.append("\tpath('api-auth/', include('rest_framework.urls', namespace='env'))\n");
        saida.append("]\n");

        return null;
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENTIFICADOR().getSymbol();
        String entityNome = entityToken.getText();

        saida.append("router.register(r'" + entityNome + "', " + entityNome + "ViewSet)\n");

        return null;
    }
}
