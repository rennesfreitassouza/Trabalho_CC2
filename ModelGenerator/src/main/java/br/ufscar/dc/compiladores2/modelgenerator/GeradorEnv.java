package br.ufscar.dc.compiladores2.modelgenerator;

public class GeradorEnv extends regrasBaseVisitor<Void> {

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    public GeradorEnv() {
        saida = new StringBuilder();
        
        saida.append("DEBUG=on\n");
        saida.append("MYSITE_HOST=localhost\n");
        saida.append("MYSITE_PORT=3306\n");
        saida.append("MYSITE_DB=mysquema\n");
        saida.append("MYSITE_USER=root\n");
        saida.append("MYSITE_PASS=123\n");
    }
}
