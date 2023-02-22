package com.example;

public class Speelveld {
    public static int rijen = 7;//=y
    public static int kolommen = 7;//=x
    public static Pion[][] veldPionnen = new Pion[kolommen][rijen];


    public static boolean legalDupliceer(int xNu, int yNu, int xMove, int yMove){
        return 
            !isEmpty(xNu, yNu)
            &&
            isEmpty(xMove,yMove)
            &&
            isAdjacent(xNu,yNu,xMove,yMove);
    }

    public static boolean legalVerplaats(int xNu, int yNu, int xMove, int yMove){
        return 
            !isEmpty(xNu, yNu)
            &&
            isEmpty(xMove,yMove)
            &&
            is2apart(xNu,yNu,xMove,yMove);
    }

    private static boolean isEmpty(int x, int y){
        try{
            return veldPionnen[x][y]==null;
        }catch(Exception e){
            return false;
        }
    }

    private static boolean isAdjacent(int x1, int y1, int x2, int y2){
        return
            (Math.abs(x1-x2)<=1 && Math.abs(y1-y2)<=1)
            &&
            (x1 != x2 && y1!=y2);
            /*if d(x1,x2)<=1 OR d(y1,y2)<=1
            *AND (x1!=x2&&y1!=y2)
            */
    }

    private static boolean is2apart(int x1, int y1, int x2, int y2){
        return 
            (Math.abs(x1-x2)==2 ^ Math.abs(y1-y2)==2)//distance = 2 in maar 1 richting.
            &&
            Math.abs(x1-x2)!=1
            &&
            Math.abs(y1-y2)!=1;
    }
}
