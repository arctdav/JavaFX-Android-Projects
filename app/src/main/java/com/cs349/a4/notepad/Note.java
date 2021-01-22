package com.cs349.a4.notepad;

import java.io.Serializable;

public class Note implements Serializable {

    private String name;
    private String content;

    public Note(String name, String content){
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }
}
