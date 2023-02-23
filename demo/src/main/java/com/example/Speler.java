package com.example;

import java.util.ArrayList;

public class Speler {
    private String kleur;
    private String naam;
    private boolean isRobot;

    public Speler(String kleur, String naam) {
        this.kleur = kleur;
        this.naam = naam;
        this.isRobot=false;
    }

    public String getKleur() {return kleur;}
    public void setKleur(String kleur){this.kleur = kleur;}

    public String getNaam() {return naam;}
    public void setNaam(String naam){this.naam = naam;}

    public void setIsRobot(boolean isRobot){this.isRobot = isRobot;}
    public boolean getIsRobot(){return isRobot;}

    public int countEigen() {
        int count = 0;
        for (int i = 0; i < Speelveld.kolommen; i++) {
            for (int j = 0; j < Speelveld.rijen; j++) {
                Pion pion = Speelveld.veldPionnen[i][j];
                if (pion != null && pion.getKleur().equals(this.kleur)) {
                    count++;
                }
            }
        }
        return count;
    }

    public String doeZet(int xOud, int yOud, int xNieuw, int yNieuw){
        String errBericht;

        if(Speelveld.veldPionnen[xOud][yOud].getKleur()==getKleur()){
            try{
                Speelveld.veldPionnen[xOud][yOud].dupliceer(xNieuw, yNieuw);

                Speelveld.movegeschiedenisStack.nieuwPannenkoekOpBord(new UndoData());

                return "Speler "+naam+" dupliceerde x"+xOud+"y"+yOud+"naar x"+xNieuw+"y"+yNieuw;
            }catch(Exception e){
                errBericht = e.getMessage();
            }
            try{
                Speelveld.veldPionnen[xOud][yOud].verplaats(xNieuw, yNieuw);

                Speelveld.movegeschiedenisStack.nieuwPannenkoekOpBord(new UndoData());
                
                return "Speler "+naam+" verplaatste x"+xOud+"y"+yOud+"naar x"+xNieuw+"y"+yNieuw;
            }catch(Exception f){
                errBericht=f.getMessage();
            }
            return errBericht;
        }else{
            return "Kies een pion van je eigen kleur";
        }
    }

    public boolean geenLegalMoveBeschikbaar() {
        ArrayList<Pion> alJouwPionnen = new ArrayList<Pion>();
        for (int i = 0; i < Speelveld.rijen; i++) {
            for (int j = 0; j < Speelveld.kolommen; j++) {
                if (Speelveld.veldPionnen[i][j] != null) {
                    if (Speelveld.veldPionnen[i][j].getKleur() == getKleur()) {
                        alJouwPionnen.add(Speelveld.veldPionnen[i][j]);
                    }
                }
            }
        }
        // Nu heb je al jouw pionnen in een lijstje staan.
        for (Pion p : alJouwPionnen) {
            if (
                // Controleer voor alle sprongen
                // Check plek 2 boven deze plek
                (Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos() + 2, p.getYPos()))
                || // Check plek 2 rechts van deze plek
                (Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() + 2))
                || // Check plek 2 onder deze plek
                (Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos() - 2, p.getYPos()))
                || // Check plek 2 links van deze plek
                (Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() - 2))) {
                return false;
            }
            if (
                // Controleer voor alle aanloggende vakjes of dupliceren kan
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos() - 1))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos()))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos() + 1))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() - 1))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() + 1))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos() - 1))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos()))
                ||
                (Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos() + 1))) {
                return false;
            }
        }
        return true;
    }
}
