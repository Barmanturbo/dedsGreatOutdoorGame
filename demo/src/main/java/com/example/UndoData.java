package com.example;

public class UndoData {
    private Pion[][] oudeOpstelling;
    private Speler speler;

    public UndoData(){
        this.speler = App.huidigeSpeler;

        for(int i=0;i<Speelveld.rijen;i++){
            for(int j=0;j<Speelveld.rijen;j++){
                oudeOpstelling[i][j]=Speelveld.veldPionnen[i][j];
            }
        }
    }

    public void terugNaarOudeOpstelling(){
        for(int i=0;i<oudeOpstelling.length;i++){
            for(int j=0;j<oudeOpstelling[i].length;j++){
                Speelveld.veldPionnen[i][j]=oudeOpstelling[i][j];
            }
        }
        App.huidigeSpeler = speler;
    }
}
