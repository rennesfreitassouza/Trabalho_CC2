/*
 * Esse projeto foi compilado e testado usando o NetBeans 12.0, Java 11.0.11
 * e o ANTLR 4.9.2.
 */
package br.ufscar.dc.compiladores2.modelgenerator;

import br.ufscar.dc.compiladores2.modelgenerator.regrasParser.ProgramContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Principal {

    public static void main(String args[]) throws IOException {

        CharStream cs = CharStreams.fromFileName(args[0]); //Objeto cs da classe CharStream (que gera um fluxo de caracteres) que chama o método utilitário da classe CharStreams.fromFileName para ser construído.
        //A classe CharStreams.fromFileName tem como argumento aquilo que é passado por linha de comando quando o programa é chamado.
       
        //Caso o código fonte não possuir erros léxicos, os tokens que seriam armazenados em arquivo são descartados e um novo arquivo é criado.
        FileOutputStream arquivo = new FileOutputStream(args[1]); //Objeto com nome de 'arquivo' do tipo FileOutputStream que cria um arquivo .txt no diretório passado no segundo argumento por linha de comando para ser escrito.
        PrintWriter pw = new PrintWriter(arquivo); // Objeto do tipo PrintWriter que escreve no objeto 'arquivo' que está aberto para gravar dados.
        
        //Construção do léxico.
        regrasLexer modelGeneradorLexer = new regrasLexer(cs);
        //Inicialização do buffer de tokens.
        CommonTokenStream tokens = new CommonTokenStream(modelGeneradorLexer);
        
        // Inicialização do parser. Para sua inicialização é passado como
        // argumento o fluxo de tokens, que aponta para o lexer, que aponta para
        // o arquivo inserido na linha de comando.
        regrasParser modelGeneratorParser = new regrasParser(tokens);
        
        MeuErrorListener meuErrorListener = new MeuErrorListener(arquivo, pw);
        
        //Adiconar meuErrorListener ao parser instanciado.
        modelGeneratorParser.addErrorListener(meuErrorListener);
        
        // A execução do parser começa chamando o símbolo inicial
        // (regra program), já que esse é um analisador  descendente (ele começa
        // pelo o símbolo inicial da gramática e vai descendo na árvore para
        // então construir uma árvore sintática).
        ProgramContext arvoreModelGenerator = modelGeneratorParser.program();
        
        
        ModelGeneratorSemantico modelGeneratorSem = new ModelGeneratorSemantico();
        modelGeneratorSem.visitProgram(arvoreModelGenerator);
        
        
        ModelGeneratorSemanticoUtils.errosSemanticos.forEach((s) -> pw.println(s));
        //forEach significa "para cada um desses erros". ((s) -> pw.println(s)) imprime a lista no arquivo.
        if (!ModelGeneratorSemanticoUtils.errosSemanticos.isEmpty()) //Se houver erros semânticos, entra neste bloco de código.
        {
            pw.println("Fim da compilacao");
            pw.close();
            arquivo.close();
        }
        else // Se não houver erros semânticos, gera o código na linguagem ModelGenerator. 
        {
            pw.close();
            arquivo.close();
            
            ModelGeneratorGerador modelGeneratorG = new ModelGeneratorGerador();  // inicialização do gerador.
            //Um escopo contém as entidades e seus respectivos fields.
            
            modelGeneratorG.visitProgram(arvoreModelGenerator); // A mesma árvore construída pelo parser é visitada novamente pelo gerador.
            try ( PrintWriter pw2 = new PrintWriter(args[1])) //PrintWriter é um objeto em java que serve para escrever no console ou em arquivo. args[1] é o local que é impresso o código de saída.
            {
                pw2.print(modelGeneratorG.saida.toString()); //saída contém todo o código HTML de saída.
                pw2.close();
            }
        }
        
    }
}
