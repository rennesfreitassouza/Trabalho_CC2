package br.ufscar.dc.compiladores2.modelgenerator;

import static br.ufscar.dc.compiladores2.modelgenerator.ModelGeneratorSemanticoUtils.insereErroIdentificadorJaDeclarado;
import static br.ufscar.dc.compiladores2.modelgenerator.ModelGeneratorSemanticoUtils.insereErroTipoNaoDeclarado;
import org.antlr.v4.runtime.Token;

public class ModelGeneratorSemantico extends regrasBaseVisitor<Void> {

    Escopos escopo;

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        escopo = new Escopos();
        return super.visitProgram(ctx);
    }

    @Override
    public Void visitEntity(regrasParser.EntityContext ctx) {
        Token entityToken = ctx.IDENT().getSymbol();

        String entityNome = entityToken.getText();

        if (escopo.existe(entityNome)) {
            insereErroIdentificadorJaDeclarado(entityToken);
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
        Token fieldToken = ctx.fieldName;
        Token modelToken = ctx.otherModelName;

        String fieldNome = fieldToken.getText();

        if (escopo.existe(fieldNome)) {
            insereErroIdentificadorJaDeclarado(fieldToken);
        } else {
            escopo.adicionarField(fieldNome);

            if (modelToken != null) {
                String modelNome = modelToken.getText();

                // Reportar erro caso o field seja declarado utilizando um novo
                // tipo não declarado como ENTITY.
                if (escopo.obterTipo(modelNome) != TabelaDeSimbolos.TipoModelGenerator.ENTITY) {
                    insereErroTipoNaoDeclarado(modelToken);
                }
            }
        }

        return super.visitField(ctx);
    }

}
