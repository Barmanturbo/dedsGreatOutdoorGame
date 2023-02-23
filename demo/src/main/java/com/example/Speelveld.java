package com.example;

public class Speelveld {
    public static int rijen = 7;//=y
    public static int kolommen = 7;//=x
    public static Pion[][] veldPionnen = new Pion[kolommen][rijen];

    public static Stack movegeschiedenisStack = new Stack();

    public static IPrinter kopiesjop = new Tgowprinter(); //vernoemd naar kopiesjop Delft

    public static boolean legalDupliceer(int xNu, int yNu, int xMove, int yMove){
        return 
            !isLeeg(xNu, yNu)
            &&
            isLeeg(xMove,yMove)
            &&
            isAanliggend(xNu,yNu,xMove,yMove);
    }

    public static boolean legalVerplaats(int xNu, int yNu, int xMove, int yMove){
        return 
            !isLeeg(xNu, yNu)
            &&
            isLeeg(xMove,yMove)
            &&
            isOp2Afstand(xNu,yNu,xMove,yMove);
    }

    private static boolean isLeeg(int x, int y){
        try{
            return veldPionnen[x][y]==null;
        }catch(Exception e){
            return false;
        }
    }

    private static boolean isAanliggend(int x1, int y1, int x2, int y2){
        return
            (Math.abs(x1-x2)<=1 && Math.abs(y1-y2)<=1)
            &&
            (x1 != x2 && y1!=y2);
            /*if d(x1,x2)<=1 OR d(y1,y2)<=1
            *AND (x1!=x2&&y1!=y2)
            */
    }

    private static boolean isOp2Afstand(int x1, int y1, int x2, int y2){
        return 
            (Math.abs(x1-x2)==2 ^ Math.abs(y1-y2)==2)//distance = 2 in maar 1 richting.
            &&
            Math.abs(x1-x2)!=1
            &&
            Math.abs(y1-y2)!=1;
    }

    public static void printBord(){
        //print x-as
        kopiesjop.print("  ");
        for(int k=0;k<kolommen;k++){
            kopiesjop.print(""+k+" ");
        }
        kopiesjop.println();
        
        //print y-as
        for(int i = 0; i < veldPionnen.length; i++){

            kopiesjop.print(i);

            for(int j = 0; j < veldPionnen[i].length; j++){

                if(veldPionnen[i][j]==null){
                    kopiesjop.print(" .");
                }else{
                    kopiesjop.print(" "+veldPionnen[i][j].getKleur());
                }
            }
            kopiesjop.println();
        }

        //print legenda
        for(Speler s : Game.spelerslijst){
            kopiesjop.print("("+s.getNaam()+": "+s.getKleur()+") ");
        }
        kopiesjop.println();
        
    }

}
