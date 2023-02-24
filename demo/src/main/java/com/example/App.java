package com.example;

public class App {
    public static Speler huidigeSpeler;

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
            System.out.println("Kies het aantal spelers. (min 2, max 4)");
            System.out.println("Of kies 0 om het programma te sluiten.");
            input = scanSolo.nextInt();
            if (input >= 2 && input <= 4) {
                correctewaarde = true;
                Game.init(input);
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
    }

    public static void beurt(IScanner scan2D2) {
        if (huidigeSpeler.geenLegalMoveBeschikbaar()) {
            gameOver();
        } else {
            System.out.println("" + huidigeSpeler + " is aan de beurt.");
            for (Speler s : Game.spelerslijst) {
                System.out.println(s.getNaam() + ": " + s.countEigen() + " punten");
            }
            Speelveld.printBord();
            System.out.println("Kies je pion die je wil verplaatsen of dupliceren.");
            System.out.println("Schrijf in de vorm [x-coordinaat],[y-coordinaat], zoals 3,4");
            System.out.println("Of typ \"redo\" als je je een zet terug wil doen.");
            if (huidigeSpeler.getIsRobot()) {
                Robotmoves.vraagCoordinatenInputRobot();
            } else {
                vraagCoordinatenInput1Human(scan2D2);
            }

            if (Game.spelerslijst.indexOf(huidigeSpeler) >= Game.spelerslijst.size() - 1) {
                huidigeSpeler = Game.spelerslijst.get(0);
            } else {
                huidigeSpeler = Game.spelerslijst.get(Game.spelerslijst.indexOf(huidigeSpeler) + 1);
            }
        }

    }

    private static void vraagCoordinatenInput1Human(IScanner scanGonJinn) {
        int xNu = Speelveld.rijen + 1;
        int yNu = Speelveld.kolommen + 1;

        loop1: while (true) {
            try {
                String input = scanGonJinn.nextLine();

                if (input == "redo") {
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

                if (xNu < Speelveld.rijen && xNu >= 0 && yNu < Speelveld.kolommen && yNu >= 0) {

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

    private static void gameOver() {
    }
}