package br.ufscar.dc.compiladores2.modelgenerator;

import java.util.List;
import java.util.LinkedList;

public class Escopos {

    private LinkedList<TabelaDeSimbolos> pilhaDeTabelas;

    public Escopos() {
        pilhaDeTabelas = new LinkedList<>();
        criarNovoEscopo();
    }

    public void criarNovoEscopo() {
        pilhaDeTabelas.push(new TabelaDeSimbolos());
    }

    public TabelaDeSimbolos obterEscopoAtual() {
        return pilhaDeTabelas.peek();
    }

    public List<TabelaDeSimbolos> percorrerEscoposAninhados() {
        return pilhaDeTabelas;
    }

    public void abandonarEscopo() {
        pilhaDeTabelas.pop();
    }

    public boolean existe(String nome) {
        for (TabelaDeSimbolos ts : pilhaDeTabelas) {
            if (ts.existe(nome)) {
                return true;
            }
        }
        return false;
    }

    public TabelaDeSimbolos.TipoModelGenerator obterTipo(String nome) {
        for (TabelaDeSimbolos ts : pilhaDeTabelas) {
            if (ts.existe(nome)) {
                return ts.obterTipo(nome);
            }
        }
        return null;
    }

    public void adicionarEntity(String nome) {
        obterEscopoAtual().adicionarEntity(nome);
    }

    public void adicionarField(String nome) {
        obterEscopoAtual().adicionarField(nome);
    }
}
