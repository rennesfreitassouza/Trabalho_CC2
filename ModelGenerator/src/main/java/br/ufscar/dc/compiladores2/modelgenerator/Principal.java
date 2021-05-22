/*
 * Esse projeto foi compilado e testado usando o NetBeans 12.0, Java 11.0.11
 * e o ANTLR 4.9.2.
 */
package br.ufscar.dc.compiladores2.modelgenerator;

import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

public class Principal {

    public static void main(String args[]) throws IOException {

        // Inicializar o primeiro argumento como arquivo de entrada
        CharStream cs = CharStreams.fromFileName(args[0]);

        try {
            System.out.println("Fim da compilacao");

        } catch (RuntimeException re) {
            // Caso haja erro sintático, capturar a mensagem de erro e imprimir
            // no terminal
            System.out.println(re.getMessage());

            System.out.println("Fim da compilacao");
        }
    }
}
