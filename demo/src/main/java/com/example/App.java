package com.example;

public class App {
    public static Speler huidigeSpeler;

    private static boolean gameOver=false;

    public static void main(String[] args) {
        Game.init(2);
        Speelveld.printBord();

        IScanner scanjiklub = new Scannerv2();
        System.out.println("press any button to start game");
        scanjiklub.nextLine();
        kiesSpelers(scanjiklub);
        vulSpelerInfoIn(scanjiklub);
        speluitleg();

        start(scanjiklub);

    }

    private static void kiesSpelers(IScanner scanSolo) {
        boolean correctewaarde = false;
        int input;

        while (correctewaarde == false) {
            System.out.println("Druk 1 om verder te gaan");
            System.out.println("Of kies 0 om het programma te sluiten.");
            input = scanSolo.nextInt();
            if (input >= 1 && input <= 4) {
                correctewaarde = true;
                Game.init(2);
            }
            if (input == 0) {
                System.exit(0);
            }
        }
    }

    private static void vulSpelerInfoIn(IScanner scanakin) {
        for (int i = 0; i < 2; i++) {
            System.out.println("Kies een naam voor speler " + (i + 1));
            Game.spelerslijst.get(i).setNaam(scanakin.nextLine());
            while (true) {
                System.out.println("Is speler " + Game.spelerslijst.get(i).getNaam() + " een mens of een robot?");
                System.out.println("Voer 1 in als mens en 2 als robot");
                int input = scanakin.nextInt();
                if (input == 1) {
                    Game.spelerslijst.get(i).setIsRobot(false);
                    System.out.println("" + Game.spelerslijst.get(i).getNaam() + " wordt gespeeld door een mens.");
                    break;
                }
                if (input == 2) {
                    Game.spelerslijst.get(i).setIsRobot(true);
                    System.out.println("" + Game.spelerslijst.get(i).getNaam() + " wordt gespeeld door de computer.");
                    break;
                }
                System.out.println("Kies een juiste waarde.");
            }
        }

    }

    private static void haalOudSpelBordTerug() {
        UndoData terug = (UndoData) Speelveld.movegeschiedenisStack.pakPannenkoekVanBord();
        terug.terugNaarOudeOpstelling();
    }

    private static void speluitleg() {
        System.out.println("je hebt 2 mogelijke zetten: verplaats of dupliceer.");
        System.out.println(
                "Bij dupliceer plaats je een extra van je eigen pionnen op het bord, aangrenzend aan een vakje met jouw pion.");
        System.out.println("Bij verplaats, verplaats je een van jouw pionnen 2 vakjes in een rechte lijn.");
        System.out.println(
                "Na dupliceren en verplaatsen veranderen alle pionnen in de 8 vakjes om de nieuwe of verplaatste) pion naar jouw kleur.");
        System.out.println("Het spel is voorbij als iemand geen zet meer kan doen.");
        System.out.println("Degene met de meeste pionnen wint.");
    }

    public static void start(IScanner scan3PO) {
        Speelveld.printBord();
        while(!gameOver){
            System.out.println("Druk op een knop om verder te gaan");
            scan3PO.nextLine();
            beurt(scan3PO);
        }
        gameOverBericht();
        scan3PO.nextLine();
        System.exit(0);
    }

    public static void beurt(IScanner scan2D2) {
        Speelveld.printBord();//print bord
        System.out.println();//print witregel

        if (huidigeSpeler.geenLegalMoveBeschikbaar()) {
            gameOver=true;//game over
        } else {

            System.out.println("" + huidigeSpeler.getNaam() + " is aan de beurt.");

            for (Speler s : Game.spelerslijst) {
                System.out.println(s.getNaam() + ": " + s.countEigen() + " punten");
            }
            Speelveld.printBord();//print bord

            //instructies
            System.out.println("Kies je pion die je wil verplaatsen of dupliceren.");
            System.out.println("Schrijf in de vorm [x-coordinaat],[y-coordinaat], zoals 3,4");
            System.out.println("Of typ \"Redo\" als je je een zet terug wil doen.");

            if (huidigeSpeler.getIsRobot()) {
                Robotmoves.vraagCoordinatenInputRobot();
            } else {
                vraagCoordinatenInput1Human(scan2D2);
            }

            if (huidigeSpeler==Game.spelerslijst.get(0)){
                huidigeSpeler = Game.spelerslijst.get(1);
            }else{huidigeSpeler = Game.spelerslijst.get(0);}
        }

    }

    private static void vraagCoordinatenInput1Human(IScanner scanGonJinn) {
        int xNu = Speelveld.rijen + 1;
        int yNu = Speelveld.kolommen + 1;

        loop1: while (true) {
            try {
                String input = scanGonJinn.nextLine();

                if (input.equals("Redo")) {
                    try {
                        haalOudSpelBordTerug();
                        while (huidigeSpeler.getIsRobot()) {
                            haalOudSpelBordTerug();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break loop1; // DIT IS EEN OUT VOOR DEZE LOOP
                }

                String[] integerStrings = input.split("[^0-9]+");

                if (integerStrings[0] != null
                        &&
                        integerStrings[1] != null
                        &&
                        integerStrings.length == 2) {

                    xNu = Integer.parseInt(integerStrings[0]);
                    yNu = Integer.parseInt(integerStrings[1]);
                }

                if (xNu < Speelveld.rijen && xNu >= 0 && yNu < Speelveld.kolommen && yNu >= 0 &&Speelveld.veldPionnen[xNu][yNu]!=null) {

                    vraagCoordinatenInput2(xNu, yNu, scanGonJinn); // DIT IS DE ENIGE ANDERE OUT UIT DEZE LOOP
                    break loop1;

                } else {
                    throw (new Exception("Geef juiste invoer!"));
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Probeer het opnieuw.");
            }
        }

    }

    private static void vraagCoordinatenInput2(int xNu, int yNu, IScanner lukeScanwalker) {
        int xZo = Speelveld.rijen + 1;
        int yZo = Speelveld.kolommen + 1;

        vCI2: while (true) {
            try {
                System.out.println("Kies veld 2");
                String input = lukeScanwalker.nextLine();

                String[] integerStrings = input.split("[^0-9]+");

                if (integerStrings[0] != null
                        &&
                        integerStrings[1] != null
                        &&
                        integerStrings.length == 2) {

                    xZo = Integer.parseInt(integerStrings[0]);
                    yZo = Integer.parseInt(integerStrings[1]);
                }

                if (xZo < Speelveld.rijen && xZo >= 0 && yZo < Speelveld.kolommen && yZo >= 0) {
                

                    huidigeSpeler.doeZet(xNu, yNu, xZo, yZo);

                    break vCI2;

                } else {
                    throw (new Exception("Geef juiste invoer!"));
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Probeer het opnieuw.");
            }

        }

    }

    public static void gameOverBericht(){
        Speelveld.printBord();
        System.out.println("Het spel is voorbij.");
        int speler1score = Game.spelerslijst.get(0).countEigen();
        int speler2score = Game.spelerslijst.get(1).countEigen();
        if(speler1score>speler2score){
            System.out.println("Speler 1 wint.");
        }else{
            if(speler2score>speler1score){
                System.out.println("Speler 2 wint");
            }else{
                System.out.println("Gelijkspel!");
            }
        }
        System.out.println("Druk op een knop om de app af te sluiten.");
    }
}