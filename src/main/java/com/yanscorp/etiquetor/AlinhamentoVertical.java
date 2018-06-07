package com.yanscorp.etiquetor;

public enum AlinhamentoVertical {
    TOPO(1),
    MEIO(2),
    FIM(3);

    private final int valor;

    AlinhamentoVertical(int valor) {
        this.valor = valor;
    }

}