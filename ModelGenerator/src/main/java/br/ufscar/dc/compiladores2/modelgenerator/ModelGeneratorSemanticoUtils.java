package br.ufscar.dc.compiladores2.modelgenerator;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;
//import org.antlr.v4.runtime.tree.TerminalNode;

//Exemplo:
public class ModelGeneratorSemanticoUtils {
    
    public static List<String> errosSemanticos = new ArrayList<>();
    // errosSemanticos é uma lista de Strings. Quando o método add for invocado
    // a partir dela, a String indicada é adicionada na lista.
    
    
    
    public static void insereErroIdentificadorJaDeclarado(Token identificador){
        errosSemanticos.add("Linha "+identificador.getLine()+": identificador "+identificador.getText()+" já declarado anteriormente.");
    }
}
