package com.example.penelitianfile.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.penelitianfile.R;

public class Welcome_Screen extends AppCompatActivity {
    protected int waktu_load = 3000; // 3 detik
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.screen_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcome_Screen.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(Welcome_Screen.this);
                finish();
            }
        },waktu_load);
    }
}
