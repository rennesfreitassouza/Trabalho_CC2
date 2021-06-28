package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.Token;

public class GeradorSerializers extends regrasBaseVisitor<Void> {

    String site;
    String app;

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorSerializers(String s, String p) {
        site = s;
        app = p;
        saida = new StringBuilder();
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        saida.append("from rest_framework import serializers\n");
        saida.append("from .models import *\n");
        saida.append("\n");

        visitModel(ctx.model());

        return null;
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENTIFICADOR().getSymbol();
        String entityNome = entityToken.getText();

        saida.append("class " + entityNome + "Serializer(serializers.ModelSerializer):\n");
        saida.append("\tclass Meta:\n");
        saida.append("\t\tmodel = " + entityNome + "\n");
        saida.append("\t\tfields = '__all__'\n");
        saida.append("\n");
        return null;
    }
}
