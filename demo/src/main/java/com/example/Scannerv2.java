package com.example;

import java.util.Scanner;

public class Scannerv2 implements IScanner{
    Scanner scanner = new Scanner(System.in);

    public Scannerv2(){}

    @Override
    public int nextInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } 
            catch (NumberFormatException e) {
                System.out.println("Voer enkel cijfers in.");
            }
        }
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
