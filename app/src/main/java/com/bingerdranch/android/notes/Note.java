package com.bingerdranch.android.notes;

public class Note {
    private String name;
    private int imageNote;
    private String text;

    public Note(String name, String text) {
        this.name = name;
        this.imageNote = R.drawable.note;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageNote() {
        return imageNote;
    }

    public void setImageNote(int imageNote) {
        this.imageNote = imageNote;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
