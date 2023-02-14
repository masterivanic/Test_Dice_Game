package com.company.pages.window;

public class Motif<T> {

    private T motifName;

    public Motif() {
    }

    public Motif(T motifName) {
        this.motifName = motifName;
    }

    public T getMotifName() {
        return motifName;
    }

    public void setMotifName(T motifName) {
        if (motifName == null) {
            throw new NullPointerException("null is not permit");
        }
        this.motifName = motifName;
    }

    @Override
    public String toString() {
        return "" + motifName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Motif<>(motifName);
    }

}
