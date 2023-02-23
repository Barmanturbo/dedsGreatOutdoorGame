package com.example;

import java.util.ArrayList;
import java.util.Random;

public class Robotmoves {
    static int stackGrootte = 0;
    public static void vraagCoordinatenInputRobot() {
        while(true){
            try{
                randomZet();
                break;
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    static void randomZet() throws Exception {
        Stack allemoves = vindAlleMoves();
        Random random = new Random();
        int nummer = random.nextInt(stackGrootte);
        Zet z = new Zet(0,0,0,0,false);
        for(int i=0;i<nummer;i++){
            z = (Zet) allemoves.pakPannenkoekVanBord();
        }
        if(z.isDupliceer){
            Speelveld.veldPionnen[z.xNu][z.yNu].dupliceer(z.xZo, z.yZo);
        }else{
            if(!z.isDupliceer){
                Speelveld.veldPionnen[z.xNu][z.yNu].verplaats(z.xZo, z.yZo);
            }else{
                throw(new Exception("An error occurred at RobotMoves. Trying again"));
            }
        }
    }

    public static Stack vindAlleMoves() {
        Stack gecontroleerdeMoves = new Stack();
        stackGrootte = 0;


        ArrayList<Pion> alJouwPionnen = new ArrayList<Pion>();//VIND ALLE EIGEN PIONNEN
        for (int i = 0; i < Speelveld.rijen; i++) {
            for (int j = 0; j < Speelveld.kolommen; j++) {
                if (Speelveld.veldPionnen[i][j] != null) {
                    if (Speelveld.veldPionnen[i][j].getKleur() == App.huidigeSpeler.getKleur()) {
                        alJouwPionnen.add(Speelveld.veldPionnen[i][j]);
                    }
                }
            }
        }

        for(Pion p : alJouwPionnen){
            //check eerst alle legal verplaats
            if(Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos() + 2, p.getYPos())){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() + 2, p.getYPos(), false));
                stackGrootte++;
            }
            if(Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() + 2)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() + 2, false));
                stackGrootte++;
            }
            if((Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos() - 2, p.getYPos()))){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() - 2, p.getYPos(), false));
                stackGrootte++;
            }
            if((Speelveld.legalVerplaats(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() - 2))){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() - 2, false));
                stackGrootte++;
            }
            //Vervolgens alle legal dupliceer
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos() - 1)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos() - 1, true));
                stackGrootte++;
            }
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos())){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos(),false));
                stackGrootte++;
            }
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos() + 1)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() - 1, p.getYPos() + 1, true));
                stackGrootte++;
            }   
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() - 1)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() - 1,true));
                stackGrootte++;
            }
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() + 1)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos(), p.getYPos() + 1,true));
                stackGrootte++;
            }
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos() - 1)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos() - 1,true));
                stackGrootte++;
            }
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos())){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos(),true));
                stackGrootte++;
            }   
            if(Speelveld.legalDupliceer(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos() + 1)){
                gecontroleerdeMoves.nieuwPannenkoekOpBord(new Zet(p.getXPos(), p.getYPos(), p.getXPos() + 1, p.getYPos() + 1,true));
                stackGrootte++;
            }

        }    
        return gecontroleerdeMoves;

    }
}
