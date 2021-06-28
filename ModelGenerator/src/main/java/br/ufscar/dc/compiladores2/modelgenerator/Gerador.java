package br.ufscar.dc.compiladores2.modelgenerator;

import static br.ufscar.dc.compiladores2.modelgenerator.GeradorUtils.imprimirArquivo;
import org.antlr.v4.runtime.Token;

public class Gerador extends regrasBaseVisitor<Void> {

    String path;
    String site;
    String app;

    GeradorModels models;
    GeradorSerializers serializers;
    GeradorVIews views;
    GeradorAppUrls appUrls;
    GeradorEnv env;
    GeradorSettings settings;
    GeradorDefaultUrls defaultUrls;

    public Gerador(String _path) {
        path = _path;
        site = "mysite";
        app = "app";
    }

    @Override
    public Void visitProgram(regrasParser.ProgramContext arvore) throws RuntimeException {
        visitConfig(arvore.config());

        // Instanciar as classe para a geração dos arquivos.
        models = new GeradorModels(site, app);
        serializers = new GeradorSerializers(site, app);
        views = new GeradorVIews(site, app);
        appUrls = new GeradorAppUrls(site, app);
        env = new GeradorEnv(site, app);
        settings = new GeradorSettings(site, app);
        defaultUrls = new GeradorDefaultUrls(site, app);

        // Reutilização da árvore sintática para gerar o código dos arquivos.
        models.visitProgram(arvore);
        serializers.visitProgram(arvore);
        views.visitProgram(arvore);
        appUrls.visitProgram(arvore);
        env.visitProgram(arvore);
        settings.visitProgram(arvore);
        defaultUrls.visitProgram(arvore);

        // Impressão dos códigos nos respectivos arquivos.
        imprimirArquivo(path + "/" + app + "/models.py", models.saida.toString());
        imprimirArquivo(path + "/" + app + "/serializers.py", serializers.saida.toString());
        imprimirArquivo(path + "/" + app + "/views.py", views.saida.toString());
        imprimirArquivo(path + "/" + app + "/urls.py", appUrls.saida.toString());
        imprimirArquivo(path + "/" + site + "/.env", env.saida.toString());
        imprimirArquivo(path + "/" + site + "/settings.py", settings.saida.toString());
        imprimirArquivo(path + "/" + site + "/urls.py", defaultUrls.saida.toString());

        return null;
    }

    @Override
    public Void visitApp(regrasParser.AppContext ctx) {
        Token appToken = ctx.IDENTIFICADOR().getSymbol();
        String appNome = appToken.getText();
        app = appNome;
        return null;
    }

    @Override
    public Void visitSite(regrasParser.SiteContext ctx) {
        Token siteToken = ctx.IDENTIFICADOR().getSymbol();
        String siteNome = siteToken.getText();
        site = siteNome;
        return null;
    }

}
