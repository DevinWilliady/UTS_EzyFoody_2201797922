package com.example.uts_ezyfoody_2201797922;

public class BalanceHandler {
    private double balance;
    private static BalanceHandler instance;

    private BalanceHandler(){
        this.balance = 100000;
    }

    public static  BalanceHandler getInstance(){
        if(instance == null){
            instance = new BalanceHandler();
        }
        return instance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
