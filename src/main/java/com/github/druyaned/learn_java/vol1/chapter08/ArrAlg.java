package com.github.druyaned.learn_java.vol1.chapter08;

import java.util.function.Supplier;

/**
 * Allows to test wildcards and some other generic aspects.<p>
 * The main rule is:<p>
 * <code>&lt? super Type&gt: write data to a generic instance</code><p>
 * <code>&lt? extends Type&gt: read data from a generic instance</code>
 */
public class ArrAlg {
    public static <T> void swap(Pair<T> p) {
        T stock = p.getFst();
        p.setFst(p.getSnd());
        p.setSnd(stock);
    }

    public static <T extends Comparable<T> & MaxSpeedable>
                  void minmax(T[] arr, Pair<? super T> res) {
        T mn = arr[0];
        T mx = arr[0];
        for (T t : arr) {
            if (t.compareTo(mn) < 0) {mn = t;}
            if (t.compareTo(mx) > 0) {mx = t;}
        }
        res.setFst(mn);
        res.setSnd(mx);
    }

    public static <T extends Comparable<T> & MaxSpeedable> 
                  void maxmin(T[] arr, Pair<? super T> res) {
        minmax(arr, res);
        swap(res);
    }

    public static class Pair<T> {
        private T fst;
        private T snd;
    
        public Pair(T fst, T snd) {
            this.fst = fst;
            this.snd = snd;
        }

        private Pair(Supplier<T> fstConstr, Supplier<T> sndConstr) {
            fst = fstConstr.get();
            snd = sndConstr.get();
        }
    
        public T getFst() {return fst;}
        public T getSnd() {return snd;}
    
        public void setFst(T fst) {this.fst = fst;}
        public void setSnd(T snd) {this.snd = snd;}
    
        @Override
        public String toString() {
            return "[fst=" + fst + ", snd=" + snd + "]";
        }

        public static <U> Pair<U> from(Supplier<U> fstConstr, Supplier<U> sndConstr) {
            return new Pair<U>(fstConstr, sndConstr);
        }
    }
}