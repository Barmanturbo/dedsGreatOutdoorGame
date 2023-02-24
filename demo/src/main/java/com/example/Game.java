package com.example;

import java.util.ArrayList;

public class Game {
    public static ArrayList<Speler> spelerslijst = new ArrayList<Speler>();

    public static void init(int n) {
        schoonmaken();
        spelerslijst.add(new Speler("a", "p1"));
        spelerslijst.add(new Speler("b", "p2"));
        
        App.huidigeSpeler = spelerslijst.get(0);
        plaatsPionnen();

    }

    private static void schoonmaken() {
        spelerslijst.clear();
        for (int i = 0; i <= Speelveld.rijen; i++) {
            for (int j = 0; j <= Speelveld.kolommen; j++) {
                try{
                Speelveld.veldPionnen[i][j] = null;
                }catch(Exception e){}
            }
        }
    }

    private static void plaatsPionnen() {
        ArrayList<Pion> voegPionToe = new ArrayList<>();
            voegPionToe.add(new Pion(Speelveld.kolommen-2, Speelveld.rijen-2, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(Speelveld.kolommen-2, Speelveld.rijen-1, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(Speelveld.kolommen-1, Speelveld.rijen-2, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(Speelveld.kolommen-1, Speelveld.rijen-1, spelerslijst.get(0).getKleur()));

            voegPionToe.add(new Pion(0, 0, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(0, 1, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(1, 0, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(1, 1, spelerslijst.get(1).getKleur()));

        if (spelerslijst.size() == 3 || spelerslijst.size() == 4) {
            voegPionToe.add(new Pion(0, Speelveld.rijen-2, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(1, Speelveld.rijen-2, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(0, Speelveld.rijen-2, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(1, Speelveld.rijen-2, spelerslijst.get(2).getKleur()));
        }
        if (spelerslijst.size() == 4) {
            voegPionToe.add(new Pion(Speelveld.kolommen-2, 0, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(Speelveld.kolommen-2, 1, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(Speelveld.kolommen-2, 0, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(Speelveld.kolommen-2, 1, spelerslijst.get(2).getKleur()));
        }

        for(Pion p:voegPionToe){
            p.voegPionToe(p.getXPos(),p.getYPos(),p.getKleur());
        }
    }
}
