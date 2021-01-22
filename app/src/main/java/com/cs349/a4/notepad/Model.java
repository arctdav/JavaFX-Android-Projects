package com.cs349.a4.notepad;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
    public ArrayList<Note> notes;
    public NoteAdapter adapter;
    public File data;

    public Model(ArrayList<Note> notes, File data) {
        this.data = data;
        this.notes = notes;
    }

    public void addNote (Note note){
        notes.add(0, note);
        adapter.notifyItemInserted(0);
        WriteData();
//        for(int i = 0; i < this.notes.size(); i++) {
//            System.out.println(notes.get(i).getName());
//        }
    }

    public void editNote (Note note, String name, String content){
        int index = notes.indexOf(note);
        if(index >= 0) {
            note.setName(name);
            note.setContent(content);
            notes.set(index, note);
            adapter.notifyItemChanged(index);
            WriteData();
        } else {
            System.out.println("error in editNote");
        }
    }

    public void editNote (int index, String name, String content){
        Note note = notes.get(index);
        if(index >= 0) {
            note.setName(name);
            note.setContent(content);
            notes.set(index, note);
            adapter.notifyItemChanged(index);
            WriteData();
        } else {
            System.out.println("error in editNote");
        }
    }

    public void removeNote(int index) {
//        System.out.println(Integer.toString(index));
        notes.remove(index);
//        for(int i = 0; i < this.notes.size(); i++) {
//            System.out.println(notes.get(i).getName());
//        }
        adapter.notifyItemRemoved(index);
        WriteData();
    }

    public void removeNote(Note note) {
        if (notes.contains(note)) {
            notes.remove(note);
            int index = notes.indexOf(note);
            adapter.notifyItemRemoved(index);
            WriteData();
        }
        else {
            System.out.println("ERRORR: remove note, this note not found go back to check");
        }
    }

    public Note get(int index) {
        return notes.get(index);
    }

    public int size() {
        return notes.size();
    }

    public void WriteData() {
        Object ob = this.notes;
        try {
            File file = this.data;
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ob);
            out.close();
            fileOut.close();

//            System.out.println("Write data -------------------------------------------");
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
