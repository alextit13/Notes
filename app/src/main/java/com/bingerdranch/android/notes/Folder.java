package com.bingerdranch.android.notes;


import java.util.ArrayList;

public class Folder{
    private String name;
    private int image;
    private ArrayList<Note>list;

    public Folder(String name, int image, ArrayList<Note> list) {
        this.name = name;
        this.image = image;
        this.list = list;
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

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Note> getList() {
        return list;
    }

    public void setList(ArrayList<Note> list) {
        this.list = list;
    }
}
