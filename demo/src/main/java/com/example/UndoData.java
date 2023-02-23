package com.example;

public class UndoData {
    private Pion[][] oudeOpstelling;
    private Speler speler;

    public UndoData(){
        this.oudeOpstelling = Speelveld.veldPionnen.clone();
        this.speler = Speelveld.huidigeSpeler;
    }

    public void terugNaarOudeOpstelling(){
        Speelveld.veldPionnen = oudeOpstelling.clone();
        Speelveld.huidigeSpeler = speler;
    }
}
