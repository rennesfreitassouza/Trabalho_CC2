package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.Token;

public class GeradorModels extends regrasBaseVisitor<Void> {

    String site;
    String app;

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorModels(String s, String p) {
        site = s;
        app = p;
        saida = new StringBuilder();
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        saida.append("from django.db import models\n");
        saida.append("\n");

        visitModel(ctx.model());

        return null;
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENTIFICADOR().getSymbol();
        String entityName = entityToken.getText();

        saida.append("class " + entityName + "(models.Model):\n");
        for (regrasParser.FieldContext field : ctx.field()) {
            visitField(field);
        }
        saida.append("\n");

        return null;
    }

    @Override
    public Void visitField(regrasParser.FieldContext ctx) {
        Token fieldToken = ctx.IDENTIFICADOR(0).getSymbol();
        String fieldNome = fieldToken.getText();

        saida.append("\t" + fieldNome + " = ");

        if (ctx.tipo_basico() != null) {
            visitTipo_basico(ctx.tipo_basico());

            saida.append("(");
            for (regrasParser.ParameterContext parameter : ctx.parameter()) {
                visitParameter(parameter);

                if (ctx.parameter().indexOf(parameter)
                        < ctx.parameter().size() - 1) {
                    saida.append(", ");
                }
            }
            saida.append(")\n");

        } else {
            Token tipoToken = ctx.IDENTIFICADOR(1).getSymbol();
            String tipoNome = tipoToken.getText();

            saida.append("models.ForeignKey(" + tipoNome + ", " + "on_delete=models.CASCADE)\n");
        }

        return null;
    }

    @Override
    public Void visitTipo_basico(regrasParser.Tipo_basicoContext ctx) {
        if (ctx.getText().equals("int")) {
            saida.append("models.IntegerField");
        } else if (ctx.getText().equals("string")) {
            saida.append("models.CharField");
        } else if (ctx.getText().equals("date")) {
            saida.append("models.DateTimeField");
        }
        return null;
    }

    @Override
    public Void visitParameter(regrasParser.ParameterContext ctx) {
        if (ctx.NUMERO() != null) {
            saida.append("max_length = " + ctx.NUMERO().getText());
        } else if (ctx.unique != null) {
            saida.append("unique = True");
        }
        return null;
    }
}
