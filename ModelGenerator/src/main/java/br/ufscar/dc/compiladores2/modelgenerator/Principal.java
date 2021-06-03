/*
 * Esse projeto foi compilado e testado usando o NetBeans 12.0, Java 11.0.11
 * e o ANTLR 4.9.2.
 */
package br.ufscar.dc.compiladores2.modelgenerator;

import br.ufscar.dc.compiladores2.modelgenerator.regrasParser.ProgramContext;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Principal {

    public static void main(String args[]) throws IOException {

        CharStream cs = CharStreams.fromFileName(args[0]); //Objeto cs da classe CharStream (que gera um fluxo de caracteres) que chama o método utilitário da classe CharStreams.fromFileName para ser construído.
        //A classe CharStreams.fromFileName tem como argumento aquilo que é passado por linha de comando quando o programa é chamado.

        //Construção do léxico.
        regrasLexer modelGeneradorLexer = new regrasLexer(cs);
        //Inicialização do buffer de tokens.
        CommonTokenStream tokens = new CommonTokenStream(modelGeneradorLexer);

        // Inicialização do parser. Para sua inicialização é passado como
        // argumento o fluxo de tokens, que aponta para o lexer, que aponta para
        // o arquivo inserido na linha de comando.
        regrasParser modelGeneratorParser = new regrasParser(tokens);

        MeuErrorListener meuErrorListener = new MeuErrorListener();

        // Remover a manipulação padrão dos erros sintáticos.
        modelGeneratorParser.removeErrorListeners();

        //Adiconar meuErrorListener ao parser instanciado.
        modelGeneratorParser.addErrorListener(meuErrorListener);

        try {
            // A execução do parser começa chamando o símbolo inicial
            // (regra program), já que esse é um analisador  descendente (ele começa
            // pelo o símbolo inicial da gramática e vai descendo na árvore para
            // então construir uma árvore sintática).
            ProgramContext arvoreModelGenerator = modelGeneratorParser.program();

            ModelGeneratorSemantico modelGeneratorSem = new ModelGeneratorSemantico();
            modelGeneratorSem.visitProgram(arvoreModelGenerator);

            if (!ModelGeneratorSemanticoUtils.errosSemanticos.isEmpty()) //Se houver erros semânticos, entra neste bloco de código.
            {
                ModelGeneratorSemanticoUtils.errosSemanticos.forEach((s) -> System.out.println(s));
                //forEach significa "para cada um desses erros". ((s) -> pw.println(s)) imprime a lista no arquivo.

                System.out.println("Fim da compilacao");
            } else // Se não houver erros semânticos, gera o código na linguagem ModelGenerator. 
            {
                ModelGeneratorGerador modelGeneratorG = new ModelGeneratorGerador();  // inicialização do gerador.
                //Um escopo contém as entidades e seus respectivos fields.

                modelGeneratorG.visitProgram(arvoreModelGenerator); // A mesma árvore construída pelo parser é visitada novamente pelo gerador.

                System.out.println("Compilado com sucesso");

                try ( PrintWriter pw2 = new PrintWriter(args[1])) //PrintWriter é um objeto em java que serve para escrever no console ou em arquivo. args[1] é o local que é impresso o código de saída.
                {
                    pw2.print(modelGeneratorG.saida.toString()); //saída contém todo o código HTML de saída.
                    pw2.close();
                }
            }
        } catch (RuntimeException re) {
            // Caso haja erro sintático, capturar a mensagem de erro e imprimir no terminal.
            System.out.println(re.getMessage());

            System.out.println("Compilação abortada");
        }

    }
}
