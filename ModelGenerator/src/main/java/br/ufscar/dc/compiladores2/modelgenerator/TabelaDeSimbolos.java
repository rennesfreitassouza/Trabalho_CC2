package br.ufscar.dc.compiladores2.modelgenerator;

import java.util.HashMap;
import java.util.Map;

public class TabelaDeSimbolos {

    public enum TipoModelGenerator {
        ENTITY,
        FIELD
    }

    class EntradaTabelaDeSimbolos {

        String nome;
        TipoModelGenerator tipo;

        private EntradaTabelaDeSimbolos(String nome, TipoModelGenerator tipo) {
            this.nome = nome;
            this.tipo = tipo;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }

    public TipoModelGenerator obterTipo(String nome) {
        return tabela.get(nome).tipo;
    }

    public void adicionarEntity(String nome) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, TipoModelGenerator.ENTITY));
    }

    public void adicionarField(String nome) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, TipoModelGenerator.FIELD));
    }
}
