package br.ufscar.dc.compiladores2.modelgenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GeradorUtils {

    public static void imprimirArquivo(String path, String texto) {
        try ( PrintWriter pw = new PrintWriter(path)) {
            pw.print(texto);
            pw.close();
        } catch (FileNotFoundException ex) {
            String errorMessage = "Arquivo \"" + path + "\" não encontrado.";
            throw new RuntimeException(errorMessage);
        }
    }
}
