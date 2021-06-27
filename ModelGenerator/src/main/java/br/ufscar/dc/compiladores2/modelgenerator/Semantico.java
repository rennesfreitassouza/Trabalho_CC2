package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.Token;
import static br.ufscar.dc.compiladores2.modelgenerator.SemanticoUtils.adicionarErroSemantico;

public class Semantico extends regrasBaseVisitor<Void> {

    Escopos escopo;

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        escopo = new Escopos();
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENTIFICADOR().getSymbol();
        String entityNome = entityToken.getText();

        if (escopo.existe(entityNome)) {
            adicionarErroSemantico(entityToken, "\"" + entityNome
                    + "\" já declarado anteriormente.");
        } else {
            escopo.adicionarEntity(entityNome);
        }

        escopo.criarNovoEscopo();
        super.visitEntity(ctx);
        escopo.abandonarEscopo();
        return null;
    }

    @Override
    public Void visitField(regrasParser.FieldContext ctx) {
        Token fieldToken = ctx.IDENTIFICADOR(0).getSymbol();
        String fieldNome = fieldToken.getText();

        if (escopo.existe(fieldNome)) {
            adicionarErroSemantico(fieldToken, "\"" + fieldNome
                    + "\" já declarado anteriormente.");
        } else {
            escopo.adicionarField(fieldNome);

            if (ctx.IDENTIFICADOR(1) != null) {
                Token modelToken = ctx.IDENTIFICADOR(1).getSymbol();
                String modelNome = modelToken.getText();

                // Reporta erro caso o field seja declarado utilizando um novo
                // tipo não declarado como ENTITY.
                if (escopo.obterTipo(modelNome)
                        != TabelaDeSimbolos.TipoModelGenerator.ENTITY) {
                    adicionarErroSemantico(modelToken, "\"" + modelNome
                            + "\" não declarado anteriormente.");
                }
            }
        }
        return super.visitField(ctx);
    }
}
