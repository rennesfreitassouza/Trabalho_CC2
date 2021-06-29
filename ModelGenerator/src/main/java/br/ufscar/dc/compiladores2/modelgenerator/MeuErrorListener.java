package br.ufscar.dc.compiladores2.modelgenerator;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class MeuErrorListener extends BaseErrorListener {

    //Para impressao dos erros identificados sintáticos em arquivo.
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
            int line, int charPositionInLine, String msg,
            RecognitionException e) {

        // Token do objeto que provocou o erro.
        Token errorToken = (Token) offendingSymbol;

        // String que representa o erro.
        String errorTokenString = errorToken.getText();

        // String contendo a mensagem de erro.
        String errorMessage = "Erro identificado: Linha " + line
                + " - erro próximo a \"" + errorTokenString + "\"";
        
        if (!msg.equals(null)){
            System.out.println(msg);
        }
        // Lançamento de uma RuntimeExcpetion com a mensagem de erro.
        throw new RuntimeException(errorMessage);
    }
}
