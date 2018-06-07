package com.yanscorp.etiquetor;

public class ElementoCodigoBarras {
    private TipoElementoCodigoBarras tipo;
    private AlinhamentoHorizontal alinhamentoHorizontal;
    private AlinhamentoVertical alinhamentoVertical;
    private Vetor ajuste;
    private int rotacao;
    private Vetor tamanho;
    private float escala;
    
    
    
	public ElementoCodigoBarras(TipoElementoCodigoBarras tipo, AlinhamentoHorizontal alinhamentoHorizontal,
			AlinhamentoVertical alinhamentoVertical, Vetor ajuste, int rotacao, Vetor tamanho, float escala) {
		super();
		this.tipo = tipo;
		this.alinhamentoHorizontal = alinhamentoHorizontal;
		this.alinhamentoVertical = alinhamentoVertical;
		this.ajuste = ajuste;
		this.rotacao = rotacao;
		this.tamanho = tamanho;
		this.escala = escala;
	}

	public ElementoCodigoBarras(TipoElementoCodigoBarras tipo, AlinhamentoHorizontal alinhamentoHorizontal,
			AlinhamentoVertical alinhamentoVertical, int rotacao) {
		this(tipo, alinhamentoHorizontal, alinhamentoVertical, Vetor.zero(), rotacao, new Vetor(90, 80), 100);
		this.tipo = tipo;
		this.alinhamentoHorizontal = alinhamentoHorizontal;
		this.alinhamentoVertical = alinhamentoVertical;
		this.rotacao = rotacao;
	}
	
	public TipoElementoCodigoBarras getTipo() {
		return tipo;
	}
	public void setTipo(TipoElementoCodigoBarras tipo) {
		this.tipo = tipo;
	}
	public AlinhamentoHorizontal getAlinhamentoHorizontal() {
		return alinhamentoHorizontal;
	}
	public void setAlinhamentoHorizontal(AlinhamentoHorizontal alinhamentoHorizontal) {
		this.alinhamentoHorizontal = alinhamentoHorizontal;
	}
	public AlinhamentoVertical getAlinhamentoVertical() {
		return alinhamentoVertical;
	}
	public void setAlinhamentoVertical(AlinhamentoVertical alinhamentoVertical) {
		this.alinhamentoVertical = alinhamentoVertical;
	}
	public Vetor getAjuste() {
		return ajuste;
	}
	public void setAjuste(Vetor ajuste) {
		this.ajuste = ajuste;
	}
	public int getRotacao() {
		return rotacao;
	}
	public void setRotacao(int rotacao) {
		this.rotacao = rotacao;
	}
	public Vetor getTamanho() {
		return tamanho;
	}
	public void setTamanho(Vetor tamanho) {
		this.tamanho = tamanho;
	}
	public float getEscala() {
		return escala;
	}
	public void setEscala(float escala) {
		this.escala = escala;
	}
    
}