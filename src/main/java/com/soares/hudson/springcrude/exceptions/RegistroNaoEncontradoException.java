package com.soares.hudson.springcrude.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(Long id) {
        super(String.format("Registro com o id: %o não encontrado!", id));
    }

    public RegistroNaoEncontradoException(Long id, Object o) {
        super(String.format("%s com o id: %o não encontrado!",
                o.getClass().getName().substring(o.getClass().getName().lastIndexOf('.') + 1), id));
    }

}
