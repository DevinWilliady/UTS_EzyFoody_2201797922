package com.example.uts_ezyfoody_2201797922;

import java.io.Serializable;
import java.util.ArrayList;

public class inputArray implements Serializable {

    private String name;
    private int price;
    private int quantity;

    public inputArray(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
