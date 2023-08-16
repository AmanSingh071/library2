package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivitySplashScreenBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class splashScreen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(splashScreen.this,login.class);
                startActivity(intent);
            }
        });
    }
}