package com.soares.hudson.springcrude.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(Long id) {
        super(String.format("Registro com o id: %s não encontrado!", id.toString()));
    }

    public RegistroNaoEncontradoException(Long id, Object o) {
        super(String.format("%s com o id: %s não encontrado!",
                o.getClass().getName().substring(o.getClass().getName().lastIndexOf('.') + 1), id.toString()));
    }

}
