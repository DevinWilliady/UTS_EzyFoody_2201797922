package com.example.uts_ezyfoody_2201797922;

import java.util.ArrayList;

public class HistoryHandler {

    public ArrayList<History> history;
    private static HistoryHandler instance;

    private HistoryHandler(){
        history = new ArrayList<History>();
    }

    public static HistoryHandler getInstance(){
        if(instance == null){
            instance = new HistoryHandler();
        }
        return instance;
    }
}
