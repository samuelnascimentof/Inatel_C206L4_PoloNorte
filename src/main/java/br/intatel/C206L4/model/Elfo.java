package br.intatel.C206L4.model;

public class Elfo {
    private String nome;
    private String funcao;

    public Elfo(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }
}
