package com.example;

public class Pion {

    private String kleur;
    private int xPos;
    private int yPos;

    public Pion(int xPos, int yPos, String kleur){}

    public String getKleur(){return kleur;}
    public void setKleur(String kleur){this.kleur=kleur;}

    public int getXPos(){return xPos;}
    public void setXPos(int xPos){this.xPos=xPos;}

    public int getYPos(){return yPos;}
    public void setYPos(int yPos){this.yPos=yPos;}

    public void verplaats(int x, int y){
        if(Speelveld.legalVerplaats(xPos, yPos, x, y)){
            addPawn(x,y,kleur);
            removePawn(xPos,yPos);
        }

        infect(x,y);
    }

    public void dupliceer(int x, int y){
        if(Speelveld.legalDupliceer(xPos, yPos, x, y)){
            addPawn(x,y,kleur);
        }
        infect(x,y);
    }

    private void addPawn(int x, int y, String kleur){
        Speelveld.veldPionnen[x][y]=new Pion(x,y,kleur);
    }
    private void removePawn(int x, int y){
        Speelveld.veldPionnen[x][y]=null;
    }

    private void infect(int x, int y){
        String phyrexianKleur = Speelveld.veldPionnen[x][y].getKleur();
        for (int j = 1; j >= -1; j--) {
            for(int i = -1; i<=1;i++){
                try {
                    if (Speelveld.veldPionnen[x - i][y + j] != null) {
                        Speelveld.veldPionnen[x - 1][y + j].setKleur(phyrexianKleur);
                    }
                } catch (Exception e) {
                }
            }
        }

    }
}
