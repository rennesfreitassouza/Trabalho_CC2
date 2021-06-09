package br.ufscar.dc.compiladores2.modelgenerator;

public class GeradorModels extends regrasBaseVisitor<Void> {
    
    StringBuilder saida; // Para construir o código python, será usado a classe StringBuilder. O objeto saida do tipo StringBuilder é um acumulador de Strings.
    Escopos escopoModelGenerator; // Para construir o código python, a classe Escopos será usada.
    
    public GeradorModels() {
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
        if (ctx.entity() != null){
            for (regrasParser.EntityContext modelClass : ctx.entity()){
                visitEntity(modelClass);
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
    
    @Override
    public Void visitEntity(regrasParser.EntityContext ctx){
        String className = ctx.IDENT().getText();
        saida.append("class "+className+"(models.Model):\n");
        for (regrasParser.FieldContext modelAttr : ctx.field()){
            visitField(modelAttr);
        }
        saida.append("\n");
        return null;
    }
    
    @Override
    public Void visitField(regrasParser.FieldContext ctx){
        String attrName = ctx.fieldName.getText();
        
        saida.append("\t"+attrName+" = ");
        
        if (ctx.tipo_basico() != null){
            visitTipo_basico(ctx.tipo_basico());
            saida.append("(");
            if (ctx.params() != null){
                for (regrasParser.ParamsContext parameter : ctx.params()){
                    visitParams(parameter);
                    if(ctx.TL != null){
                        saida.append(ctx.TL.getText());
                    }
                }
            }
            saida.append(")\n");
        }
        else{
            if(ctx.otherModelName != null){
                saida.append("models.ForeignKey("+ctx.otherModelName.getText()+", "+"on_delete=models.CASCADE)"); //Modificar?
                saida.append("\n");
            }
        }
        
        
        return null;
    }
    
    @Override
    public Void visitTipo_basico(regrasParser.Tipo_basicoContext ctx){
        
        if (ctx.getText().equals("int")){
            saida.append("models.IntegerField"); //Modificar?
            
        }
        if (ctx.getText().equals("string")){
            saida.append("models.CharField"); //Modificar?
            
        }
        if (ctx.getText().equals("date")){
            saida.append("models.DateTimeField"); //Modificar?
            
        }
        return null;
    }
    
    @Override
    public Void visitParams(regrasParser.ParamsContext ctx){
        if(ctx.parCharFieldML != null){
            saida.append(ctx.parCharFieldML.getText()+ctx.parCharFieldTL.getText()+ctx.NUM_INT().getText());
        }
        if(ctx.parFieldUnique != null){
            saida.append(ctx.parFieldUnique.getText()+ctx.parFieldTL.getText()+ctx.parFieldBoolean.getText());
        }
        
        return null;
    }
}