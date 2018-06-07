package com.yanscorp.etiquetor;

public enum TipoElementoCodigoBarras {
    CODIGO(1),
    REFERENCIA(2),
    PRECO(3),
    TAMANHO(4),
    SUB_TAMANHO(5),
    COR(6);

    private final int valor;
    
    TipoElementoCodigoBarras(int valor) {
        this.valor = valor;
    }

}