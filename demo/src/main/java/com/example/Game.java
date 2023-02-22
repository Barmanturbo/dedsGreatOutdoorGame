package com.example;

import java.util.ArrayList;

public class Game {
    public ArrayList<Speler> spelerslijst = new ArrayList<Speler>();

    public void init() {
        schoonmaken();
        spelerslijst.add(new Speler("rood", "p1"));
        spelerslijst.add(new Speler("blauw", "p2"));

        plaatsPionnen();

    }

    private void schoonmaken() {
        for (int i = 0; i <= Speelveld.rijen; i++) {
            for (int j = 0; j <= Speelveld.kolommen; j++) {
                Speelveld.veldPionnen[i][j] = null;
            }
        }
    }

    private void plaatsPionnen() {
        ArrayList<Pion> voegPionToe = new ArrayList<>();
            voegPionToe.add(new Pion(6, 6, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(6, 7, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(7, 6, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(7, 7, spelerslijst.get(0).getKleur()));

            voegPionToe.add(new Pion(1, 1, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(1, 2, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(2, 1, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(2, 2, spelerslijst.get(1).getKleur()));

        if (spelerslijst.size() == 3) {
            voegPionToe.add(new Pion(1, 6, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(2, 6, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(1, 7, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(2, 7, spelerslijst.get(2).getKleur()));
        }
        if (spelerslijst.size() == 4) {
            voegPionToe.add(new Pion(6, 1, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(6, 2, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(7, 1, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(7, 2, spelerslijst.get(2).getKleur()));
        }

        for(Pion p:voegPionToe){
            p.addPawn(p.getXPos(),p.getYPos(),p.getKleur());
        }
    }
}
