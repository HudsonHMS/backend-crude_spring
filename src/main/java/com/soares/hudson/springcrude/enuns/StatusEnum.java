package com.soares.hudson.springcrude.enuns;

public enum StatusEnum {

    ATIVO(1),
    INATIVO(2);

    private Integer status;

    private StatusEnum ( Integer status ) {
        this.status = status;
    }

    public Integer getStatus () {
        return this.status;
    }

    @Override
    public String toString() {
        return getStatus().toString();
    }

}
