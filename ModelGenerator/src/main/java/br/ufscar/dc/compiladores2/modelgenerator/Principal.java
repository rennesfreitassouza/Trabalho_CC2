/*
 * Esse projeto foi compilado e testado usando o NetBeans 12.0, Java 11.0.11 e o
 * ANTLR 4.7.2.
 */
package br.ufscar.dc.compiladores2.modelgenerator;

import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import br.ufscar.dc.compiladores2.modelgenerator.regrasParser.ProgramContext;
import static br.ufscar.dc.compiladores2.modelgenerator.SemanticoUtils.errosSemanticos;

public class Principal {

    public static void main(String args[]) throws IOException {

        // Inicialização do fluxo de caracteres com o arquivo indicado no
        // primeiro argumento.
        CharStream cs = CharStreams.fromFileName(args[0]);

        // Inicialização do analisador léxico com o fluxo de caracteres.
        regrasLexer lexer = new regrasLexer(cs);

        // Inicialização da lista de tokens com o analisador léxico.
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Inicialização do analisador sintático com a lista de tokens.
        regrasParser parser = new regrasParser(tokens);

        // Instanciação da classe que irá capturar os erros.
        MeuErrorListener meuErrorListener = new MeuErrorListener();

        // Remoção da manipulação padrão dos erros sintáticos.
        parser.removeErrorListeners();

        // Adição do objeto instanciado na manipulação dos erros sintáticos.
        parser.addErrorListener(meuErrorListener);

        try {
            // Início da análise sintática pelo símbolo inicial da gramática
            // (pois é um analisador descendente), armazenando a árvore gerada.
            ProgramContext arvore = parser.program();

            // Instanciação a classe para a análise semântica.
            Semantico semantico = new Semantico();

            // Reutilização da árvore sintática para realizar a análise
            // semântica.
            semantico.visitProgram(arvore);

            if (!errosSemanticos.isEmpty()) {
                // Caso haja erros semânticos, imprimí-los e terminar a
                // compilação.

                errosSemanticos.forEach((s) -> System.out.println(s));
                System.out.println("Fim da compilacao");
            } else {
                // Caso não haja erros semânticos, prosseguir com a geração do
                // código.

                // Instanciar as classe para gerar os arquivos.
                GeradorModels models = new GeradorModels();
                GeradorSerializers serializers = new GeradorSerializers();

                // Reutilização da árvore sintática para gerar o código dos
                // arquivos.
                models.visitProgram(arvore);
                serializers.visitProgram(arvore);

                // Impressão dos códigos nos respectivos arquivos.
                try ( PrintWriter pw = new PrintWriter(args[1]
                        + "/models.py")) {
                    pw.print(models.saida.toString());
                    pw.close();
                }
                
                try ( PrintWriter pw = new PrintWriter(args[1]
                        + "/serializers.py")) {
                    pw.print(serializers.saida.toString());
                    pw.close();
                }

                System.out.println("Compilado com sucesso");
            }
        } catch (RuntimeException re) {
            // Caso haja algum erros sintático, imprimí-lo e terminar a
            // compilação.

            System.out.println(re.getMessage());
            System.out.println("Fim da compilacao");
        }
    }
}
