package com.example;

import com.example.IScanner;

public class App {
    public static void main(String[] args) {
        Game.init(2);
        Speelveld.printBord();

        /*Scannerv2 scanjiklub = new Scannerv2();
        System.out.println("press any button to start game");
        scanjiklub.nextLine();
        kiesSpelers(scanjiklub);
        vulSpelerInfoIn(scanjiklub);
        //Uitleg spel
        //Uitleg symbolen spelers
        IPrinter mistEenSVoorSprinter = new Tgowprinter();
        //Begin spel
        //Einde spel*/

    }

    private static void kiesSpelers(IScanner scanSolo){
        boolean correctewaarde = false;
        int input;

        while(correctewaarde==false){
            System.out.println("Kies het aantal spelers. (min 2, max 4)");
            System.out.println("Of kies 0 om het programma te sluiten.");
            input = scanSolo.nextInt();
            if(input>=2&&input<=4){
                correctewaarde = true;
                Game.init(input);
            }
            if(input==0){
                System.exit(0);
            }
        }
    }

    private static void vulSpelerInfoIn(IScanner scanakin){
        for(Speler s : Game.spelerslijst){
            System.out.println("Kies een naam voor speler 1");
            s.setNaam(scanakin.nextLine());
            while(true){
                System.out.println("Is speler "+s.getNaam()+" een mens of een robot?");
                System.out.println("Voer 1 in als mens en 2 als robot");
                int input = scanakin.nextInt();
                if(input==1){
                    s.setIsRobot(false);
                    System.out.println(""+s.getNaam()+" wordt gespeeld door een mens.");
                    break;
                }
                if(input==2){
                    s.setIsRobot(true);
                    System.out.println(""+s.getNaam()+" wordt gespeeld door de computer.");
                    break;
                }
                System.out.println("Kies een juiste waarde.");
            }
            
        }
    }
}