package com.bingerdranch.android.notes;

import java.util.ArrayList;

public class Folder {
    private String name;
    private int imageFolder;
    private ArrayList<Note>containNotes;

    public Folder(String name, ArrayList<Note> containNotes) {
        this.name = name;
        this.imageFolder = R.drawable.folder;
        this.containNotes = containNotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(int imageFolder) {
        this.imageFolder = imageFolder;
    }

    public ArrayList<Note> getContainNotes() {
        return containNotes;
    }

    public void setContainNotes(ArrayList<Note> containNotes) {
        this.containNotes = containNotes;
    }
}
