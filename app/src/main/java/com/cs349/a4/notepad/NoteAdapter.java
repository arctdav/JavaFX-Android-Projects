package com.cs349.a4.notepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button trashcan;
        public TextView contentTextView;
        public LinearLayout tvLinear;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nameTextView = itemView.findViewById(R.id.note_name);
            this.contentTextView = itemView.findViewById(R.id.note_content);
            this.trashcan = itemView.findViewById(R.id.trashcan);
            this.tvLinear = itemView.findViewById(R.id.textviewLinear);
        }
    }

    public Model model;

    public NoteAdapter(ArrayList<Note> notes, Model model) {
        this.model = model;
        model.adapter = this;
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View noteView = inflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(noteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, final int position) {
        Note note = model.get(position);

        TextView textView = holder.nameTextView;
        String nameString = note.getName();
        if(nameString.length() > 10) {
            nameString = nameString.substring(0, 10);
        }
        textView.setText(nameString);
        textView.setTypeface(Typeface.DEFAULT, 1);
        textView.setTextSize(22);
        TextView content_textView = holder.contentTextView;
        String contentString = note.getContent();
        int content_firstNewLine = note.getContent().indexOf("\n");
        if(content_firstNewLine != -1) {
            contentString = contentString.substring(0, content_firstNewLine);
        }
        if(contentString.length() > 15) {
            contentString = contentString.substring(0, 15);
        }
        content_textView.setText(contentString);
        content_textView.setTextColor(Color.GRAY);

        if(nameString.length() == 0) {
            nameString = contentString;
            if(nameString.length() > 10) {
                nameString = contentString.substring(0, 10);
            }
            textView.setText(nameString);
        }


        Button button = holder.trashcan;
        final int adapter_positon = holder.getAdapterPosition();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                System.out.println("trashcan clicked");
                model.removeNote(adapter_positon);
//                model.adapter.notifyItemRemoved(adapter_positon);
            }

        });

        LinearLayout tvLinear = holder.tvLinear;
        tvLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println(adapter_positon);
                Intent intent = new Intent(v.getContext(), ItemDetailActivity.class);

                Note currnote = model.get(adapter_positon);
                String currname = currnote.getName();
                String currcontent = currnote.getContent();

                Bundle extras = new Bundle();
                extras.putString("EXTRA_NAME",currname);
                extras.putString("EXTRA_CONTENT",currcontent);
                extras.putInt("EXTRA_POS", adapter_positon);
                intent.putExtras(extras);

                ((Activity)v.getContext()).startActivityForResult(intent, 3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    // this is for fixing onbindviewholder position error
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
