package com.ddweb.structures;

/**
 * TEMP
 *
 * */
public class Pair<K,L> {
    private K k;
    private L l;

    public Pair(K k, L l) {
        this.k = k;
        this.l = l;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }

    public L getL() {
        return l;
    }

    public void setL(L l) {
        this.l = l;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (k != null ? !k.equals(pair.k) : pair.k != null) return false;
        return l != null ? l.equals(pair.l) : pair.l == null;
    }

    @Override
    public int hashCode() {
        int result = k != null ? k.hashCode() : 0;
        result = 31 * result + (l != null ? l.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "k=" + k +
                ", l=" + l +
                '}';
    }
}
