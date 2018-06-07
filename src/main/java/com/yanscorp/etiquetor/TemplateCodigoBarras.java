package com.yanscorp.etiquetor;

import java.util.Arrays;
import java.util.List;

public class TemplateCodigoBarras {
    private List<ElementoCodigoBarras> elementos;
    private Vetor tamanho;
    
    public TemplateCodigoBarras() {
    	elementos = Arrays.asList(
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.CODIGO, AlinhamentoHorizontal.MEIO, AlinhamentoVertical.MEIO, 0),
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.REFERENCIA, AlinhamentoHorizontal.MEIO, AlinhamentoVertical.FIM, 0),
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.PRECO, AlinhamentoHorizontal.DIREITA, AlinhamentoVertical.FIM, 0),
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.COR, AlinhamentoHorizontal.DIREITA, AlinhamentoVertical.MEIO, 90),
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.TAMANHO, AlinhamentoHorizontal.MEIO, AlinhamentoVertical.TOPO, 0),
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.SUB_TAMANHO, AlinhamentoHorizontal.DIREITA, AlinhamentoVertical.TOPO, 0),
    			new ElementoCodigoBarras(TipoElementoCodigoBarras.PRECO, AlinhamentoHorizontal.ESQUERDA, AlinhamentoVertical.MEIO, 270)
    			);
    	
    	tamanho = new Vetor(200, 80);
    	
    }
    
	public List<ElementoCodigoBarras> getElementos() {
		return elementos;
	}
	public void setElementos(List<ElementoCodigoBarras> elementos) {
		this.elementos = elementos;
	}
	public Vetor getTamanho() {
		return tamanho;
	}
	public void setTamanho(Vetor tamanho) {
		this.tamanho = tamanho;
	}
    
    
    
}