package com.soares.hudson.springcrude.enuns;

public enum CategoriaEnum {
    CURSOS("Cursos"),
    TREINAMENTOS("Treinamentos");

    private String nome;

    private CategoriaEnum( String nome ) {
        this.nome = nome;
    }

    public String getNome () {
        return this.nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
