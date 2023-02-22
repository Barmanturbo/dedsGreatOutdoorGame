package com.example;

public class Speler {
    private String kleur;
    private String naam;

    public Speler(String kleur, String naam){
        this.kleur = kleur;
        this.naam = naam;
    }

    public String getKleur() {
        return kleur;
    }
    public String getNaam() {
        return naam;
    }
    
    public int telEigenPionnen() {
        int count = 0;
        for (int i = 0; i < Speelveld.kolommen; i++) {
            for (int j = 0; j < Speelveld.rijen; j++) {
                Pion pion = Speelveld.veldPionnen[i][j];
                if (pion != null && pion.getKleur().equals(this.kleur)){
                    count++;
                }
            }
        }
        return count;
    }
    
}
