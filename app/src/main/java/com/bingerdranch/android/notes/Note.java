package com.bingerdranch.android.notes;

import android.media.Image;

public class Note{
    private String text = "";
    private String name = "";
    private int image;

    public Note(String text, String name, int image) {
        this.text = text;
        this.name = name;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImageView(int image) {
        this.image = image;
    }
}
