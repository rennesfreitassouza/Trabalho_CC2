package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.Token;

public class GeradorSerializers extends regrasBaseVisitor<Void> {

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorSerializers() {
        saida = new StringBuilder();

        saida.append("from rest_framework import serializers\n");
        saida.append("from .models import *\n");
        saida.append("\n");
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENT().getSymbol();
        String entityNome = entityToken.getText();

        saida.append("class " + entityNome
                + "Serializer(serializers.ModelSerializer):\n");
        saida.append("\tclass Meta:\n");
        saida.append("\t\tmodel = " + entityNome + "\n");
        saida.append("\t\tfields = '__all__'\n");
        saida.append("\n");
        return null;
    }
}
