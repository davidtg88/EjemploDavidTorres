package com.example.david.ejemplo;

/**
 * Created by David on 08/01/2016.
 */
public class Exercise {
    private int id;
    private String wording;

    public Exercise (int id, String wording){
        this.id = id;
        this.wording = wording;
    }

    public int getId() {
        return id;
    }

    public String getWording() {
        return wording;
    }
}
