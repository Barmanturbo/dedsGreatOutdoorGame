package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Robotmoves {
    static int stackGrootte = 0;

    public static void vraagCoordinatenInputRobot() {
        while (true) {
            try {
                //randomZet();
                beterv1();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void beterv1() throws Exception {
        // vind alle moves die de meeste punten opleveren
        ArrayList<Zet> meestePuntenMoves = vinddMeestePuntenMoves();

        // kies vervolgens een getal tussen 0 en meestePuntenMoves.size()
        Random random = new Random();
        int nummer = random.nextInt(meestePuntenMoves.size());

        // Doe vervolgens die zet
        if (meestePuntenMoves.get(nummer).isDupliceer) {
            Speelveld.veldPionnen[meestePuntenMoves.get(nummer).xNu][meestePuntenMoves.get(nummer).yNu]
                    .dupliceer(meestePuntenMoves.get(nummer).xZo, meestePuntenMoves.get(nummer).yZo);
        } else {
            Speelveld.veldPionnen[meestePuntenMoves.get(nummer).xNu][meestePuntenMoves.get(nummer).yNu]
                    .verplaats(meestePuntenMoves.get(nummer).xZo, meestePuntenMoves.get(nummer).yZo);
        }

    }

    private static ArrayList<Zet> vinddMeestePuntenMoves() {
        ArrayList<Zet> gecontroleerdeMoves = new ArrayList<Zet>();
        int hoogstePunten = 0;

        ArrayList<Pion> alJouwPionnen = new ArrayList<Pion>();// VIND ALLE EIGEN PIONNEN
        for (int i = 0; i < Speelveld.rijen; i++) {
            for (int j = 0; j < Speelveld.kolommen; j++) {
                if (Speelveld.veldPionnen[i][j] != null) {
                    if (Speelveld.veldPionnen[i][j].getKleur() == App.huidigeSpeler.getKleur()) {
                        alJouwPionnen.add(Speelveld.veldPionnen[i][j]);
                    }
                }
            }
        }

        for (Pion p : alJouwPionnen) {
            int xPos = p.getXPos();
            int yPos = p.getYPos();

            // check eerst alle legal verplaats
            if (Speelveld.legalVerplaats(xPos, yPos, xPos + 2, yPos)) {
                if (berekenVerhogingPunten(xPos + 2, yPos, false) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos + 2, yPos, false));
                    hoogstePunten = berekenVerhogingPunten(xPos + 2, yPos, false);
                }
            }
            if (Speelveld.legalVerplaats(xPos, yPos, xPos, yPos + 2)) {
                if (berekenVerhogingPunten(xPos, yPos + 2, false) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos, yPos + 2, false));
                    hoogstePunten = berekenVerhogingPunten(xPos, yPos + 2, false);
                }
            }
            if ((Speelveld.legalVerplaats(xPos, yPos, xPos - 2, yPos))) {
                if (berekenVerhogingPunten(xPos - 2, yPos, false) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos - 2, yPos, false));
                    hoogstePunten = berekenVerhogingPunten(xPos - 2, yPos, false);
                }
            }
            if ((Speelveld.legalVerplaats(xPos, yPos, xPos, yPos - 2))) {
                if (berekenVerhogingPunten(xPos, yPos - 2, false) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos, yPos - 2, false));
                    hoogstePunten = berekenVerhogingPunten(xPos, yPos - 2, false);
                }
            }
            // Vervolgens alle legal dupliceer
            if (Speelveld.legalDupliceer(xPos, yPos, xPos - 1, yPos - 1)) {
                if (berekenVerhogingPunten(xPos - 1, yPos - 1, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos - 1, yPos - 1, true));
                    hoogstePunten = berekenVerhogingPunten(xPos - 1, yPos - 1, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos - 1, yPos)) {
                if (berekenVerhogingPunten(xPos - 1, yPos, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos - 1, yPos, false));
                    hoogstePunten = berekenVerhogingPunten(xPos - 1, yPos, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos - 1, yPos + 1)) {
                if (berekenVerhogingPunten(xPos - 1, yPos + 1, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos - 1, yPos + 1, true));
                    hoogstePunten = berekenVerhogingPunten(xPos - 1, yPos + 1, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos, yPos - 1)) {
                if (berekenVerhogingPunten(xPos, yPos - 1, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos, yPos - 1, true));
                    hoogstePunten = berekenVerhogingPunten(xPos, yPos - 1, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos, yPos + 1)) {
                if (berekenVerhogingPunten(xPos, yPos + 1, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos, yPos + 1, true));
                    hoogstePunten = berekenVerhogingPunten(xPos, yPos + 1, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos + 1, yPos - 1)) {
                if (berekenVerhogingPunten(xPos + 1, yPos + 1, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos + 1, yPos - 1, true));
                    hoogstePunten = berekenVerhogingPunten(xPos + 1, yPos + 1, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos + 1, yPos)) {
                if (berekenVerhogingPunten(xPos + 1, yPos, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos + 1, yPos, true));
                    hoogstePunten = berekenVerhogingPunten(xPos + 1, yPos, true);
                }
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos + 1, yPos + 1)) {
                if (berekenVerhogingPunten(xPos + 1, yPos + 1, true) >= hoogstePunten) {
                    gecontroleerdeMoves.add(new Zet(xPos, yPos, xPos + 1, yPos + 1, true));
                    hoogstePunten = berekenVerhogingPunten(xPos + 1, yPos + 1, true);
                }
            }

        }

        for (Zet z : gecontroleerdeMoves) {// Haal nu alle moves uit de lijst die niet
            if (berekenVerhogingPunten(z.xZo, z.yZo, z.isDupliceer) != hoogstePunten) {
                gecontroleerdeMoves.remove(z);
            }
        }
        return gecontroleerdeMoves;

    }

    private static int berekenVerhogingPunten(int x, int y, boolean isDupliceer) {
        int punten = 0;
        for (int j = 1; j >= -1; j--) {// controleer de kolom boven,onder en van veldPionnen[x][y]
            for (int i = -1; i <= 1; i++) {// en de rij links, rechts en van veldPionnen[x][y]
                try {

                    if (Speelveld.veldPionnen[x - i][y + j] != null) {
                        punten++;
                    }
                } catch (Exception e) {
                }
            }
        }
        // Maar hij rekent ook punten als op zijn eigen plaats een pion staat, wat
        // altijd waar is.
        // Dan hoef je alleen nog een punt af te trekken als de pion verplaatst is en
        // niet gedupliceerd.
        if (!isDupliceer) {
            punten--;
        }
        return punten;
    }

    private static void randomZet() throws Exception {
        Stack allemoves = vindAlleMoves();
        Random random = new Random();
        int nummer = random.nextInt(stackGrootte);
        Zet z = new Zet(0, 0, 0, 0, false);
        for (int i = 0; i < nummer; i++) {
            z = (Zet) allemoves.pakPannenkoekVanBord();
        }
        if (z.isDupliceer) {
            Speelveld.veldPionnen[z.xNu][z.yNu].dupliceer(z.xZo, z.yZo);
        } else {
            if (!z.isDupliceer) {
                Speelveld.veldPionnen[z.xNu][z.yNu].verplaats(z.xZo, z.yZo);
            } else {
                throw (new Exception("An error occurred at RobotMoves. Trying again"));
            }
        }
    }

    private static Stack vindAlleMoves() {
        Stack gecontroleerdeMoves = new Stack();
        stackGrootte = 0;

        ArrayList<Pion> alJouwPionnen = new ArrayList<Pion>();// VIND ALLE EIGEN PIONNEN
        for (int i = 0; i < Speelveld.rijen; i++) {
            for (int j = 0; j < Speelveld.kolommen; j++) {
                if (Speelveld.veldPionnen[i][j] != null) {
                    if (Speelveld.veldPionnen[i][j].getKleur() == App.huidigeSpeler.getKleur()) {
                        alJouwPionnen.add(Speelveld.veldPionnen[i][j]);
                    }
                }
            }
        }
        for (Pion p : alJouwPionnen) {
            int xPos = p.getXPos();
            int yPos = p.getYPos();
            // check eerst alle legal verplaats
            if (Speelveld.legalVerplaats(xPos, yPos, xPos + 2, yPos)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos + 2, yPos, false));
                stackGrootte++;
            }
            if (Speelveld.legalVerplaats(xPos, yPos, xPos, yPos + 2)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos, yPos + 2, false));
                stackGrootte++;
            }
            if ((Speelveld.legalVerplaats(xPos, yPos, xPos - 2, yPos))) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos - 2, yPos, false));
                stackGrootte++;
            }
            if ((Speelveld.legalVerplaats(xPos, yPos, xPos, yPos - 2))) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos, yPos - 2, false));
                stackGrootte++;
            }
            // Vervolgens alle legal dupliceer
            if (Speelveld.legalDupliceer(xPos, yPos, xPos - 1, yPos - 1)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos - 1, yPos - 1, true));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos - 1, yPos)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos - 1, yPos, false));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos - 1, yPos + 1)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos - 1, yPos + 1, true));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos, yPos - 1)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos, yPos - 1, true));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos, yPos + 1)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos, yPos + 1, true));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos + 1, yPos - 1)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos + 1, yPos - 1, true));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos + 1, yPos)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos + 1, yPos, true));
                stackGrootte++;
            }
            if (Speelveld.legalDupliceer(xPos, yPos, xPos + 1, yPos + 1)) {
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(xPos, yPos, xPos + 1, yPos + 1, true));
                stackGrootte++;
            }

        }
        return gecontroleerdeMoves;

    }
}
