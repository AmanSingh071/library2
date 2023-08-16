package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityForgotpasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    ActivityForgotpasswordBinding binding;
    String email;
    FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotpasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPasswordfun();
            }
        });
        binding.loginforgort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(forgotpassword.this,login.class);
                startActivity(intent);
            }
        });
    }
    private void forgotPasswordfun() {
        email= binding.emailsignin.getText().toString();
        if(email.isEmpty())
        {
            Toast.makeText(forgotpassword.this, "Email cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgotpassword.this,"please check your spam Email to reset Password",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(forgotpassword.this,"Try again!!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}