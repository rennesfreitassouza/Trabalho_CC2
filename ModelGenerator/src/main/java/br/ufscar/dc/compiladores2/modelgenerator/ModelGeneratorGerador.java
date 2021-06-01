package br.ufscar.dc.compiladores2.modelgenerator;

public class ModelGeneratorGerador extends regrasBaseVisitor<Void> {
    
    StringBuilder saida; // Para construir o código python, será usado a classe StringBuilder. O objeto saida do tipo StringBuilder é um acumulador de Strings.
    Escopos escopoModelGenerator; // Para construir o código python, a classe Escopos será usada.
    
    public ModelGeneratorGerador() {
        escopoModelGenerator = new Escopos();
        saida = new StringBuilder();
    }
    
    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx){
        visitModel(ctx.model());
        return null;
    }
    
    
    @Override
    public Void visitModel(regrasParser.ModelContext ctx){
        if (ctx.imports() != null){
            for (regrasParser.ImportsContext djangoModules : ctx.imports()){
                visitImports(djangoModules);
            }
        }
        return null;
    }
    
    @Override
    public Void visitImports(regrasParser.ImportsContext ctx){
        saida.append("from django.db ");
        String stringImport = ctx.start.getText();
        saida.append(stringImport); 
        for (regrasParser.ModulesContext modules : ctx.modules()){
            saida.append(" " + modules.getText());
            if (ctx.stop.getText().equals(",")){
                saida.append(ctx.stop.getText());
            }
        }
        saida.append("\n");
        return null;
    }
}
