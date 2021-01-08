package com.example.uts_ezyfoody_2201797922;

public class History {
    private int historyId;
    private String address;
    private String date;

    public History(int historyId, String address, String date) {
        this.historyId = historyId;
        this.address = address;
        this.date = date;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
