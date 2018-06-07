package com.yanscorp.etiquetor;

public enum AlinhamentoHorizontal {
    ESQUERDA(1),
    MEIO(2),
    DIREITA(3);

    private final int valor;

    AlinhamentoHorizontal(int valor) {
        this.valor = valor;
    }

}