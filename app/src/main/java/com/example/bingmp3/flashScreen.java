package com.example.bingmp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.window.SplashScreen;

public class flashScreen extends AppCompatActivity {

    Button btnGetMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        mapping();

        btnGetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(flashScreen.this, broaddingScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void mapping() {
        btnGetMusic = findViewById(R.id.btnGetMusic);
    }

}