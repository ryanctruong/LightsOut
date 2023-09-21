package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        int clicks = intent.getIntExtra("Taps", -1);

        TextView clicksCounter = findViewById(R.id.clicks);
        clicksCounter.setText("Taps Done: " + clicks);
    }
}