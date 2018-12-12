package com.example.marijn.friendsr;

import java.io.Serializable;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class Friend implements Serializable {
    private String name, bio;
    private int drawableId;
    private float rating;

    // Friend constructor, used to store friend objects
    public Friend(String name, String bio, int drawableId) {
        this.name = name;
        this.bio = bio;
        this.drawableId = drawableId;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}

