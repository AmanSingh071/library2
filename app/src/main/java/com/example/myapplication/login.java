package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    EditText email,pass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);

                signinuser();
            }
        });
        binding.SignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });
        binding.signinguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        binding.signinforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,forgotpassword.class);
                startActivity(intent);
            }
        });

    }
    private void signinuser() {
        email=findViewById(R.id.emailsignin);
        pass=findViewById(R.id.passwordsignin);




        String email1 = email.getText().toString();
        String pass1= pass.getText().toString();
        if (email1.isEmpty() || pass1.isEmpty()) {
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(login.this,"Email and password cannot be blank",Toast.LENGTH_LONG).show();
            return;
        }

        auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(login.this,"success",Toast.LENGTH_LONG).show();
                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent=new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                }
                else
                {binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(login.this,"failed",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}