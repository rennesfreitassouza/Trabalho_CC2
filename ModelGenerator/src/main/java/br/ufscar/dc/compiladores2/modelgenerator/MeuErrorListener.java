
package br.ufscar.dc.compiladores2.modelgenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class MeuErrorListener extends BaseErrorListener {
    
      private static PrintWriter pw;
      private static FileOutputStream arquivo;
      
      public MeuErrorListener(FileOutputStream arquivo, PrintWriter escritaarquivo) {
          this.arquivo = arquivo;
          this.pw = escritaarquivo;   //Objeto do tipo PrintWriter que escreve no objeto 'arquivo' da classe Principal que está aberto para gravar dados.
    }
      
      //Para impressao dos erros identificados sintáticos em arquivo.
      @Override
      public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
      System.out.println("Erro identificado. Compilação abortada.");
      pw.println("Erro identificado:"+msg);
          try {
              pw.close();
              arquivo.close();
          } catch (IOException ex) {
              Logger.getLogger(MeuErrorListener.class.getName()).log(Level.SEVERE, null, ex);
          }
      System.exit(0);
   }
}
