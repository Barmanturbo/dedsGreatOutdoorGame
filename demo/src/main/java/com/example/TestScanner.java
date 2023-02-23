package com.example;

public class TestScanner implements IScanner {

    String waarde;
    public void setNextInt(int input){
        waardeInt = input;
    }

    @Override
    public String nextLine() {
        return waarde;
    }

    
    
    int waardeInt;
    
    public void setNextLine(String input){
        waarde = input;
    }

    @Override
    public int nextInt() {
        // TODO Auto-generated method stub
        return waardeInt;
    }
}
