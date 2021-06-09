package br.ufscar.dc.compiladores2.modelgenerator;

import java.util.List;
import java.util.ArrayList;
import org.antlr.v4.runtime.Token;

public class SemanticoUtils {

    public static List<String> errosSemanticos = new ArrayList<>();

    public static void adicionarErroSemantico(Token token, String mensagem) {
        int linha = token.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }
}
