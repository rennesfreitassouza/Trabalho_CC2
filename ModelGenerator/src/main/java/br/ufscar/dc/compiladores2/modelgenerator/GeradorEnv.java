package br.ufscar.dc.compiladores2.modelgenerator;

public class GeradorEnv extends regrasBaseVisitor<Void> {

    String site;
    String app;

    // Para construir o código python, será usado a classe StringBuilder. O
    // objeto saida do tipo StringBuilder é um acumulador de Strings.
    StringBuilder saida;

    String host;
    String port;
    String db;
    String user;
    String pass;

    public GeradorEnv(String s, String p) {
        site = s;
        app = p;
        saida = new StringBuilder();

        host = "localhost";
        port = "3306";
        db = "mysquema";
        user = "root";
        pass = "123";
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext ctx) {
        if (ctx.database() != null) {
            visitDatabase(ctx.database());
        }

        saida.append("DEBUG=on\n");
        saida.append(site.toUpperCase() + "_HOST=" + host + "\n");
        saida.append(site.toUpperCase() + "_PORT=" + port + "\n");
        saida.append(site.toUpperCase() + "_DB=" + db + "\n");
        saida.append(site.toUpperCase() + "_USER=" + user + "\n");
        saida.append(site.toUpperCase() + "_PASS=" + pass + "\n");

        return null;
    }

    @Override
    public Void visitDb_config(regrasParser.Db_configContext ctx) {
        String config = ctx.environment().getText();
        String valor;

        if (ctx.IDENTIFICADOR() != null) {
            valor = ctx.IDENTIFICADOR().getText();
        } else {
            valor = ctx.NUMERO().getText();
        }

        switch (config) {
            case "HOST":
                host = valor;
                break;
            case "PORT":
                port = valor;
                break;
            case "DB":
                db = valor;
                break;
            case "USER":
                user = valor;
                break;
            case "PASS":
                pass = valor;
                break;
            default:
                break;
        }

        return null;
    }

}
