package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.Token;

public class GeradorVIews extends regrasBaseVisitor<Void> {

    String site;
    String app;

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorVIews(String s, String p) {
        site = s;
        app = p;
        saida = new StringBuilder();
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        saida.append("from django.http import HttpResponse\n");
        saida.append("from rest_framework import viewsets\n");
        saida.append("from rest_framework.viewsets import ModelViewSet\n");
        saida.append("from rest_framework.response import Response\n");
        saida.append("from rest_framework import authentication, permissions\n");
        saida.append("from .models import *\n");
        saida.append("from .serializers import *\n");
        saida.append("import json\n");
        saida.append("\n");

        visitModel(ctx.model());

        return null;
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENTIFICADOR().getSymbol();
        String entityNome = entityToken.getText();

        saida.append("class " + entityNome + "ViewSet(viewsets.ModelViewSet):\n");
        saida.append("\tqueryset = " + entityNome + ".objects.all()\n");
        saida.append("\tserializer_class = " + entityNome + "Serializer\n");
        saida.append("\tpermission_classes = [permissions.IsAuthenticated]\n");
        saida.append("\n");
        return null;
    }
}
