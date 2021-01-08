package com.example.uts_ezyfoody_2201797922;

import java.util.ArrayList;

public class Singleton {
    public ArrayList<inputArray> listItem;
    public static Singleton instance;

    private Singleton(){
        listItem = new ArrayList<inputArray>();
    }

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
