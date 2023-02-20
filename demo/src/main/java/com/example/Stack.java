package com.example;

public class Stack {

    private Pannenkoek<Object> bovenste;

    public Stack() { //constructor
        this.bovenste = null;
    }

    public void nieuwPannenkoekOpBord(Object obj) {
        Pannenkoek<Object> pannenkoek = new Pannenkoek<>(obj);
        pannenkoek.setVolgende(this.bovenste);
        this.bovenste = pannenkoek;
    }

    // je kan alleen de bovenste pannenkoek pakken
    public Object pakPannenkoekVanBord() {
        if (this.bovenste == null) {
            return null;
        }
        Pannenkoek<Object> bovenstePannenkoek = this.bovenste;
        this.bovenste = bovenstePannenkoek.getVolgende();
        return bovenstePannenkoek.getBeleg();
    }

    private class Pannenkoek<T> {
        private T beleg;
        private Pannenkoek<T> volgende; //is dus de pannenkoek hier onder

        public Pannenkoek(T beleg) {
            setBeleg(beleg);
            setVolgende(null);
        }

        public void setBeleg(T beleg){this.beleg = beleg;}
        public Object getBeleg(){return beleg;}

        public Pannenkoek<T> getVolgende() {return this.volgende;}
        public void setVolgende(Pannenkoek<T> volgende) {this.volgende = volgende;}
    }
}