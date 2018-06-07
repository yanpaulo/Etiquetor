package com.yanscorp.etiquetor;

public class Vetor {
    private float x;
    private float y;

    public Vetor(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public Vetor soma(Vetor v) {
        return new Vetor(this.x + v.x, this.y + v.y);
    }

    public Vetor multiplica(float escalar) {
        return new Vetor(this.x * escalar, this.y + escalar);
    }
    
    public static Vetor zero() {
    	return new Vetor(0, 0);
    }
}