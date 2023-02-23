package com.example;

import java.util.ArrayList;

public class Game {
    public static ArrayList<Speler> spelerslijst = new ArrayList<Speler>();

    public static void init(int n) {
        schoonmaken();
        spelerslijst.add(new Speler("rood", "p1"));
        spelerslijst.add(new Speler("blauw", "p2"));
        if(n==3){
            spelerslijst.add(new Speler("geel", "p3"));
        }
        if(n==4){
            spelerslijst.add(new Speler("groen","p4"));
        }
        Speelveld.huidigeSpeler = spelerslijst.get(0);
        plaatsPionnen();

    }

    private static void schoonmaken() {
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
            voegPionToe.add(new Pion(5, 5, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(5, 6, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(6, 5, spelerslijst.get(0).getKleur()));
            voegPionToe.add(new Pion(6, 6, spelerslijst.get(0).getKleur()));

            voegPionToe.add(new Pion(1, 1, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(1, 2, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(2, 1, spelerslijst.get(1).getKleur()));
            voegPionToe.add(new Pion(2, 2, spelerslijst.get(1).getKleur()));

        if (spelerslijst.size() == 3) {
            voegPionToe.add(new Pion(1, 5, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(2, 5, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(1, 6, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(2, 6, spelerslijst.get(2).getKleur()));
        }
        if (spelerslijst.size() == 4) {
            voegPionToe.add(new Pion(5, 1, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(5, 2, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(6, 1, spelerslijst.get(2).getKleur()));
            voegPionToe.add(new Pion(6, 2, spelerslijst.get(2).getKleur()));
        }

        for(Pion p:voegPionToe){
            p.voegPionToe(p.getXPos(),p.getYPos(),p.getKleur());
        }
    }
}
