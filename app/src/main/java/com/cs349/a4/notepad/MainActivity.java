package com.cs349.a4.notepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    NoteAdapter adapter;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvNotepad = (RecyclerView) findViewById(R.id.rv_notepad);


//        ArrayList<Note> machine_data = new ArrayList<>();
        ArrayList<Note> machine_data = ReadData();
//        ReadData();

        // test
//        Note t1 = new Note("mynote", "this his content");
//        Note t2 = new Note("0123456789101112", "this his cont\nent2aaaaannnndfddddddsdaweewr");
//        Note t3 = new Note("0123456789101112", "this his cont\nent2aaaaannnndfddddddsdaweewr");
//        Note t4 = new Note("0123456789101112", "this his cont\nent2aaaaannnndfddddddsdaweewr");
//        machine_data.add(t1);
//        machine_data.add(t2);
//        machine_data.add(t3);
//        machine_data.add(t4);

        File data = new File(this.getFilesDir(), "notepad.ser");
        this.model = new Model(machine_data, data);
        adapter = new NoteAdapter(machine_data, this.model);
        rvNotepad.setAdapter(adapter);
        rvNotepad.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addonClick(View view) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_NAME","");
        extras.putString("EXTRA_CONTENT","");
        extras.putInt("EXTRA_POS", -1);
        intent.putExtras(extras);
        startActivityForResult(intent, 2);
    }

//    public void WriteData() {
//        Object ob = adapter.model.notes;
//        try {
//            File file = new File(this.getFilesDir(), "notepad.ser");
//            FileOutputStream fileOut = new FileOutputStream(file);
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(ob);
//            out.close();
//            fileOut.close();
//
//            System.out.println("Write data -------------------------------------------");
//            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public ArrayList<Note> ReadData() {
        ArrayList<Note> notes = null;
        try {
            File file = new File(this.getFilesDir(), "notepad.ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            notes = (ArrayList<Note>) in.readObject();
            in.close();
            fileIn.close();

//            System.out.println("Read data ------------------------------------------------");
//            for(int i = 0; i < notes.size(); i++) {
//                System.out.println(notes.get(i).getName() + " | " + notes.get(i).getContent());
//            }


            return notes;
        } catch (Exception e) {
//            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    // request code:
    // 2 - create new note
    // 3 - note on click
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (2) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    String result_name = extras.getString("RESULT_NAME");
                    String result_note = extras.getString("RESULT_NOTE");
                    if(result_name.length() == 0 && result_note.length() == 0) {

                    } else {
                        adapter.model.addNote(new Note(result_name, result_note));
                    }
                }
//                model.WriteData();
                break;
            }
            case (3) : {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    String result_name = extras.getString("RESULT_NAME");
                    String result_note = extras.getString("RESULT_NOTE");
                    int position = extras.getInt("RESULT_POS");
                    if(result_name.length() == 0 && result_note.length() == 0) {
                        adapter.model.removeNote(position);
                    } else {
                        adapter.model.editNote(position, result_name, result_note);
                    }
                }
//                model.WriteData();
                break;
            }
        }
    }
}
