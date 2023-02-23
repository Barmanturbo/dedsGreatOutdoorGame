package com.example;

import java.io.PrintWriter;

public class Tgowprinter implements IPrinter{
    private PrintWriter printer = new PrintWriter(System.out,true);

    @Override
    public void println() {
        printer.println();
    }

    @Override
    public void println(Object data) {
        printer.println(data);
    }

    @Override
    public void print(Object data) {
        printer.print(data);
    }

    
}