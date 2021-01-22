package com.cs349.a4.notepad;

import android.app.ActionBar;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String extra_name = extras.getString("EXTRA_NAME");
        String extra_content = extras.getString("EXTRA_CONTENT");

        final TextView detail_name = findViewById(R.id.detail_name);
        final TextView detail_note = findViewById(R.id.detail_note);
        final int extra_pos = extras.getInt("EXTRA_POS");
        detail_note.setMovementMethod(new ScrollingMovementMethod());

        detail_name.setText(extra_name);
        detail_note.setText(extra_content);


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent resultIntent = new Intent();
                String result_name = detail_name.getText().toString();
                String result_note = detail_note.getText().toString();
                Bundle extras = new Bundle();
                extras.putString("RESULT_NAME",result_name);
                extras.putString("RESULT_NOTE",result_note);
                extras.putInt("RESULT_POS",extra_pos);
                resultIntent.putExtras(extras);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);


        FloatingActionButton saveFAB = findViewById(R.id.saveFAB);
        saveFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                String result_name = detail_name.getText().toString();
                String result_note = detail_note.getText().toString();
                Bundle extras = new Bundle();
                extras.putString("RESULT_NAME",result_name);
                extras.putString("RESULT_NOTE",result_note);
                extras.putInt("RESULT_POS",extra_pos);
                resultIntent.putExtras(extras);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });


    }

}
